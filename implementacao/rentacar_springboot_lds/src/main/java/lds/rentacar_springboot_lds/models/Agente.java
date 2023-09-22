package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lds.rentacar_springboot_lds.services.DadosCadastroAgente;

@Table(name = "Agente")
@Entity(name = "Agente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cnpj")
public class Agente {
    private String nomeFantasia;
    @Id
    private String cnpj;
    private String usuariologin;
    private String tipo;

    public Agente(DadosCadastroAgente dados, String usuariologin) {
        this.nomeFantasia = dados.nomeFantasia();
        this.cnpj = dados.cnpj();
        this.tipo = dados.tipo();

        this.usuariologin = usuariologin;
    }

    public Agente updateCliente(DadosCadastroAgente dados) {
        if (dados.cnpj() != null)
            this.cnpj = dados.cnpj();
        if (dados.nomeFantasia() != null)
            this.nomeFantasia = dados.nomeFantasia();

        return this;
    }

}
