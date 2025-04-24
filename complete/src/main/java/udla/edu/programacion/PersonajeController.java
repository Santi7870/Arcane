package udla.edu.programacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepo;

    @Autowired
    private TecnologiaRepository tecnologiaRepo;

    @Autowired
    private OrganizacionRepository organizacionRepo;

    // Endpoint de prueba - FUNCIONA
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "¡Sistema funcionando! Personajes: " + personajeRepo.count();
    }

    // Lista principal de personajes
    @GetMapping("/personajes")
    public String listarPersonajes(Model model) {
        try {
            model.addAttribute("personajes", personajeRepo.findAll());
            return "listar";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    // Mostrar formulario de creación
    @GetMapping("/personajes/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tecnologias", tecnologiaRepo.findAll());
        model.addAttribute("organizaciones", organizacionRepo.findAll());
        model.addAttribute("tipoPersonaje", "DEFENSOR");

        Defensor personaje = new Defensor();
        personaje.setCiudad("Piltover");
        model.addAttribute("personaje", personaje);

        return "formulario";
    }

    // Procesar formulario
    @PostMapping("/personajes/guardar")
    public String guardarPersonaje(
            @RequestParam String nombre,
            @RequestParam String ciudad,
            @RequestParam String tipoPersonaje,
            @RequestParam(required = false) String rango,
            @RequestParam(required = false) String motivo) {

        try {
            if ("DEFENSOR".equals(tipoPersonaje)) {
                Defensor d = new Defensor();
                d.setNombre(nombre);
                d.setCiudad(ciudad);
                d.setRango(rango);
                personajeRepo.save(d);
            } else {
                Rebelde r = new Rebelde();
                r.setNombre(nombre);
                r.setCiudad(ciudad);
                r.setMotivo(motivo);
                personajeRepo.save(r);
            }
            return "redirect:/personajes";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/personajes/nuevo?error";
        }
    }

    // Eliminar personaje
    @GetMapping("/personajes/eliminar/{id}")
    public String eliminarPersonaje(@PathVariable Long id) {
        try {
            personajeRepo.deleteById(id);
            return "redirect:/personajes";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/personajes?error";
        }
    }
}