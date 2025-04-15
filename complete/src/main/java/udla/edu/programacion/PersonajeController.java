package udla.edu.programacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
public class PersonajeController {
    @Autowired
    private PersonajeRepository personajeRepo;

    @Autowired
    private TecnologiaRepository tecnologiaRepo;

    @GetMapping("/personajes")
    public String listarPersonajes(Model model) {
        List<Personaje> personajes = personajeRepo.findAll();
        model.addAttribute("personajes", personajes);
        return "personajes";
    }

    @GetMapping("/personajes/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {
        Personaje personaje = personajeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Personaje no encontrado"));
        model.addAttribute("personaje", personaje);
        return "detalle-personaje";
    }
}
