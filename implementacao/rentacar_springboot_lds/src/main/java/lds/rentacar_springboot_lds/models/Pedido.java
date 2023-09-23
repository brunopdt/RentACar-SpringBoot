package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lds.rentacar_springboot_lds.services.DadosCadastroPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Pedido")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pedido")
public class Pedido {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPedido;
  private String Status;
  private String Veiculo_matricula;
  private String data_inicio;
  private String data_final;
  private String Cliente_cpf;
  private String Agente_cnpj;

  public Pedido(DadosCadastroPedido dados) {
    this.Status = dados.status();
    this.Veiculo_matricula = dados.veiculo_matricula();
    this.data_inicio = dados.data_inicio();
    this.data_final = dados.data_final();
    this.Cliente_cpf = dados.cliente_cpf();
  }
}
