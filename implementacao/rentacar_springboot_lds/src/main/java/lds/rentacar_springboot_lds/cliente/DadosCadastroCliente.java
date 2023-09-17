package lds.rentacar_springboot_lds.cliente;

import java.util.List;

import lds.rentacar_springboot_lds.usuario.DadosUsuario;

public record DadosCadastroCliente(String nome, String rg, String cpf, String endereco, String profissao, String entidadeEmpregadora, List<DadosRendimentoCliente> rendimentos, DadosUsuario usuario) {
  
}
