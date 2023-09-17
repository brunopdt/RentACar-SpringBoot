package lds.rentacar_springboot_lds.cliente;

public record DadosCadastroCliente(String nome, String rg, String cpf, String endereco, String profissao,
    String entidadeEmpregadora, String login, String senha) {
}
