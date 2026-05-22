function StatesList({ data = [] }) {
  // Criar mapa de dados por UF para busca rápida
  const dataMap = data.reduce((map, item) => {
    map[item.uf] = item.quantidade
    return map
  }, {})

  // Obter lista única de estados
  const states = Object.keys(dataMap)

  return (
  <div className="states-list card">
    <h2>Operações por Estados</h2>

    <div className="states-scroll">

      <div className="state-header">
        <span>Estados</span>
        <strong>Operações</strong>
      </div>

      {states.map((state) => (
        <div key={state} className="state-item">

          <span>{state}</span>

          <strong>{dataMap[state] ?? 0}</strong>
        </div>
      ))}
    </div>
  </div>
)
}

export default StatesList