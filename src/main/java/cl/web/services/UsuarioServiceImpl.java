package cl.web.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.web.dto.UsuarioDTO;
import cl.web.models.Usuario;
import cl.web.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

	private final PasswordEncoder passwordEncoder;

	public UsuarioServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder= passwordEncoder;
	}
	
	@Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }

	@Override
	public void saveUser(UsuarioDTO userDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(userDto.getUsername());
        usuario.setRole(userDto.getRole());
        // encriptar password usando spring security bcrypt
        usuario.setPassword(passwordEncoder.encode(userDto.getPassword()));
        
        usuarioRepository.save(usuario);
		
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username).orElse(null);
	}

	@Override
	public List<UsuarioDTO> findAllUsers() {
		 List<Usuario> usuario = usuarioRepository.findAll();
		 
		 return usuario.stream()
	                .map(this::mapToDto)
	                .collect(Collectors.toList());
	}
	
   private UsuarioDTO mapToDto(Usuario usuario) {
	   UsuarioDTO userDto = new UsuarioDTO();
        userDto.setId(usuario.getId());
        userDto.setUsername(usuario.getUsername());
        userDto.setPassword(usuario.getPassword());
        userDto.setRole(usuario.getRole());
        return userDto;
   }
}
