package lds.rentacar_springboot_lds.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lds.rentacar_springboot_lds.models.Agente;
import lds.rentacar_springboot_lds.models.Cliente;
import lds.rentacar_springboot_lds.models.Usuario;
import lds.rentacar_springboot_lds.repositories.AgenteRepository;
import lds.rentacar_springboot_lds.repositories.ClienteRepository;
import lds.rentacar_springboot_lds.repositories.UsuarioRepository;
import lds.rentacar_springboot_lds.services.DadosUsuario;

@RestController
@RequestMapping("usuarios/login")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AgenteRepository agenteRepository;

    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "Hello World!";
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody DadosUsuario login) throws Exception {
        Optional<Usuario> existingUser = usuarioRepository.findById(login.login());
        if (existingUser.isPresent() && existingUser.get().getsenha().equals(login.senha())) {
            Cliente existingClient = clienteRepository.findByusuariologin(login.login());
            Agente existingAgent = agenteRepository.findByusuariologin(login.login());
            if (existingClient != null) {
                return ResponseEntity.ok(existingClient);
            } else if (existingAgent != null) {
                return ResponseEntity.ok(existingAgent);
            } else {
                throw new Exception("Usuário não encontrado");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
