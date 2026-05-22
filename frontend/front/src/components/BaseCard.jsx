import { FiFileText, FiSearch, FiTrash2 } from 'react-icons/fi'

function BaseCard({ title, date, status = '', quantidade, onAnalysis, onDelete }) {
  return (
    <div className="base-card card">
      <div className="base-top">
        <div>
          <h3>
            <FiFileText />
            {title}
          </h3>

          <p>Importado em {date}</p>
          <p className="operacoes">Operações: {quantidade ?? '-'}</p>
        </div>

        <span className={`status ${status.toLowerCase()}`}>
          {status}
        </span>
      </div>

      <div className="base-actions">
        <button className="analysis-btn" onClick={onAnalysis}>
          <FiSearch />
          Análise
        </button>

        <button className="delete-btn" onClick={onDelete}>
          <FiTrash2 />
        </button>
      </div>
    </div>
  )
}

export default BaseCard