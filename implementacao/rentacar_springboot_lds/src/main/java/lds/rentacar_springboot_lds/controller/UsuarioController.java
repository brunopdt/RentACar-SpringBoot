package lds.rentacar_springboot_lds.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lds.rentacar_springboot_lds.models.Cliente;
import lds.rentacar_springboot_lds.models.Usuario;
import lds.rentacar_springboot_lds.repositories.AgenteRepository;
import lds.rentacar_springboot_lds.repositories.ClienteRepository;
import lds.rentacar_springboot_lds.repositories.UsuarioRepository;
import lds.rentacar_springboot_lds.services.DadosUsuario;

@RestController
@RequestMapping("usuarios/login")

public class UsuarioController {
    @Autowired
    private ClienteRepository _repository;

    @Autowired
    private UsuarioRepository _repositoyUsuario;

    @Autowired
    private AgenteRepository _repositoryAgente;

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody DadosUsuario login) {
        Optional<Usuario> existingUser = _repositoyUsuario.findById(login.login());
        if (existingUser.isPresent() && existingUser.get().getSenha().equals(login.senha())) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas!");
        }
    }
}
