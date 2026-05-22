import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from 'recharts'

function TopClientsChart({ data = [] }) {
  const currencyFormatter = new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  })

  const formatCurrency = (value) => currencyFormatter.format(Number(value) || 0)

  const top10 = (Array.isArray(data) ? data.slice() : [])
    .map(item => ({ ...item, valor: Number(item.valor) || 0 }))
    .sort((a, b) => b.valor - a.valor)
    .slice(0, 10)

  return (
    <div className="chart-container card">
      <h2>Gráfico Top 10 Clientes</h2>

      <ResponsiveContainer width="100%" height={350}>
        <BarChart layout="vertical" data={top10}>
          <XAxis type="number" tickFormatter={formatCurrency} />

          <YAxis
            dataKey="nome"
            type="category"
            tickFormatter={(value) =>
              typeof value === 'string'
                ? value.trim().split(/\s+/)[0]
                : value
            }
          />

          <Tooltip formatter={(value) => formatCurrency(value)} />

          <Bar dataKey="valor" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  )
}

export default TopClientsChart