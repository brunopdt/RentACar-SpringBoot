package lds.rentacar_springboot_lds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lds.rentacar_springboot_lds.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
