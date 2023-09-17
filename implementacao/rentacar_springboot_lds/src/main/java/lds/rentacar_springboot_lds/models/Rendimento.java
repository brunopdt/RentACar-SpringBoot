package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lds.rentacar_springboot_lds.services.DadosRendimentoCliente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Rendimentos")
public class Rendimento {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idRendimentos;
  private double valor;
  private String Cliente_cpf;

  public Rendimento(DadosRendimentoCliente dados) {
    this.valor = dados.valor();
    this.Cliente_cpf = dados.Cliente_cpf();
  }
}
