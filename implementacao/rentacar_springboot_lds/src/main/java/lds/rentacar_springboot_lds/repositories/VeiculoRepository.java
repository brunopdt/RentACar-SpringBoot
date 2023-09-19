package lds.rentacar_springboot_lds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lds.rentacar_springboot_lds.models.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, String> {

}
