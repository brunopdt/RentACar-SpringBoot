package lds.rentacar_springboot_lds.cliente;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lds.rentacar_springboot_lds.usuario.DadosUsuario;

public class Rendimento {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private double valor;

  public Rendimento(DadosRendimentoCliente dados){
    this.valor = dados.valor();
  }
}

