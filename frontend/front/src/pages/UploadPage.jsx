import { useEffect, useRef, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import UploadArea from '../components/AreaUpload'
import BaseCard from '../components/BaseCard'

function UploadPage() {
  const [bases, setBases] = useState([])
  const [loading, setLoading] = useState(true)
  const [selectedFile, setSelectedFile] = useState(null)
  const [uploadStatus, setUploadStatus] = useState('')
  const fileInputRef = useRef(null)
  const navigate = useNavigate()

  const loadBases = () => {
    setLoading(true)
    fetch('/operacoes/bases')
      .then(res => res.json())
      .then(data => setBases(data || []))
      .catch(err => {
        console.error('Erro ao carregar bases:', err)
        setBases([])
      })
      .finally(() => setLoading(false))
  }

  useEffect(() => {
    loadBases()
  }, [])

  const handleBrowseClick = () => {
    setUploadStatus('')
    fileInputRef.current?.click()
  }

  const handleFileChange = event => {
    const file = event.target.files?.[0] ?? null
    setSelectedFile(file)
    setUploadStatus(file ? `Selecionado: ${file.name}` : '')
  }

  const handleUploadConfirm = () => {
    if (!selectedFile) {
      setUploadStatus('Selecione um arquivo antes de enviar.')
      return
    }

    const formData = new FormData()
    formData.append('arquivo', selectedFile)

    fetch('/operacoes/carga', {
      method: 'POST',
      body: formData,
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(`Erro ao enviar arquivo: ${response.statusText}`)
        }
        return response.text()
      })
      .then(message => {
        setUploadStatus(`Upload enviado com sucesso.`)
        setSelectedFile(null)
        if (fileInputRef.current) {
          fileInputRef.current.value = ''
        }
        loadBases()
        console.log(message)
      })
      .catch(error => {
        console.error(error)
        setUploadStatus('Falha no envio do arquivo.')
      })
  }

  const handleDelete = (nCarga) => {
    setUploadStatus('')
    if (!nCarga) return

    fetch(`/operacoes/carga/${nCarga}`, {
      method: 'DELETE',
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(`Erro ao deletar carga: ${response.statusText}`)
        }
        return response.text()
      })
      .then(msg => {
        setUploadStatus('Base deletada com sucesso.')
        loadBases()
        console.log(msg)
      })
      .catch(err => {
        console.error(err)
        setUploadStatus('Falha ao deletar a base. Verifique o backend.')
      })
  }

  return (
    <div className="container">
      <div className="upload-header">
        <h1>Carga de Dados Operacionais</h1>

        <p>
          Faça upload de bases CSV para análise no sistema.
        </p>
      </div>

      <UploadArea />

      <input
        ref={fileInputRef}
        type="file"
        accept=".csv"
        style={{ display: 'none' }}
        onChange={handleFileChange}
      />

      <div className="upload-button-wrapper">
        <button className="upload-btn" type="button" onClick={handleBrowseClick}>
          Carregar CSV
        </button>
        <button
          className="confirm-btn"
          type="button"
          onClick={handleUploadConfirm}
          disabled={!selectedFile}
        >
          Enviar
        </button>
      </div>

      {uploadStatus && <p className="upload-status">{uploadStatus}</p>}

      <div className="bases-header">
        <h2>Bases Cadastradas</h2>

        <span>{loading ? 'Carregando...' : `${bases.length} bases disponíveis`}</span>
      </div>

      <div className="bases-grid">
        {loading && <p>Carregando bases...</p>}

        {!loading && bases.map(b => (
          <BaseCard
            key={b.nCarga}
            title={`${b.nCarga} - ${new Date(b.dataCarga).toLocaleDateString()}`}
            date={new Date(b.dataCarga).toLocaleString()}
            status="Ativa"
            quantidade={b.quantidadeOperacoes}
            onAnalysis={() => navigate(`/analysis/${b.nCarga}`)}
            onDelete={() => handleDelete(b.nCarga)}
          />
        ))}
      </div>
    </div>
  )
}

export default UploadPage