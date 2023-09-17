package lds.rentacar_springboot_lds.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.Embedded;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lds.rentacar_springboot_lds.services.DadosCadastroCliente;
import lds.rentacar_springboot_lds.services.DadosRendimentoCliente;
import lds.rentacar_springboot_lds.services.DadosUsuario;
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

  @Embedded
  private List<DadosRendimentoCliente> rendimentos = new ArrayList<>();

  @Embedded
  private Usuario usuario;

  public Cliente(DadosCadastroCliente dados) {
    this.nome = dados.nome();
    this.cpf = dados.cpf();
    this.rg = dados.rg();
    this.endereco = dados.endereco();
    this.profissao = dados.profissao();
    this.entidadeEmpregadora = dados.entidadeEmpregadora();
    this.rendimentos = new ArrayList<DadosRendimentoCliente>();
    this.usuario = new Usuario(new DadosUsuario(dados.login(), dados.senha()));
  }

}