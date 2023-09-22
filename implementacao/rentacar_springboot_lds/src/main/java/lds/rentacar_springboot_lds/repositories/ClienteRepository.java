package lds.rentacar_springboot_lds.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lds.rentacar_springboot_lds.models.Agente;
import lds.rentacar_springboot_lds.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente findByusuariologin(String login);
}
