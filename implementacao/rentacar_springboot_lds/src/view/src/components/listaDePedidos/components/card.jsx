import Card from '@mui/material/Card'
import CardContent from '@mui/material/CardContent'
import CardMedia from '@mui/material/CardMedia'
import Typography from '@mui/material/Typography'
import { CardActionArea } from '@mui/material'
import ok from '../../../assets/ok.jpg'
import pending from '../../../assets/pending.jpg'
import reproved from '../../../assets/reproved.jpg'
import info from '../../../assets/info.jpg'

import PropTypes from 'prop-types'

export const PedidoCard = props => {
  const imgSource = {
    EM_ANALISE: pending,
    APROVADO: ok,
    REPROVADO: reproved,
    INFO: info
  }

  return (
    <Card sx={{ width: 400 }}>
      <CardActionArea>
        <CardMedia
          component="img"
          height="140"
          image={imgSource[props.status]}
          alt="green iguana"
        />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div">
            {props.titulo}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {props.data_inicio} - {props.data_fim}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {props.status}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            {props.veiculo_matricula}
          </Typography>
        </CardContent>
      </CardActionArea>
    </Card>
  )
}

PedidoCard.propTypes = {
  titulo: PropTypes.string.isRequired,
  data_inicio: PropTypes.string,
  data_fim: PropTypes.string,
  status: PropTypes.string.isRequired,
  veiculo_matricula: PropTypes.string
}
