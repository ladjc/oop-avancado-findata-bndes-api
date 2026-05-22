function StatsCard({ title, value }) {
  return (
    <div className="stats-card card">
      <span>{title}</span>
      <h2>{value}</h2>
    </div>
  )
}

export default StatsCard