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
    private String usuario_login;
    private TipoAgente tipo;

    public Agente(DadosCadastroAgente dados, String usuario_login) {
        this.nomeFantasia = dados.nomeFantasia();
        this.cnpj = dados.cnpj();
        this.tipo = dados.tipo();

        this.usuario_login = usuario_login;
    }

    public Agente updateCliente(DadosCadastroAgente dados) {
        if (dados.cnpj() != null)
            this.cnpj = dados.cnpj();
        if (dados.nomeFantasia() != null)
            this.nomeFantasia = dados.nomeFantasia();

        return this;
    }

}
