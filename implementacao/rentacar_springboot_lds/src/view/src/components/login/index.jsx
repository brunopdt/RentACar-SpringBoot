import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { useCallback, useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { useApi } from "../../api/axiosInstance";
import { Dialog } from "@mui/material";

const Login = () => {
  const [formData, setFormData] = useState({
    login: "",
    senha: "",
  });
  const [openDialog, setOpendialog] = useState(false)

  const navigate = useNavigate();

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
      try {
        const resposta = await useApi.post("/usuarios/login", formData);
        console.log(resposta);
        localStorage.setItem("usuario", JSON.stringify(resposta));
        navigate("/listaPedidos");
      } catch (error) {
        console.error("Erro:", error);
        setOpendialog(true)
      }
    },
    [formData, navigate]
  );

  return (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Dialog className="dialog" open={openDialog} onClose={() => setOpendialog(false)}>
            <Box sx={{height: "200px", width: "200px", display: "flex",flexDirection: "column", alignItems: "center", justifyContent: "center", textAlign: "center"}}>
            <Typography sx={{color: "red", fontSize: 30}}>LOGIN INVÁLIDO</Typography>
            <Typography>Revise se as informações estão corretas</Typography>
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
          <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}></Avatar>
          <Typography component="h1" variant="h2">
            Sign in
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
              label="Nome de Usuário"
              name="login"
              value={formData.login}
              autoFocus
              onChange={handleChange}
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
              LOGAR
            </Button>

            <Button
              component={Link}
              to="/cadastro"
              type="button"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Cadastrar
            </Button>
          </Box>
        </Box>
      </Container>
  );
};

export default Login;
