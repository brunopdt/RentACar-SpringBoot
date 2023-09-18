package lds.rentacar_springboot_lds.services;

import lds.rentacar_springboot_lds.models.TipoAgente;

public record DadosCadastroAgente(String cnpj, String nomeFantasia, TipoAgente tipo, String login, String senha) {
}
