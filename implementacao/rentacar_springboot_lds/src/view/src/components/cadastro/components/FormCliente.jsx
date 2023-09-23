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
 
  const handleChange = (e) => { 
    const { name, value } = e.target; 
    setFormData((prevFormData) => ({ 
      ...prevFormData, 
      [name]: value, 
    })); 
  }; 
 
  const handleSubmit = useCallback( 
    async (e) => { 
      e.preventDefault(); 
      try { 
        const resposta = await useApi.post("/usuarios", formData); 
        console.log(resposta); 
        localStorage.setItem("usuario", JSON.stringify(resposta)); 
        navigate("/login"); 
      } catch (error) { 
        console.log("erro", error); 
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
          <Box sx={{ mt: 1 }} component="form" noValidate> 
            <TextField 
              margin="normal" 
              required 
              fullWidth 
              name="nome" 
              label="Nome" 
              type="text" 
              value={formData.nome} 
              onChange={handleChange} 
            /> 
            <TextField 
              margin="normal" 
              required 
              fullWidth 
              name="rg" 
              label="Rg" 
              type="text" 
              value={formData.rg} 
              onChange={handleChange} 
            /> 
            <TextField 
              margin="normal" 
              required 
              fullWidth 
              name="cpf" 
              label="CPF" 
              type="text" 
              value={formData.cpf} 
              onChange={handleChange} 
            /> 
            <TextField 
              margin="normal" 
              required 
              fullWidth 
              name="entidadeEmpregadora" 
              label="Entidade Empregadora" 
              type="text" 
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
              required 
              fullWidth 
              name="rendimento2" 
              label="2º Rendimento" 
              type="number" 
              value={formData.rendimento2} 
              onChange={handleChange} 
            /> 
            <TextField 
              margin="normal" 
              required 
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
    </ThemeProvider> 
  ); 
}; 
 
export default FormCliente;