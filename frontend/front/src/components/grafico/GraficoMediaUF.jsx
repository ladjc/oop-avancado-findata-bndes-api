import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, Cell } from 'recharts'

const fmt = v => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL', notation: 'compact', maximumFractionDigits: 1 }).format(v)
const fmtFull = v => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(v)

const CustomTooltip = ({ active, payload, label }) => {
  if (!active || !payload?.length) return null
  return (
    <div style={{ background: '#fff', border: '1px solid #e4e2db', borderRadius: 8, padding: '10px 14px', boxShadow: '0 4px 16px rgba(0,0,0,.1)' }}>
      <p style={{ fontWeight: 600, fontSize: 13, marginBottom: 4 }}>{label}</p>
      <p style={{ fontSize: 13, color: '#1a4a2e' }}>{fmtFull(payload[0].value)}</p>
    </div>
  )
}

export default function GraficoMediaUF({ data = [] }) {
  const sorted = [...data].sort((a, b) => b.valor - a.valor)
  return (
    <div className="chart-card card">
      <div className="chart-title">Média de valor desembolsado por UF</div>
      <ResponsiveContainer width="100%" height={300}>
        <BarChart data={sorted} margin={{ top: 10, right: 20, left: 10, bottom: 20 }}>
          <XAxis dataKey="uf" tick={{ fontSize: 11, fill: '#9b9890' }} axisLine={false} tickLine={false} />
          <YAxis tickFormatter={fmt} tick={{ fontSize: 11, fill: '#9b9890' }} axisLine={false} tickLine={false} width={70} />
          <Tooltip content={<CustomTooltip />} cursor={{ fill: 'rgba(26,74,46,.06)' }} />
          <Bar dataKey="valor" radius={[4, 4, 0, 0]}>
            {sorted.map((_, i) => (
              <Cell key={i} fill={i === 0 ? '#1a4a2e' : i < 5 ? '#2d7a4f' : '#a8c8b5'} />
            ))}
          </Bar>
        </BarChart>
      </ResponsiveContainer>
    </div>
  )
}
