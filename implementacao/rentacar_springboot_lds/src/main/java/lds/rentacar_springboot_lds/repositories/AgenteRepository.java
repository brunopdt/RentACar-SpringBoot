package lds.rentacar_springboot_lds.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lds.rentacar_springboot_lds.models.Agente;

public interface AgenteRepository extends JpaRepository<Agente, String> {

   /*  @Query("SELECT a FROM Agente a WHERE a.usuario.login = :login")
    Optional<Agente> findByUsuarioLogin(@Param("login") String login);*/
}

