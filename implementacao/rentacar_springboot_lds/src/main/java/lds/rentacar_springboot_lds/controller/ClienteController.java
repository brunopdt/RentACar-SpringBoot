package lds.rentacar_springboot_lds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lds.rentacar_springboot_lds.models.Cliente;
import lds.rentacar_springboot_lds.repositories.ClienteRepository;
import lds.rentacar_springboot_lds.services.DadosCadastroCliente;

@RestController
@RequestMapping("usuarios")
public class ClienteController {

  @Autowired
  private ClienteRepository _repository;

  @GetMapping
  public String helloWorld() {
    return "Hello World!";
  }

  @PostMapping
  public void cadastrar( @RequestBody DadosCadastroCliente dados) {
    _repository.save(new Cliente(dados));
  }

}
