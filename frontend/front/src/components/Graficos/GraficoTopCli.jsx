import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, Cell } from 'recharts'

const fmt = v => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL', notation: 'compact', maximumFractionDigits: 1 }).format(v)
const fmtFull = v => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(v)

const CustomTooltip = ({ active, payload }) => {
  if (!active || !payload?.length) return null
  const d = payload[0].payload
  return (
    <div style={{ background: '#fff', border: '1px solid #e4e2db', borderRadius: 8, padding: '10px 14px', boxShadow: '0 4px 16px rgba(0,0,0,.1)', maxWidth: 280 }}>
      <p style={{ fontWeight: 600, fontSize: 13, marginBottom: 4 }}>{d.nome}</p>
      <p style={{ fontSize: 13, color: '#1a4a2e' }}>{fmtFull(d.valor)}</p>
    </div>
  )
}

export default function GraficoTopCli({ data = [] }) {
  const top10 = [...data].sort((a, b) => b.valor - a.valor).slice(0, 10)
  const height = Math.max(260, top10.length * 38)

  return (
    <div className="chart-card card">
      <div className="chart-title">Top 10 clientes por valor total desembolsado</div>
      <ResponsiveContainer width="100%" height={height}>
        <BarChart
          data={top10}
          layout="vertical"
          margin={{ top: 0, right: 20, left: 8, bottom: 0 }}
        >
          <XAxis type="number" tickFormatter={fmt} tick={{ fontSize: 11, fill: '#9b9890' }} axisLine={false} tickLine={false} />
          <YAxis
            dataKey="nome"
            type="category"
            tick={{ fontSize: 12, fill: '#6b6860' }}
            axisLine={false} tickLine={false}
            width={200}
            tickFormatter={v => {
              if (!v) return ''
              const words = v.trim().split(/\s+/)
              const label = words.slice(0, 3).join(' ')
              return label.length > 26 ? label.slice(0, 26) + '…' : label
            }}
          />
          <Tooltip content={<CustomTooltip />} cursor={{ fill: 'rgba(26,74,46,.06)' }} />
          <Bar dataKey="valor" radius={[0, 4, 4, 0]}>
            {top10.map((_, i) => (
              <Cell key={i} fill={i === 0 ? '#1a4a2e' : i === 1 ? '#2d7a4f' : i < 5 ? '#5fa87a' : '#a8c8b5'} />
            ))}
          </Bar>
        </BarChart>
      </ResponsiveContainer>
    </div>
  )
}
