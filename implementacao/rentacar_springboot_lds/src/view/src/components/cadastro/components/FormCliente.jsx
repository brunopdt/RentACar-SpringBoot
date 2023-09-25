import { useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import { useApi } from "../../../api/axiosInstance";


const FormCliente = () => {
  const [formData, setFormData] = useState({
    nome: "",
    rg: "",
    cpf: "",
    endereco: "",
    profissao: "",
    entidadeEmpregadora: "",
    rendimento1: "",
    rendimento2: "",
    rendimento3: "",
    login: "",
    senha: "",
  });

  const navigate = useNavigate();

  const handleChange = useCallback((e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }, []);

  const handleSubmit =
    async (e) => {
      e.preventDefault();
      console.log(formData);

      const rendimentos = [formData.rendimento1];

      if (formData.rendimento2 !== "") {
        rendimentos.push(formData.rendimento2);
      }

      if (formData.rendimento3 !== "") {
        rendimentos.push(formData.rendimento3);
      }

      try {
        const resposta = await useApi.post("/usuarios", {
          ...formData, rendimentos
        });
        console.log(resposta);
        navigate("/login");
      } catch (error) {
        console.log("erro", error);
      }
    }


  return (
    <Container onSubmit={handleSubmit} component="form" maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Box sx={{ mt: 1 }} noValidate>
          <TextField
            margin="normal"
            required
            fullWidth
            name="nome"
            label="Nome"
            value={formData.nome}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="rg"
            label="Rg"
            value={formData.rg}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="cpf"
            label="CPF"
            value={formData.cpf}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="profissao"
            label="Profissão"
            value={formData.profissao}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="endereco"
            label="Endereço"
            value={formData.endereco}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="entidadeEmpregadora"
            label="Entidade Empregadora"
            value={formData.entidadeEmpregadora}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="rendimento1"
            label="1º Rendimento"
            type="number"
            value={formData.rendimento1}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            fullWidth
            name="rendimento2"
            label="2º Rendimento"
            type="number"
            value={formData.rendimento2}
            onChange={handleChange}
          />
          <TextField
            margin="normal"
            fullWidth
            name="rendimento3"
            label="3º Rendimento"
            type="number"
            value={formData.rendimento3}
            onChange={handleChange}
          />
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
  );
};

export default FormCliente;