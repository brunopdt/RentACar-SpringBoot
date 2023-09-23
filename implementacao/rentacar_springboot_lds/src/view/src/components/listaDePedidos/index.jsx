import { PedidoCard } from './components/card'
import { Box, Button, Dialog } from '@mui/material'
import { useApi } from '../../api/axiosInstance'
import { useEffect, useState } from 'react'
import FormPedido from './components/FormPedido'

export const ListaDePedidos = () => {
  const [pedidos, setPedidos] = useState([])
  const [openDialog, setOpendialog] = useState(false)

  const loadPedidos = async () => {
    const resposta = await useApi.get('/pedidos')
    setPedidos(resposta.data)
  }

  useEffect(() => {
    loadPedidos()
  }, [])

  const renderPedidos = () => {
    if (pedidos.length === 0) {
      return (
        <PedidoCard status="INFO" titulo="Você ainda não tem pedidos abertos" />
      )
    }

    return pedidos.map((pedido) => (
      <PedidoCard
        key={pedido.idPedido}
        data_inicio={pedido.data_inicio}
        data_fim={pedido.data_fim}
        status={pedido.status}
        titulo={'Pedido ' + pedido.idPedido}
        veiculo_matricula={pedido.veiculo_matricula}
      />
    ));

  }

  return (
    <>
      <Box
        sx={{
          width: '100%',
          display: 'flex',
          alignItems: 'center',
          flexDirection: 'column',
          gap: '2rem',
          margin: 2
        }}
      >
        <Button variant='filled' sx={{
          bgcolor: "#629662", fontWeight: "bold", color: "white", '&:hover': {
            backgroundColor: '#436343',
          },
        }} onClick={() => setOpendialog(true)}>Criar um novo pedido</Button>
        <Dialog open={openDialog} onClose={() => setOpendialog(false)}>
          <Box sx={{ margin: 5 }}>
            <FormPedido onAtualizar={renderPedidos} fecharDialog={() => setOpendialog(false)}/>
          </Box>
        </Dialog>
        {renderPedidos()}
      </Box >
    </>
  )
}
