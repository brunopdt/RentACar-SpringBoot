package lds.rentacar_springboot_lds.services;

public record DadosCadastroCliente(String nome, String rg, String cpf, String endereco, String profissao,
    String entidadeEmpregadora, String login, String senha) {
}
