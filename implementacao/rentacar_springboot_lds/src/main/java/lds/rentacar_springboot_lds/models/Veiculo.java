package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lds.rentacar_springboot_lds.services.DadosVeiculo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name= "Veiculo")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Veiculo")
public class Veiculo {

  
  private String placa;
  @Id
  private String matricula;
  private String marca;
  private String modelo;

  public Veiculo(DadosVeiculo dados) {
    this.placa = dados.placa();
    this.marca = dados.marca();
    this.modelo = dados.modelo();
    this.matricula = dados.matricula();
  }
}
