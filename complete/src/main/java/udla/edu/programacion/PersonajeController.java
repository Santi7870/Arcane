package udla.edu.programacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonajeController {
    @Autowired
    private PersonajeRepository personajeRepository;

    @GetMapping("/personajes")
    public String mostrarPersonajes(Model model) {
        model.addAttribute("personajes", personajeRepository.findAll());
        return "personajes";
    }
}
