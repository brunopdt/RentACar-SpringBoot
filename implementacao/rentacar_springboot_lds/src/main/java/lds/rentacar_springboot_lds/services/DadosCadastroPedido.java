package lds.rentacar_springboot_lds.services;

public record DadosCadastroPedido(String status, String veiculo_matricula, String data_inicio, String data_final,
    String cliente_cpf) {
}