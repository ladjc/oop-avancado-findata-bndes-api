import { useState, useCallback, useEffect } from 'react'

let globalAddToast = null

export function ToastContainer() {
  const [toasts, setToasts] = useState([])

  globalAddToast = useCallback((msg, type = 'default') => {
    const id = Date.now()
    setToasts(prev => [...prev, { id, msg, type }])
    setTimeout(() => setToasts(prev => prev.filter(t => t.id !== id)), 3200)
  }, [])

  return (
    <div className="toast-container">
      {toasts.map(t => (
        <div key={t.id} className={`toast ${t.type}`}>
          {t.type === 'success' && '✓ '}
          {t.type === 'error' && '✕ '}
          {t.msg}
        </div>
      ))}
    </div>
  )
}

export function toast(msg, type = 'default') {
  globalAddToast?.(msg, type)
}
