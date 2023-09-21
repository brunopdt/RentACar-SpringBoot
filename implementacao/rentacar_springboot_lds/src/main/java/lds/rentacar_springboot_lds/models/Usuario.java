package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lds.rentacar_springboot_lds.services.DadosUsuario;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Usuario")
public class Usuario {
  @Id
  private String login;

  public String getLogin() {
    return login;
  }

  private String senha;
  public String getsenha(){
    return this.senha;
  }
  public Usuario(DadosUsuario dados) {
    this.login = dados.login();
    this.senha = dados.senha();
  }
}
