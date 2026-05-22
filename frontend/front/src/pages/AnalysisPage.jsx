import { useNavigate, useParams } from 'react-router-dom'
import { useEffect, useState } from 'react'
import StatsCard from '../components/StatsCard'
import StatesList from '../components/OperacoesEstados'

import MediaUfChart from '../components/grafico/GraficoMediaUF'
import MediaSetorChart from '../components/grafico/GraficoMediaSetor'
import MediaPorteChart from '../components/grafico/GraficoMediaPorte'
import TopClientsChart from '../components/grafico/GraficoTopCli'

function AnalysisPage() {
  const navigate = useNavigate()
  const { id } = useParams()
  const [quantidadeOperacoes, setQuantidadeOperacoes] = useState(0)
  const [operacoesPorSituacao, setOperacoesPorSituacao] = useState([])
  const [mediaUf, setMediaUf] = useState([])
  const [mediaSetor, setMediaSetor] = useState([])
  const [mediaPorte, setMediaPorte] = useState([])
  const [topClientes, setTopClientes] = useState([])
  const [quantidadeUf, setQuantidadeUf] = useState([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const baseId = parseInt(id, 10)

    // Buscar quantidade total de operações
    fetch('/operacoes/bases')
      .then(res => res.json())
      .then(bases => {
        const base = bases.find(b => b.nCarga === baseId)
        if (base) {
          setQuantidadeOperacoes(base.quantidadeOperacoes || 0)
        }
      })
      .catch(err => console.error('Erro ao carregar quantidade:', err))

    // Buscar operações por situação
    fetch(`/estatisticas/quantidade-operacoes-situacao?base=${baseId}`)
      .then(res => res.json())
      .then(data => setOperacoesPorSituacao(data || []))
      .catch(err => console.error('Erro ao carregar situações:', err))

    // Buscar média de valor por UF
    fetch(`/estatisticas/media-valor-uf?base=${baseId}`)
      .then(res => res.json())
      .then(data => {
        const formatted = (data || []).map(item => ({
          uf: item.uf,
          valor: parseFloat(item.mediaValor.toFixed(2))
        }))
        setMediaUf(formatted)
      })
      .catch(err => console.error('Erro ao carregar média por UF:', err))

    // Buscar média de valor por setor
    fetch(`/estatisticas/media-valor-setor?base=${baseId}`)
      .then(res => res.json())
      .then(data => {
        const formatted = (data || []).map(item => ({
          setorCnae: item.setorCnae,
          mediaValor: parseFloat(item.mediaValor.toFixed(2))
        }))
        setMediaSetor(formatted)
      })
      .catch(err => console.error('Erro ao carregar média por setor:', err))

    // Buscar média de valor por porte
    fetch(`/estatisticas/media-valor-porte?base=${baseId}`)
      .then(res => res.json())
      .then(data => {
        const formatted = (data || []).map(item => ({
          porteDoCliente: item.porteDoCliente,
          mediaValor: parseFloat(item.mediaValor.toFixed(2))
        }))
        setMediaPorte(formatted)
      })
      .catch(err => console.error('Erro ao carregar média por porte:', err))

    // Buscar top clientes
    fetch(`/estatisticas/top-clientes?base=${baseId}`)
      .then(res => res.json())
      .then(data => {
        const formatted = (data || []).map(item => ({
          nome: item.cliente,
          valor: item.totalDesembolsado
        }))
        setTopClientes(formatted)
      })
      .catch(err => console.error('Erro ao carregar top clientes:', err))

    // Buscar quantidade de operações por UF
    fetch(`/estatisticas/quantidade-operacoes-uf?base=${baseId}`)
      .then(res => res.json())
      .then(data => setQuantidadeUf(data || []))
      .catch(err => console.error('Erro ao carregar quantidade por UF:', err))
      .finally(() => setLoading(false))
  }, [id])

  const situacaoAtivas = operacoesPorSituacao.find(
    s => s.situacaoDaOperacao?.toUpperCase() === 'ATIVA'
  )
  const situacaoLiquidadas = operacoesPorSituacao.find(
    s => s.situacaoDaOperacao?.toUpperCase() === 'LIQUIDADA'
  )

  return (
    <div className="container">
      <div className="analysis-header">
        <button className="back-btn" onClick={() => navigate('/')}>
          Tela de Upload
        </button>
      </div>
      
      <div className="analysis-title-container">

        <h1 className="analysis-title">
          Tela de Análise: Base {id}
        </h1>
      </div>

      <div className="analysis-layout">
        <div className="analysis-main">
          <div className="top-grid">
            <div className="big-card card">
              <span>NÚMERO OPERAÇÕES</span>

              <h1>{loading ? '-' : quantidadeOperacoes}</h1>
            </div>

            <div className="side-stats">
              <StatsCard
                title="ATIVAS"
                value={loading ? '-' : situacaoAtivas?.quantidade || 0}
              />

              <StatsCard
                title="LIQUIDADAS"
                value={loading ? '-' : situacaoLiquidadas?.quantidade || 0}
              />
            </div>
          </div>

          <MediaUfChart data={mediaUf} />

          <MediaSetorChart data={mediaSetor} />

          <MediaPorteChart data={mediaPorte} />

          <TopClientsChart data={topClientes} />
        </div>

        <StatesList data={quantidadeUf} />
      </div>
    </div>
  )
}

export default AnalysisPage