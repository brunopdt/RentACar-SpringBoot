package lds.rentacar_springboot_lds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lds.rentacar_springboot_lds.models.Pedido;
import lds.rentacar_springboot_lds.repositories.AgenteRepository;
import lds.rentacar_springboot_lds.repositories.ClienteRepository;
import lds.rentacar_springboot_lds.repositories.PedidoRepository;
import lds.rentacar_springboot_lds.repositories.VeiculoRepository;
import lds.rentacar_springboot_lds.services.DadosCadastroPedido;
import lds.rentacar_springboot_lds.services.DadosEditPedido;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

  @Autowired
  private ClienteRepository _repositoryCliente;

  @Autowired
  private AgenteRepository _repositoryAgente;

  @Autowired
  private VeiculoRepository _repositoryVeiculo;

  @Autowired
  private PedidoRepository _repository;

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "Hello World!";
  }

  @PostMapping
  @Transactional
  public ResponseEntity<Void> cadastrar(@RequestBody DadosCadastroPedido dados) {
    if (_repositoryCliente.findById(dados.cliente_cpf()).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
    }

    if (_repositoryVeiculo.findById(dados.veiculo_matricula()).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
    }

    _repository.save(new Pedido(dados));
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{id}")
  public void editarStatus(@PathVariable String id, @RequestBody DadosEditPedido dados) {
    Pedido pedido = _repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Agente não encontrado"));

    if (_repositoryAgente.findById(dados.agente_cnpj()).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Agente não encontrado");
    }

    pedido.editarPedido(dados);
    _repository.save(pedido);
  }

  @GetMapping
  public ResponseEntity<List<Pedido>> listar() {
    return ResponseEntity.ok(_repository.findAll());
  }

}
