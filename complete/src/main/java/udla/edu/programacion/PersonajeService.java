package udla.edu.programacion;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepo;

    @Transactional
    public Personaje guardarPersonaje(Personaje personaje) {
        // Validaciones adicionales
        if (personaje.getCiudad().equals("Zaun") && personaje instanceof Defensor) {
            throw new IllegalArgumentException("No puede haber defensores en Zaun");
        }
        return personajeRepo.save(personaje);
    }

    @Transactional
    public void eliminarPersonaje(Long id) {
        // Validar si tiene relaciones primero
        personajeRepo.deleteById(id);
    }

    public List<Defensor> listarDefensores() {
        return personajeRepo.findAllDefensores();
    }
}