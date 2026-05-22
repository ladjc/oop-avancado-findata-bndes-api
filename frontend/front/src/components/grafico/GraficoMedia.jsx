import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  ResponsiveContainer
} from 'recharts'

function MediaUfChart({ data = [] }) {
  const currencyFormatter = new Intl.NumberFormat('pt-BR', {
    style: 'currency',
    currency: 'BRL'
  })

  const formatCurrency = (value) => currencyFormatter.format(Number(value) || 0)

  const sortedData = (Array.isArray(data) ? data.slice() : [])
    .map(item => ({ ...item, valor: Number(item.valor) || 0 }))
    .sort((a, b) => b.valor - a.valor)

  return (
    <div className="chart-container card">
      <h2>Gráfico de Média de Valor por UF</h2>

      <ResponsiveContainer width="100%" height={350}>
        <BarChart data={sortedData} margin={{ top: 20, right: 30, left: 20, bottom: 40 }}>
          <XAxis dataKey="uf" label={{position: 'top', dy: -10 }} />
          <YAxis
            label={{angle: -90, position: 'insideLeft', dy: -10 }}
            tickFormatter={formatCurrency}
          />
          <Tooltip formatter={(value) => formatCurrency(value)} />

          <Bar dataKey="valor" />
        </BarChart>
      </ResponsiveContainer>
    </div>
  )
}

export default MediaUfChart