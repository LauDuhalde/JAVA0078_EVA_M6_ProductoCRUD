package cl.web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.web.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
}
