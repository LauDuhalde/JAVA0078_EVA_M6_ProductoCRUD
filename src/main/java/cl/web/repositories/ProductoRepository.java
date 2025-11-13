package cl.web.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import cl.web.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
