package lds.rentacar_springboot_lds.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lds.rentacar_springboot_lds.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
/* 
    @Query("SELECT a FROM Cliente c WHERE c.usuario.login = :login")
     Optional<Cliente> findByUsuarioLogin(@Param("login") String login);*/
}

