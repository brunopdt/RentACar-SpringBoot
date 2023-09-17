package lds.rentacar_springboot_lds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import lds.rentacar_springboot_lds.services.DadosRendimentoCliente;
import lds.rentacar_springboot_lds.models.Cliente;
import lds.rentacar_springboot_lds.models.Rendimento;
import lds.rentacar_springboot_lds.models.Usuario;
import lds.rentacar_springboot_lds.repositories.ClienteRepository;
import lds.rentacar_springboot_lds.repositories.RendimentoRepository;
import lds.rentacar_springboot_lds.repositories.UsuarioRepository;
import lds.rentacar_springboot_lds.services.DadosCadastroCliente;
import lds.rentacar_springboot_lds.services.DadosUsuario;

@RestController
@RequestMapping("usuarios")
public class ClienteController {

  @Autowired
  private ClienteRepository _repository;

  @Autowired
  private UsuarioRepository _repositoryUsuario;

  @Autowired
  private RendimentoRepository _repositoryRendimento;

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "Hello World!";
  }

  @PostMapping
  @Transactional
  public void cadastrar(@RequestBody DadosCadastroCliente dados) {
    if (_repositoryUsuario.findById(dados.login()).isPresent() || _repository.findById(dados.cpf()).isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe");
    }
    Usuario usuario = new Usuario(new DadosUsuario(dados.login(), dados.senha()));
    _repositoryUsuario.save(usuario);

    Cliente cliente = new Cliente(dados, usuario.getLogin());
    _repository.save(cliente);

    double[] rendimentos = dados.rendimentos();
    List<Rendimento> rendimentoList = new ArrayList<>();

    for (double rendimento : rendimentos) {
      Rendimento rendimentoObj = new Rendimento(new DadosRendimentoCliente(rendimento, cliente.getCpf()));
      rendimentoList.add(rendimentoObj);
    }

    _repositoryRendimento.saveAll(rendimentoList);

    ResponseEntity.created(null);
  }

  @GetMapping
  public ResponseEntity<List<Cliente>> getAll() throws Exception {
    return ResponseEntity.ok(_repository.findAll());
  }

  @DeleteMapping("/{cpf}")
  public void deleteFromCpf(@PathVariable String cpf) {
    _repository.findById(cpf)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
    _repository.deleteById(cpf);
  }

  @PutMapping
  public void update(@RequestBody DadosCadastroCliente cliente) {
    Optional<Cliente> clienteEncontrado = this._repository.findById(cliente.cpf());
    if (clienteEncontrado.isPresent()) {
      Cliente clienteEncontradoClass = clienteEncontrado.get();
      Cliente clienteAtualizado = clienteEncontradoClass.updateCliente(cliente);
      this._repository.save(clienteAtualizado);
    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
    }
  }

}
