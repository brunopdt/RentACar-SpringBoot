import { useState, useCallback } from "react";
import { useNavigate } from "react-router-dom";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import { FormLabel, MenuItem, RadioGroup, Select, Dialog, Typography } from "@mui/material";
import { useApi } from "../../../api/axiosInstance";

export const FormAgente = () => {
  const [formData, setFormData] = useState({
    cnpj: "",
    nomeFantasia: "",
    tipo: "EMPRESA",
    login: "",
    senha: "",
  });

  const [openDialog, setOpendialog] = useState(false)

  const navigate = useNavigate();

  const [dialogError, setDialogError] = useState("")

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await useApi.post("/agentes", formData);
      navigate("/");
    } catch (error) {
      setDialogError(error.response.data.message)
      setOpendialog(true)
    }
  }

  const handleChange = useCallback((e) => {
    const { name, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [name]: value,
    }));
  }, []);

  const handleTipoChange = useCallback((e) => {
    const { value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      tipo: value,
    }));
  }, []);

  return (
    <Container component="main">
      <Dialog className="dialog" open={openDialog} onClose={() => setOpendialog(false)}>
            <Box sx={{height: "200px", width: "200px", display: "flex",flexDirection: "column", alignItems: "center", justifyContent: "center", textAlign: "center"}}>
            <Typography sx={{color: "red", fontSize: 30}}>{dialogError}</Typography>
            </Box>
        </Dialog>
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Box sx={{ mt: 1 }} component="form" onSubmit={handleSubmit}>
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
            </RadioGroup>
            <Select
              value={formData.tipo}
              onChange={handleTipoChange}
              label="Tipo"
              sx={{ ml: 2 }}
            >
              <MenuItem value="EMPRESA">Empresa</MenuItem>
              <MenuItem value="BANCO">Banco</MenuItem>
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
  );
};

export default FormAgente;