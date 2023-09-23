import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Cadastro } from './components/cadastro'
import Login  from './components/login'
import ListaDePedidos from './components/listaDePedidos'

function App() {
  return (
    <BrowserRouter>
      <Routes>
      <Route path="/login" element={<Login />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/listaPedidos" element={<ListaDePedidos />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
