package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lds.rentacar_springboot_lds.services.DadosCadastroCliente;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "Cliente")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cpf")

public class Cliente {

  private String nome;
  @Id
  private String cpf;
  private String rg;
  private String endereco;
  private String profissao;
  private String entidadeEmpregadora;
  private String usuario_login;

  public Cliente(DadosCadastroCliente dados, String usuario_login) {
    this.nome = dados.nome();
    this.cpf = dados.cpf();
    this.rg = dados.rg();
    this.endereco = dados.endereco();
    this.profissao = dados.profissao();
    this.entidadeEmpregadora = dados.entidadeEmpregadora();

    this.usuario_login = usuario_login;
  }

  public Cliente updateCliente(DadosCadastroCliente dados) {
    if (dados.nome() != null)
      this.nome = dados.nome();
    if (dados.rg() != null)
      this.rg = dados.rg();
    if (dados.endereco() != null)
      this.endereco = dados.endereco();
    if (dados.profissao() != null)
      this.profissao = dados.profissao();
    if (dados.entidadeEmpregadora() != null)
      this.entidadeEmpregadora = dados.entidadeEmpregadora();

    return this;
  }

}
