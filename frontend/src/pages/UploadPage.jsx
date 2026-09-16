import { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { toast } from '../Toast'

function UploadZone({ onFile, selectedFile }) {
  const [dragging, setDragging] = useState(false)
  const inputRef = useRef(null)

  const handleDrop = e => {
    e.preventDefault()
    setDragging(false)
    const file = e.dataTransfer.files?.[0]
    if (file && file.name.endsWith('.csv')) onFile(file)
    else if (file) toast('Apenas arquivos .csv são aceitos', 'error')
  }

  const handleDragOver = e => { e.preventDefault(); setDragging(true) }
  const handleDragLeave = () => setDragging(false)

  return (
    <div
      className={`upload-zone${dragging ? ' drag-over' : ''}`}
      onDrop={handleDrop}
      onDragOver={handleDragOver}
      onDragLeave={handleDragLeave}
      onClick={() => inputRef.current?.click()}
    >
      <div className="upload-icon">
        <svg width="48" height="48" fill="none" stroke="currentColor" strokeWidth="1.5" viewBox="0 0 24 24">
          <path strokeLinecap="round" strokeLinejoin="round" d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5m-13.5-9L12 3m0 0l4.5 4.5M12 3v13.5"/>
        </svg>
      </div>
      {selectedFile
        ? <p className="file-selected">📄 {selectedFile.name}</p>
        : <>
            <h3>Arraste o CSV aqui</h3>
            <p>ou clique para selecionar · apenas .csv de no máximo 20MB</p>
          </>
      }
      <input
        ref={inputRef}
        type="file"
        accept=".csv"
        style={{ display: 'none' }}
        onChange={e => { const f = e.target.files?.[0]; if (f) onFile(f) }}
      />
    </div>
  )
}

function BaseCard({ base, onAnalysis, onDelete }) {
  const date = new Date(base.dataCarga).toLocaleString('pt-BR')
  return (
    <div className="base-card card">
      <div className="base-card-top">
        <div className="base-card-info">
          <h4>Base #{base.nCarga}</h4>
          <p>Importada em {date}</p>
          <p className="base-qty">{base.quantidadeOperacoes?.toLocaleString('pt-BR')} operações</p>
        </div>
        <span className="badge badge-green">Ativa</span>
      </div>
      <div className="base-card-actions">
        <button className="btn btn-primary btn-sm" style={{ flex: 1 }} onClick={() => onAnalysis(base.nCarga)}>
          <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" d="M3 13.125C3 12.504 3.504 12 4.125 12h2.25c.621 0 1.125.504 1.125 1.125v6.75C7.5 20.496 6.996 21 6.375 21h-2.25A1.125 1.125 0 013 19.875v-6.75zM9.75 8.625c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125v11.25c0 .621-.504 1.125-1.125 1.125h-2.25a1.125 1.125 0 01-1.125-1.125V8.625zM16.5 4.125c0-.621.504-1.125 1.125-1.125h2.25C20.496 3 21 3.504 21 4.125v15.75c0 .621-.504 1.125-1.125 1.125h-2.25a1.125 1.125 0 01-1.125-1.125V4.125z"/></svg>
          Analisar
        </button>
        <button className="btn btn-danger btn-sm btn-icon" onClick={() => onDelete(base.nCarga)}>
          <svg width="14" height="14" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" d="M14.74 9l-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 01-2.244 2.077H8.084a2.25 2.25 0 01-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 00-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 013.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 00-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 00-7.5 0"/></svg>
        </button>
      </div>
    </div>
  )
}

export default function UploadPage() {
  const navigate = useNavigate()
  const [bases, setBases] = useState([])
  const [loading, setLoading] = useState(true)
  const [selectedFile, setSelectedFile] = useState(null)
  const [uploading, setUploading] = useState(false)

  const loadBases = () => {
    setLoading(true)
    fetch('/operacoes/bases')
      .then(r => r.json())
      .then(data => setBases(data || []))
      .catch(() => setBases([]))
      .finally(() => setLoading(false))
  }

  useEffect(() => { loadBases() }, [])

  const handleUpload = () => {
    if (!selectedFile) return
    setUploading(true)
    const fd = new FormData()
    fd.append('arquivo', selectedFile)
    fetch('/operacoes/carga', { method: 'POST', body: fd })
      .then(r => { if (!r.ok) throw new Error(); return r.text() })
      .then(() => { toast('Base carregada com sucesso!', 'success'); setSelectedFile(null); loadBases() })
      .catch(() => toast('Falha ao enviar o arquivo.', 'error'))
      .finally(() => setUploading(false))
  }

  const handleDelete = nCarga => {
    if (!confirm(`Confirmar exclusão da Base #${nCarga}?`)) return
    fetch(`/operacoes/carga/${nCarga}`, { method: 'DELETE' })
      .then(r => { if (!r.ok) throw new Error(); return r.text() })
      .then(() => { toast(`Base #${nCarga} removida.`, 'success'); loadBases() })
      .catch(() => toast('Falha ao remover a base.', 'error'))
  }

  return (
    <div className="page">
      <div className="page-header">
        <div className="header-row">
          <div>
            <h2>Carga de Dados</h2>
            <p>Importe arquivos CSV do Portal de Dados Abertos do BNDES</p>
          </div>
        </div>
      </div>

      <UploadZone onFile={setSelectedFile} selectedFile={selectedFile} />

      <div style={{ display: 'flex', justifyContent: 'flex-end', gap: 10, marginTop: 14 }}>
        {selectedFile && (
          <button className="btn btn-ghost" onClick={() => setSelectedFile(null)}>Cancelar</button>
        )}
        <button
          className="btn btn-primary"
          onClick={handleUpload}
          disabled={!selectedFile || uploading}
        >
          {uploading
            ? 'Enviando...'
            : <>
                <svg width="15" height="15" fill="none" stroke="currentColor" strokeWidth="2" viewBox="0 0 24 24"><path strokeLinecap="round" strokeLinejoin="round" d="M3 16.5v2.25A2.25 2.25 0 005.25 21h13.5A2.25 2.25 0 0021 18.75V16.5M16.5 12L12 16.5m0 0L7.5 12m4.5 4.5V3"/></svg>
                Importar Base
              </>
          }
        </button>
      </div>

      <div className="bases-section">
        <div className="bases-section-header">
          <h3>Bases Cadastradas</h3>
          <span className="bases-count">
            {loading ? 'Carregando…' : `${bases.length} ${bases.length === 1 ? 'base' : 'bases'}`}
          </span>
        </div>

        {loading ? (
          <div className="bases-grid">
            {[1,2,3].map(i => <div key={i} className="skeleton" style={{ height: 148, borderRadius: 16 }} />)}
          </div>
        ) : bases.length === 0 ? (
          <div className="card card-padded" style={{ textAlign: 'center', color: 'var(--text3)', padding: '48px 24px' }}>
            <p style={{ fontSize: 14 }}>Nenhuma base importada ainda. Envie um CSV para começar.</p>
          </div>
        ) : (
          <div className="bases-grid">
            {bases.map(b => (
              <BaseCard
                key={b.nCarga}
                base={b}
                onAnalysis={id => navigate(`/analysis/${id}`)}
                onDelete={handleDelete}
              />
            ))}
          </div>
        )}
      </div>
    </div>
  )
}
