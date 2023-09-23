import React, { useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import { useApi } from "../../api/axiosInstance";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { MenuItem, Select } from "@mui/material";

const defaultTheme = createTheme();

export const Cadastro = () => {
  const [formData, setFormData] = useState({
    cnpj: "",
    nomeFantasia: "",
    tipo: "",
    login: "",
    senha: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    console.log(name, value);
    console.log(formData);
  };

  const handleTipoChange = (e) => {
    const tipo = e.target.value;
    setFormData({
      ...formData,
      tipo,
    });
  };

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
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
          <Typography component="h1" variant="h2">
            Sign Up
          </Typography>
          <Box
            sx={{ mt: 1 }}
            component="form"
            onSubmit={handleSubmit}
            noValidate
          >
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
              <Typography variant="body1"></Typography>
              <TextField
                margin="normal"
                required
                fullWidth
                name="tipo"
                label="Tipo: Agente/Banco"
                type="text"
                value={formData.tipo}
                onChange={handleChange}
              />
              {/*<Select
                value={formData.tipo}
                onChange={handleTipoChange}
                label="Tipo"
                sx={{ ml: 2 }}
              >
                <MenuItem value="Agente">Agente</MenuItem>
                <MenuItem value="Banco">Banco</MenuItem>
        </Select>*/}
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

export default Cadastro;
