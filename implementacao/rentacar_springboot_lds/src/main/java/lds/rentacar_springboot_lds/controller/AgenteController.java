package lds.rentacar_springboot_lds.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lds.rentacar_springboot_lds.models.Agente;
import lds.rentacar_springboot_lds.models.Usuario;
import lds.rentacar_springboot_lds.repositories.AgenteRepository;
import lds.rentacar_springboot_lds.repositories.UsuarioRepository;
import lds.rentacar_springboot_lds.services.DadosCadastroAgente;
import lds.rentacar_springboot_lds.services.DadosUsuario;

@RestController
@RequestMapping("agentes")
@CrossOrigin(origins = "http://localhost:5173")
public class AgenteController {

  @Autowired
  private UsuarioRepository _repositoryUsuario;

  @Autowired
  private AgenteRepository _repository;

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "Hello World!";
  }

  @PostMapping
  @Transactional
  public void cadastrar(@RequestBody DadosCadastroAgente dados) {
    if (_repositoryUsuario.findById(dados.login()).isPresent() || _repository.findById(dados.cnpj()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe");
    }
    Usuario usuario = new Usuario(new DadosUsuario(dados.login(), dados.senha()));
    _repositoryUsuario.save(usuario);

    Agente agente = new Agente(dados, usuario.getLogin());
    _repository.save(agente);

    ResponseEntity.created(null);
  }

  @GetMapping
  public ResponseEntity<List<Agente>> getAll() throws Exception {
    return ResponseEntity.ok(_repository.findAll());
  }

  @DeleteMapping("/{cnpj}")
  @Transactional
  public void deleteFromCnpj(@PathVariable String cnpj) {
    Agente agente = _repository.findById(cnpj)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agente não encontrado"));
    _repositoryUsuario.deleteById(agente.getUsuariologin());
    _repository.deleteById(cnpj);
  }

  @PutMapping
  public void update(@RequestBody DadosCadastroAgente agente) {
    Optional<Agente> agenteEncontrado = this._repository.findById(agente.cnpj());
    if (agenteEncontrado.isPresent()) {
      Agente agenteEncontradoClass = agenteEncontrado.get();
      Agente agenteAtualizado = agenteEncontradoClass.updateCliente(agente);
      this._repository.save(agenteAtualizado);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }
  }

}
