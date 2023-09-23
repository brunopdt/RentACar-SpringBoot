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
import FormCliente from "./components/FormCliente";
import FormAgente from "./components/FormAgente";
import { Link } from "react-router-dom";

export const Cadastro = () => {
  const [tipoCliente, setTipoCliente] = useState("individual");

  const handleTipoClienteChange = (event) => {
    setTipoCliente(event.target.value);
  };

  return (
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
          <Typography component="h1" variant="h3">
            Sign Up
          </Typography>
        </Box>
        <Box
          sx={{
            mt: 1,
            display: "flex",
            marginTop: 3,
            flexDirection: "column",
            alignItems: "center",
            justifyContent: "center",
          }}
          component="form"
          noValidate
        >
          <Typography component="h1" variant="h5" align="center">
            Cadastro
          </Typography>
          <label>Tipo de Cliente:</label>
          <select value={tipoCliente} onChange={handleTipoClienteChange}>
            <option value="individual">Cliente </option>
            <option value="empresarial"> Agente </option>
          </select>
          {tipoCliente === "individual" ? <FormCliente /> : <FormAgente />}
          <Button
              component={Link}
              to="/login"
              type="button"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Login
            </Button>
        </Box>
      </Container>
  );
};

export default Cadastro;
