package udla.edu.programacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonajeController {

    @Autowired
    private PersonajeRepository personajeRepo;

    @Autowired
    private TecnologiaRepository tecnologiaRepo;

    @Autowired
    private OrganizacionRepository organizacionRepo;

    @Autowired
    private RelacionRepository relacionRepo;

    @Autowired
    private UsoTecnologiaRepository usoTecnologiaRepo;

    @Autowired
    private VinculoRepository vinculoRepo;

    // Endpoint de prueba
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "¡Sistema funcionando! Personajes: " + personajeRepo.count() +
                ", Tecnologías: " + tecnologiaRepo.count() +
                ", Relaciones: " + relacionRepo.count();
    }

    // Lista principal de personajes
    @GetMapping("/personajes")
    public String listarPersonajes(Model model) {
        model.addAttribute("personajes", personajeRepo.findAll());
        return "listar";
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
    }

    // Eliminar personaje
    @GetMapping("/personajes/eliminar/{id}")
    public String eliminarPersonaje(@PathVariable Long id) {
        personajeRepo.deleteById(id);
        return "redirect:/personajes";
    }

    // ========== RELACIONES ENTRE PERSONAJES ==========
    @GetMapping("/personajes/{id}/relaciones")
    public String verRelaciones(@PathVariable Long id, Model model) {
        Personaje personaje = personajeRepo.findById(id).orElseThrow();
        model.addAttribute("personaje", personaje);
        model.addAttribute("relaciones", relacionRepo.findByPersonajeAOrPersonajeB(personaje, personaje));
        return "relaciones";
    }

    @GetMapping("/personajes/{id}/relaciones/nueva")
    public String nuevaRelacion(@PathVariable Long id, Model model) {
        Personaje personaje = personajeRepo.findById(id).orElseThrow();
        List<Personaje> otros = personajeRepo.findAll().stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());

        model.addAttribute("personaje", personaje);
        model.addAttribute("otrosPersonajes", otros);
        model.addAttribute("relacion", new Relacion());
        return "formulario-relacion";
    }

    @PostMapping("/personajes/{id}/relaciones/guardar")
    public String guardarRelacion(@PathVariable Long id,
                                  @ModelAttribute Relacion relacion,
                                  @RequestParam Long otroId) {
        Personaje personajeA = personajeRepo.findById(id).orElseThrow();
        Personaje personajeB = personajeRepo.findById(otroId).orElseThrow();

        relacion.setPersonajeA(personajeA);
        relacion.setPersonajeB(personajeB);
        relacionRepo.save(relacion);

        return "redirect:/personajes/" + id + "/relaciones";
    }

    // ========== TECNOLOGÍAS ==========
    @GetMapping("/personajes/{id}/tecnologias")
    public String verTecnologias(@PathVariable Long id, Model model) {
        Personaje personaje = personajeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Personaje no encontrado"));

        // Usamos el método con JOIN FETCH para optimizar
        List<UsoTecnologia> usos = usoTecnologiaRepo.findWithTecnologiaByPersonajeId(id);

        model.addAttribute("personaje", personaje);
        model.addAttribute("usosTecnologia", usos);
        return "tecnologias";
    }

    @GetMapping("/personajes/{id}/tecnologias/asignar")
    public String asignarTecnologia(@PathVariable Long id, Model model) {
        Personaje personaje = personajeRepo.findById(id).orElseThrow();
        model.addAttribute("personaje", personaje);
        model.addAttribute("tecnologiasDisponibles", tecnologiaRepo.findAll());
        model.addAttribute("uso", new UsoTecnologia());
        return "formulario-tecnologia";
    }

    @PostMapping("/personajes/{id}/tecnologias/guardar")
    public String guardarTecnologia(@PathVariable Long id,
                                    @RequestParam Long tecnologiaId,
                                    @RequestParam String proposito) {
        Personaje personaje = personajeRepo.findById(id).orElseThrow();
        Tecnologia tecnologia = tecnologiaRepo.findById(tecnologiaId).orElseThrow();

        UsoTecnologia uso = new UsoTecnologia();
        uso.setPersonaje(personaje);
        uso.setTecnologia(tecnologia);
        uso.setProposito(proposito);
        usoTecnologiaRepo.save(uso);

        return "redirect:/personajes/" + id + "/tecnologias";
    }

    // ========== VÍNCULOS ==========
    @GetMapping("/personajes/{id}/vinculos")
    public String verVinculos(@PathVariable Long id, Model model) {
        Personaje personaje = personajeRepo.findById(id).orElseThrow();
        model.addAttribute("personaje", personaje);
        model.addAttribute("vinculos", vinculoRepo.findByOrigenOrDestino(personaje, personaje));
        return "vinculos";
    }

    @GetMapping("/personajes/{id}/vinculos/nuevo")
    public String nuevoVinculo(@PathVariable Long id, Model model) {
        Personaje origen = personajeRepo.findById(id).orElseThrow();
        List<Personaje> otros = personajeRepo.findAll().stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());

        model.addAttribute("origen", origen);
        model.addAttribute("otrosPersonajes", otros);
        model.addAttribute("vinculo", new Vinculo());
        return "formulario-vinculo";
    }

    @PostMapping("/personajes/{id}/vinculos/guardar")
    public String guardarVinculo(@PathVariable Long id,
                                 @ModelAttribute Vinculo vinculo,
                                 @RequestParam Long destinoId) {
        Personaje origen = personajeRepo.findById(id).orElseThrow();
        Personaje destino = personajeRepo.findById(destinoId).orElseThrow();

        vinculo.setOrigen(origen);
        vinculo.setDestino(destino);
        vinculoRepo.save(vinculo);

        return "redirect:/personajes/" + id + "/vinculos";
    }
}