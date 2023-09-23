package lds.rentacar_springboot_lds.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lds.rentacar_springboot_lds.models.Veiculo;
import lds.rentacar_springboot_lds.repositories.VeiculoRepository;
import lds.rentacar_springboot_lds.services.DadosVeiculo;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin(origins = "http://localhost:5173")
public class VeiculoController {

  @Autowired
  private VeiculoRepository _repository;

  @GetMapping("/helloWorld")
  public String helloWorld() {
    return "Hello World!";
  }

  @PostMapping
  public void cadastrar(@RequestBody DadosVeiculo dados) {

    Veiculo veiculo = new Veiculo(dados);
    _repository.save(veiculo);

  }

  @GetMapping
  public ResponseEntity<List<Veiculo>> getAll() {
    return ResponseEntity.ok(_repository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Veiculo> getById(@PathVariable String id) {
    Optional<Veiculo> veiculoOptional = _repository.findById(id);
    return veiculoOptional.map(ResponseEntity::ok)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
  }
}
