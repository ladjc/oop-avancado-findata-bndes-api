import { FiUploadCloud } from 'react-icons/fi'

function AreaUpload() {
  return (
    <div className="upload-area">
      <FiUploadCloud size={60} />

      <h2>Arraste o CSV aqui ou clique para selecionar</h2>

      <p>Apenas arquivos .csv</p>
    </div>
  )
}

export default AreaUpload