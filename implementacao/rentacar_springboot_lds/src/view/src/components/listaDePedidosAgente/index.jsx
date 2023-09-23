import { PedidoCard } from '../listaDePedidos/components/card'
import { Box, Button, Dialog, Grid } from '@mui/material'
import { useApi } from '../../api/axiosInstance'
import { useEffect, useState } from 'react'

export const ListaDePedidosAgente = () => {
  const [pedidos, setPedidos] = useState([])
  const [openDialog, setOpendialog] = useState(true)

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
        <PedidoCard status="INFO" titulo="Não há pedidos pendentes" onClick={() => setOpendialog(true)}/>
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
        onClick={() => setOpendialog(true)}
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
        <Dialog open={openDialog} onClose={() => setOpendialog(false)}>
          <Box sx={{ margin: 5, display: 'flex', flexDirection: "column", gap: '1rem' }}>
            <Button variant='contained'>APROVAR</Button>
            <Button variant='contained'>REPROVAR</Button>
          </Box>
        </Dialog>
        {renderPedidos()}
      </Box >
    </>
  )
}
