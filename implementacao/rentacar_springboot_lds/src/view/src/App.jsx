import { BrowserRouter, Route, Routes } from 'react-router-dom'
import { Cadastro } from './components/cadastro'
import Login from './components/login'
import { ListaDePedidos } from './components/listaDePedidos'
import { ListaDePedidosAgente } from './components/listaDePedidosAgente'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/cadastro" element={<Cadastro />} />
        <Route path="/listaPedidos" element={<ListaDePedidos />} />
        <Route path='/listaPedidosAgente' element={<ListaDePedidosAgente />}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
