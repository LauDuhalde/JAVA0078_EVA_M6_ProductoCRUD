package cl.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cl.web.dto.UsuarioDTO;
import cl.web.models.Usuario;
import cl.web.services.UsuarioServiceImpl;
import jakarta.validation.Valid;

@Controller
public class WebController {
	
	@Autowired
	UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping("/")
	public String index() {
		return "redirect:registro";
	}
	
    @GetMapping("/registro")
    public String mostrarRegistroForm(Model model) {
        UsuarioDTO user = new UsuarioDTO();
        model.addAttribute("usuario", user);
        return "register";
    }
	
    @PostMapping("/registro")
    public String registro_guardar(@Valid @ModelAttribute("usuario") UsuarioDTO userDto,
                                   BindingResult result,
                                   Model model) {
        try {

            Usuario existeUsername = usuarioServiceImpl.findByUsername(userDto.getUsername());
            if (existeUsername != null) {
                result.rejectValue("username", null, "Ese nombre de usuario ya está en uso");
            }

            if (result.hasErrors()) {
                model.addAttribute("usuario", userDto);
                return "register";
            }

            usuarioServiceImpl.saveUser(userDto);
            model.addAttribute("mensaje", "Usuario creado con exito");
            return "register";

        } catch (Exception e) {
            // Captura cualquier error no previsto y lo deriva a página de error
        	model.addAttribute("errorMessage", "Ocurrió un error inesperado. Intenta más tarde.");
        	return "register";
        }
    }
	
}