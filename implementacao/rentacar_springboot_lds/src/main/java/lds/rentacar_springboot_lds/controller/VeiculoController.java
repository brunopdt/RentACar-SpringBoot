package lds.rentacar_springboot_lds.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lds.rentacar_springboot_lds.models.Veiculo;
import lds.rentacar_springboot_lds.repositories.VeiculoRepository;
import lds.rentacar_springboot_lds.services.DadosVeiculo;

@RestController
@RequestMapping("/veiculos")
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
  
}
