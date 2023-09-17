package lds.rentacar_springboot_lds.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lds.rentacar_springboot_lds.services.DadosCadastroAgente;
import lds.rentacar_springboot_lds.services.DadosUsuario;

@Table(name = "Agente")
@Entity(name = "Agente")
@Getter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "cnpj")
public class Agente {
    private String nomeFantasia;
    private String cnpj;

    @Embedded
    private Usuario usuario;

    public Agente(DadosCadastroAgente dados) {
        this.nomeFantasia = dados.nomeFantasia();
        this.cnpj = dados.CNPJ();
        //if (!this.validaCpfCnpj())
         //  throw new IllegalArgumentException("CNPJ ou CPF inválido");
         this.usuario = new Usuario(new DadosUsuario(dados.login(), dados.senha()));
    }

}
