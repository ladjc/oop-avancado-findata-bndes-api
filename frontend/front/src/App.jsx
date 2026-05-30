import { BrowserRouter, Routes, Route, useLocation, useNavigate } from 'react-router-dom'
import { FaGithub } from 'react-icons/fa'
import UploadPage from './pages/UploadPage'
import AnalysisPage from './pages/AnalysisPage'

const NAV = [
  { path: '/', label: 'Bases de Dados', icon: '📁' },
  {
    path: 'https://github.com/ladjc/oop-avancado-findata-bndes-api',
    label: 'Repositório',
    icon: <FaGithub />,
  },
]

function Sidebar() {
  const location = useLocation()
  const navigate = useNavigate()

  return (
    <aside className="sidebar">
      <div className="sidebar-logo">
        <h1>FinData BNDES</h1>
        <p>Análise de Financiamentos</p>
      </div>

      <nav className="sidebar-nav">
        {NAV.map(item => {
          const isExternal = item.path.startsWith('http')
          const isActive = !isExternal && location.pathname === item.path

          return isExternal ? (
            <a
              key={item.path}
              href={item.path}
              className={`nav-item${isActive ? ' active' : ''}`}
              target="_blank"
              rel="noopener noreferrer"
            >
              <span>{item.icon}</span>
              {item.label}
            </a>
          ) : (
            <button
              key={item.path}
              className={`nav-item${isActive ? ' active' : ''}`}
              onClick={() => navigate(item.path)}
            >
              <span>{item.icon}</span>
              {item.label}
            </button>
          )
        })}
      </nav>

      <div className="sidebar-footer">
        <p>
          FATEC · POO Avançada
          <br />
          Dados Abertos BNDES
        </p>
      </div>
    </aside>
  )
}
function Layout({ children }) {
  return (
    <div className="app-layout">
      <Sidebar />
      <main className="main">{children}</main>
    </div>
  )
}

export default function App() {
  return (
    <BrowserRouter>
      <Layout>
        <Routes>
          <Route path="/" element={<UploadPage />} />
          <Route path="/analysis/:id" element={<AnalysisPage />} />
        </Routes>
      </Layout>
    </BrowserRouter>
  )
}
