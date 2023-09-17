package lds.rentacar_springboot_lds.models;

import lds.rentacar_springboot_lds.services.DadosUsuario;

public class Usuario {
  private String login;
  private String senha;

  public Usuario(DadosUsuario dados) {
    this.login = dados.login();
    this.senha = dados.senha();
  }
}
