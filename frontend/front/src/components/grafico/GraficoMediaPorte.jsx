import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer, Cell } from 'recharts'

const fmt = v => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL', notation: 'compact', maximumFractionDigits: 1 }).format(v)
const fmtFull = v => new Intl.NumberFormat('pt-BR', { style: 'currency', currency: 'BRL' }).format(v)

const COLORS = { 'Microempresa': '#a8c8b5', 'Pequena Empresa': '#5fa87a', 'Média Empresa': '#2d7a4f', 'Grande Empresa': '#1a4a2e' }

const CustomTooltip = ({ active, payload, label }) => {
  if (!active || !payload?.length) return null
  return (
    <div style={{ background: '#fff', border: '1px solid #e4e2db', borderRadius: 8, padding: '10px 14px', boxShadow: '0 4px 16px rgba(0,0,0,.1)' }}>
      <p style={{ fontWeight: 600, fontSize: 13, marginBottom: 4 }}>{label}</p>
      <p style={{ fontSize: 13, color: '#1a4a2e' }}>{fmtFull(payload[0].value)}</p>
    </div>
  )
}

export default function GraficoMediaPorte({ data = [] }) {
  const sorted = [...data].sort((a, b) => b.mediaValor - a.mediaValor)
  return (
    <div className="chart-card card">
      <div className="chart-title">Média de valor por porte do cliente</div>
      <ResponsiveContainer width="100%" height={260}>
        <BarChart data={sorted} margin={{ top: 10, right: 20, left: 10, bottom: 20 }}>
          <XAxis
            dataKey="porteDoCliente"
            tick={{ fontSize: 12, fill: '#6b6860' }}
            axisLine={false} tickLine={false}
            tickFormatter={v => v?.length > 14 ? v.slice(0, 14) + '…' : v}
          />
          <YAxis tickFormatter={fmt} tick={{ fontSize: 11, fill: '#9b9890' }} axisLine={false} tickLine={false} width={70} />
          <Tooltip content={<CustomTooltip />} cursor={{ fill: 'rgba(26,74,46,.06)' }} />
          <Bar dataKey="mediaValor" radius={[4, 4, 0, 0]}>
            {sorted.map((entry, i) => (
              <Cell key={i} fill={COLORS[entry.porteDoCliente] || '#2d7a4f'} />
            ))}
          </Bar>
        </BarChart>
      </ResponsiveContainer>
    </div>
  )
}
