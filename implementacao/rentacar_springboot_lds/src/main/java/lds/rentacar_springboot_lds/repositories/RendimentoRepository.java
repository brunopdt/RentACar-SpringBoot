package lds.rentacar_springboot_lds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lds.rentacar_springboot_lds.models.Rendimento;

public interface RendimentoRepository extends JpaRepository<Rendimento, String> {

}
