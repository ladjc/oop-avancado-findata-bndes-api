import { PieChart, Pie, Cell, Tooltip, ResponsiveContainer, Legend } from 'recharts'

const COLORS = ['#1a4a2e', '#2d7a4f', '#4a9d6f']

const fmtPercent = (value) => {
  return (value).toFixed(1) + '%'
}

const CustomTooltip = ({ active, payload }) => {
  if (!active || !payload?.length) return null
  return (
    <div style={{ background: '#fff', border: '1px solid #e4e2db', borderRadius: 8, padding: '10px 14px', boxShadow: '0 4px 16px rgba(0,0,0,.1)' }}>
      <p style={{ fontWeight: 600, fontSize: 12, marginBottom: 4 }}>{payload[0].name}</p>
      <p style={{ fontSize: 13, color: '#1a4a2e' }}>{fmtPercent(payload[0].value)}</p>
    </div>
  )
}

export default function GraficoParticipacaoUF({ data = [] }) {
  const sorted = [...data].sort((a, b) => b.percentual - a.percentual)
  
  // Top 10 + Outros
  const top10 = sorted.slice(0, 10)
  const others = sorted.slice(10)
  const otherPercentual = others.reduce((sum, item) => sum + item.percentual, 0)
  
  const chartData = otherPercentual > 0 
    ? [...top10, { uf: 'Outros', percentual: otherPercentual }]
    : top10
  
  return (
    <div className="chart-card card">
      <div className="chart-title">Participação por UF</div>
      <ResponsiveContainer width="100%" height={500}>
        <PieChart>
          <Pie
            data={chartData}
            cx="50%"
            cy="50%"
            labelLine={false}
            label={({ uf, percentual }) => `${uf} ${fmtPercent(percentual)}`}
            outerRadius={200}
            fill="#1a4a2e"
            dataKey="percentual"
          >
            {chartData.map((_, i) => (
              <Cell key={`cell-${i}`} fill={COLORS[i % COLORS.length]} />
            ))}
          </Pie>
          <Tooltip content={<CustomTooltip />} />
        </PieChart>
      </ResponsiveContainer>
    </div>
  )
}

