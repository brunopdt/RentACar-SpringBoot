import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Cadastro } from './components/cadastro'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/cadastro" element={<Cadastro />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
