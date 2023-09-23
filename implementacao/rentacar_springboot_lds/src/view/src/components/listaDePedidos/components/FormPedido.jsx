import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import dayjs from 'dayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useCallback, useState } from "react";
import PropTypes from 'prop-types'

import { useApi } from "../../../api/axiosInstance";

const defaultTheme = createTheme();

const FormPedido = ({onAtualizar, fecharDialog }) => {

  const [formData, setFormData] = useState({
    veiculo_matricula: "",
    data_inicio: dayjs('2023-09-25'),
    data_final: dayjs('2023-09-25')
  });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = useCallback(
    async (e) => {
      e.preventDefault();
      const dataInicial = new Date(formData.data_inicio).toISOString().split('T')[0];
      const dataFinal = new Date(formData.data_final).toISOString().split('T')[0];

      try {
        const usuario = JSON.parse(localStorage.getItem("usuario"))
        await useApi.post("/pedidos", {data_inicio: dataInicial, data_final: dataFinal, veiculo_matricula: formData.veiculo_matricula, status: "EM_ANALISE", cliente_cpf: usuario.data.cpf});
      } catch (error) {
        console.error("Erro:", error);
      } finally {
        fecharDialog()
        onAtualizar()
        window.location.reload();
      }
    },
    [fecharDialog, formData.data_final, formData.data_inicio, formData.veiculo_matricula, onAtualizar]
  );

  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: "flex",
            flexDirection: "column",
            alignItems: "center",
          }}
        >
          <Typography component="h1" variant="h2">
            Alugar
          </Typography>
          <Box
            component="form"
            onSubmit={handleSubmit}
            noValidate
            sx={{ mt: 1 }}
          >
            <TextField
              margin="normal"
              required
              fullWidth
              label="Matricula do Veiculo"
              name="veiculo_matricula"
              value={formData.veiculo_matricula}
              autoFocus
              onChange={handleChange}
            />
            <LocalizationProvider dateAdapter={AdapterDayjs}>
              <DemoContainer components={['DatePicker', 'DatePicker']}>
                <DatePicker 
                views={['day', 'month', 'year']}
                inputFormat="DD/MM/YYYY"
                label="Data inicial" 
                value={formData.data_inicio} 
                onChange={handleChange}/>
                <DatePicker
                  label="Data final"
                  value={formData.data_final}
                  onChange={handleChange}
                />
              </DemoContainer>
            </LocalizationProvider>

            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              ALUGAR
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};
FormPedido.propTypes = {
  onAtualizar: PropTypes.func,
  fecharDialog: PropTypes.func
}

export default FormPedido;
