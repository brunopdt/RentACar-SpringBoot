package lds.rentacar_springboot_lds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import lds.rentacar_springboot_lds.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente findByusuariologin(String login);
}
