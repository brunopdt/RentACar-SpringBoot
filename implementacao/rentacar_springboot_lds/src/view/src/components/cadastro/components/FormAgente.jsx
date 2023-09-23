import React, { useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { FormLabel, MenuItem, Radio, RadioGroup, Select } from "@mui/material";
import { useApi } from "../../../api/axiosInstance";

const defaultTheme = createTheme();

export const FormAgente = () => {
  const [formData, setFormData] = useState({
    cnpj: "",
    nomeFantasia: "",
    tipo: "Agente",
    login: "",
    senha: "",
  });

  const navigate = useNavigate();

  const handleSubmit = useCallback(
    async (e) => {
      e.preventDefault();
      try {
        const resposta = await useApi.post("/agentes", formData);
        console.log(resposta);
        localStorage.setItem("agente", JSON.stringify(resposta));
        navigate("/login");
      } catch (error) {
        console.error("Erro:", error);
      }
    },
    [formData, navigate]
  );

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  };

  const handleTipoChange = (e) => {
    const { value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      tipo: value,
    }));
  };

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
          <Box sx={{ mt: 1 }} component="form" noValidate onSubmit={handleSubmit}>
            <TextField
              margin="normal"
              required
              fullWidth
              label="Nome de Usuário"
              name="login"
              value={formData.login}
              onChange={handleChange}
              autoFocus
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="senha"
              label="Senha"
              type="password"
              value={formData.senha}
              onChange={handleChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="nomeFantasia"
              label="Nome Fantasia"
              type="text"
              value={formData.nomeFantasia}
              onChange={handleChange}
            />
            <TextField
              margin="normal"
              required
              fullWidth
              name="cnpj"
              label="CNPJ"
              type="text"
              value={formData.cnpj}
              onChange={handleChange}
            />
            <Box sx={{ display: "flex", alignItems: "center" }}>
              <FormLabel>Tipo</FormLabel>
              <RadioGroup
                name="tipo"
                value={formData.tipo}
                onChange={handleTipoChange}
              >
                <Radio value="Agente">Agente</Radio>
                <Radio value="Banco">Banco</Radio>
              </RadioGroup>
              <Select
                value={formData.tipo}
                onChange={handleTipoChange}
                label="Tipo"
                sx={{ ml: 2 }}
              >
                <MenuItem value="Agente">Agente</MenuItem>
                <MenuItem value="Banco">Banco</MenuItem>
              </Select>
            </Box>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              CADASTRAR
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
};

export default FormAgente;