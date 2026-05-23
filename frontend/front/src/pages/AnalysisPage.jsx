import { useNavigate, useParams } from 'react-router-dom'
import { useEffect, useState, useMemo } from 'react'
import GraficoMediaUF from '../components/grafico/GraficoMediaUF'
import GraficoMediaSetor from '../components/grafico/GraficoMediaSetor'
import GraficoMediaPorte from '../components/grafico/GraficoMediaPorte'
import GraficoTopCli from '../components/grafico/GraficoTopCli'

const fmt = v => Number(v || 0).toLocaleString('pt-BR', { style: 'currency', currency: 'BRL', maximumFractionDigits: 0 })
const fmtN = v => Number(v || 0).toLocaleString('pt-BR')

function Skeleton({ h = 300 }) {
  return <div className="skeleton" style={{ height: h, borderRadius: 16 }} />
}

function StatCard({ label, value, sub, loading }) {
  return (
    <div className="stat-card card">
      <div className="stat-label">{label}</div>
      {loading
        ? <div className="skeleton" style={{ height: 36, width: '60%', marginTop: 6 }} />
        : <div className="stat-value">{value}</div>
      }
      {sub && <div className="stat-sub">{sub}</div>}
    </div>
  )
}

const PAGE_SIZE = 15

export default function AnalysisPage() {
  const navigate = useNavigate()
  const { id } = useParams()
  const baseId = parseInt(id, 10)

  const [loading, setLoading] = useState(true)
  const [data, setData] = useState({
    qtdTotal: 0, ativas: 0, liquidadas: 0,
    mediaUf: [], mediaSetor: [], mediaPorte: [], topClientes: [], qtdUf: []
  })

  // Tabela
  const [operacoes, setOperacoes] = useState([])
  const [loadingTabela, setLoadingTabela] = useState(true)
  const [filtroTexto, setFiltroTexto] = useState('')
  const [filtroUF, setFiltroUF] = useState('')
  const [filtroSetor, setFiltroSetor] = useState('')
  const [filtroSituacao, setFiltroSituacao] = useState('')
  const [page, setPage] = useState(0)

  useEffect(() => {
    const base = `?base=${baseId}`

    Promise.all([
      fetch('/operacoes/bases').then(r => r.json()),
      fetch(`/estatisticas/quantidade-operacoes-situacao${base}`).then(r => r.json()),
      fetch(`/estatisticas/media-valor-uf${base}`).then(r => r.json()),
      fetch(`/estatisticas/media-valor-setor${base}`).then(r => r.json()),
      fetch(`/estatisticas/media-valor-porte${base}`).then(r => r.json()),
      fetch(`/estatisticas/top-clientes${base}`).then(r => r.json()),
      fetch(`/estatisticas/quantidade-operacoes-uf${base}`).then(r => r.json()),
    ]).then(([bases, situacoes, mediaUf, mediaSetor, mediaPorte, topClientes, qtdUf]) => {
      const base = bases.find(b => b.nCarga === baseId)
      const ativas = situacoes.find(s => s.situacaoDaOperacao?.toUpperCase() === 'ATIVA')?.quantidade || 0
      const liquidadas = situacoes.find(s => s.situacaoDaOperacao?.toUpperCase() === 'LIQUIDADA')?.quantidade || 0
      setData({
        qtdTotal: base?.quantidadeOperacoes || 0, ativas, liquidadas,
        mediaUf: (mediaUf || []).map(i => ({ uf: i.uf, valor: +i.mediaValor?.toFixed(2) })),
        mediaSetor: (mediaSetor || []).map(i => ({ setorCnae: i.setorCnae, mediaValor: +i.mediaValor?.toFixed(2) })),
        mediaPorte: (mediaPorte || []).map(i => ({ porteDoCliente: i.porteDoCliente, mediaValor: +i.mediaValor?.toFixed(2) })),
        topClientes: (topClientes || []).map(i => ({ nome: i.cliente, valor: i.totalDesembolsado })),
        qtdUf: qtdUf || [],
      })
    }).finally(() => setLoading(false))

    // Buscar operações para a tabela
    fetch(`/operacoes?base=${baseId}`)
      .then(r => r.json())
      .then(d => setOperacoes(d || []))
      .catch(() => setOperacoes([]))
      .finally(() => setLoadingTabela(false))
  }, [id])

  // Filtros derivados
  const ufs = useMemo(() => [...new Set(operacoes.map(o => o.uf).filter(Boolean))].sort(), [operacoes])
  const setores = useMemo(() => [...new Set(operacoes.map(o => o.setorCnae).filter(Boolean))].sort(), [operacoes])
  const situacoes = useMemo(() => [...new Set(operacoes.map(o => o.situacaoDaOperacao).filter(Boolean))].sort(), [operacoes])

  const filtradas = useMemo(() => {
    let r = operacoes
    if (filtroTexto) {
      const q = filtroTexto.toLowerCase()
      r = r.filter(o => o.cliente?.toLowerCase().includes(q) || o.cpfCnpj?.includes(q))
    }
    if (filtroUF) r = r.filter(o => o.uf === filtroUF)
    if (filtroSetor) r = r.filter(o => o.setorCnae === filtroSetor)
    if (filtroSituacao) r = r.filter(o => o.situacaoDaOperacao === filtroSituacao)
    return r
  }, [operacoes, filtroTexto, filtroUF, filtroSetor, filtroSituacao])

  const totalPages = Math.ceil(filtradas.length / PAGE_SIZE)
  const pageData = filtradas.slice(page * PAGE_SIZE, (page + 1) * PAGE_SIZE)

  const resetFiltros = () => { setFiltroTexto(''); setFiltroUF(''); setFiltroSetor(''); setFiltroSituacao(''); setPage(0) }

  const temFiltro = filtroTexto || filtroUF || filtroSetor || filtroSituacao

  return (
    <div className="page">
      <div className="page-nav">
        <button className="btn btn-secondary btn-sm" onClick={() => navigate('/')}>
          ← Bases
        </button>
        <h2>Análise · Base #{id}</h2>
      </div>

      {/* Stats */}
      <div className="stat-grid">
        <StatCard label="Total de operações" value={fmtN(data.qtdTotal)} loading={loading} />
        <StatCard label="Operações ativas" value={fmtN(data.ativas)} loading={loading} />
        <StatCard label="Liquidadas" value={fmtN(data.liquidadas)} loading={loading} />
      </div>

      <div className="analysis-layout">
        <div className="analysis-main">

          {/* Tabela com filtros */}
          <div className="card table-card">
            <div className="table-filters">
              <input
                className="filter-input"
                placeholder="Buscar por empresa ou CNPJ…"
                value={filtroTexto}
                onChange={e => { setFiltroTexto(e.target.value); setPage(0) }}
              />
              <select className="filter-select" value={filtroUF} onChange={e => { setFiltroUF(e.target.value); setPage(0) }}>
                <option value="">Todos os estados</option>
                {ufs.map(u => <option key={u}>{u}</option>)}
              </select>
              <select className="filter-select" value={filtroSetor} onChange={e => { setFiltroSetor(e.target.value); setPage(0) }}>
                <option value="">Todos os setores</option>
                {setores.map(s => <option key={s}>{s}</option>)}
              </select>
              <select className="filter-select" value={filtroSituacao} onChange={e => { setFiltroSituacao(e.target.value); setPage(0) }}>
                <option value="">Todas as situações</option>
                {situacoes.map(s => <option key={s}>{s}</option>)}
              </select>
              {temFiltro && (
                <button className="btn btn-ghost btn-sm" onClick={resetFiltros}>Limpar filtros</button>
              )}
            </div>
            <div className="table-wrap">
              {loadingTabela ? (
                <div style={{ padding: 24 }}><Skeleton h={180} /></div>
              ) : pageData.length === 0 ? (
                <div className="table-empty">
                  {temFiltro ? 'Nenhuma operação encontrada com esses filtros.' : 'Nenhuma operação disponível.'}
                </div>
              ) : (
                <table>
                  <thead>
                    <tr>
                      <th>Empresa</th>
                      <th>UF</th>
                      <th>Setor</th>
                      <th>Porte</th>
                      <th>Valor Desembolsado</th>
                      <th>Situação</th>
                    </tr>
                  </thead>
                  <tbody>
                    {pageData.map((op, i) => (
                      <tr key={op.id ?? i}>
                        <td style={{ maxWidth: 220 }}>
                          <div style={{ fontWeight: 500, fontSize: 13, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{op.cliente || '—'}</div>
                          {op.cpfCnpj && <div className="td-mono" style={{ fontSize: 11 }}>{op.cpfCnpj}</div>}
                        </td>
                        <td className="td-mono">{op.uf || '—'}</td>
                        <td style={{ fontSize: 12.5, maxWidth: 160, overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>{op.setorCnae || '—'}</td>
                        <td style={{ fontSize: 12.5 }}>{op.porteDoCliente || '—'}</td>
                        <td className="td-value">{fmt(op.valorDesembolsadoReais)}</td>
                        <td>
                          <span className={`badge ${op.situacaoDaOperacao?.toUpperCase() === 'ATIVA' ? 'badge-green' : op.situacaoDaOperacao?.toUpperCase() === 'LIQUIDADA' ? 'badge-blue' : 'badge-yellow'}`}>
                            {op.situacaoDaOperacao || '—'}
                          </span>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>
            {!loadingTabela && filtradas.length > PAGE_SIZE && (
              <div className="table-pagination">
                <span>{filtradas.length} resultado{filtradas.length !== 1 ? 's' : ''} · página {page + 1} de {totalPages}</span>
                <div className="pagination-btns">
                  <button onClick={() => setPage(0)} disabled={page === 0}>«</button>
                  <button onClick={() => setPage(p => p - 1)} disabled={page === 0}>‹</button>
                  <button onClick={() => setPage(p => p + 1)} disabled={page >= totalPages - 1}>›</button>
                  <button onClick={() => setPage(totalPages - 1)} disabled={page >= totalPages - 1}>»</button>
                </div>
              </div>
            )}
          </div>

          {/* Gráficos */}
          {loading ? (
            <>
              <Skeleton />
              <Skeleton />
              <Skeleton />
              <Skeleton />
            </>
          ) : (
            <>
              <GraficoMediaUF data={data.mediaUf} />
              <GraficoMediaSetor data={data.mediaSetor} />
              <GraficoMediaPorte data={data.mediaPorte} />
              <GraficoTopCli data={data.topClientes} />
            </>
          )}
        </div>

        {/* Sidebar de estados */}
        <div className="analysis-sidebar">
          <div className="card states-card">
            <div className="states-card-header">Operações por estado</div>
            <div className="states-scroll">
              {loading
                ? [1,2,3,4,5].map(i => (
                    <div key={i} className="state-row">
                      <div className="skeleton" style={{ width: 32, height: 16 }} />
                      <div className="skeleton" style={{ width: 48, height: 16 }} />
                    </div>
                  ))
                : [...data.qtdUf].sort((a, b) => b.quantidade - a.quantidade).map(item => (
                    <div key={item.uf} className="state-row">
                      <span className="state-uf">{item.uf}</span>
                      <span className="state-qty">{fmtN(item.quantidade)}</span>
                    </div>
                  ))
              }
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
