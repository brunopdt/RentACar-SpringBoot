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
import org.springframework.web.server.ResponseStatusException;

import lds.rentacar_springboot_lds.models.Agente;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AgenteRepository agenteRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DadosUsuario login) throws Exception {
        Optional<Usuario> existingUser = usuarioRepository.findById(login.login());
        if (existingUser.isPresent() && existingUser.get().getsenha().equals(login.senha())) {
            /*Optional<Cliente> existingClient = clienteRepository.findByUsuarioLogin(login.login());
            Optional<Agente> existingAgent = agenteRepository.findByUsuarioLogin(login.login());
            if (existingClient.isPresent()) {
                return ResponseEntity.ok(existingClient.get());
            } else if (existingAgent.isPresent()) {
                return ResponseEntity.ok(existingAgent.get());
            } else {
                throw new Exception("Usuário não encontrado");

            
            }*/
            return ResponseEntity.ok(existingUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
