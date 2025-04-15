package udla.edu.programacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonajeRepository personajeRepo;
    private final TecnologiaRepository tecnologiaRepo;
    private final OrganizacionRepository organizacionRepo;

    public DataLoader(PersonajeRepository personajeRepo,
                      TecnologiaRepository tecnologiaRepo,
                      OrganizacionRepository organizacionRepo) {
        this.personajeRepo = personajeRepo;
        this.tecnologiaRepo = tecnologiaRepo;
        this.organizacionRepo = organizacionRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Crear tecnologías
        Tecnologia hextech = new Tecnologia();
        hextech.setNombre("Hextech");
        hextech.setTipo("Magia");
        hextech.setDescripcion("Tecnología basada en gemas mágicas");
        hextech.setRestringida(true);
        tecnologiaRepo.save(hextech);

        // Crear organizaciones
        Organizacion consejo = new Organizacion();
        consejo.setNombre("Consejo de Piltover");
        consejo.setCiudad("Piltover");
        consejo.setTipo("Gobierno");
        organizacionRepo.save(consejo);

        // Crear personajes
        Defensor vi = new Defensor();
        vi.setNombre("Vi");
        vi.setCiudad("Piltover");
        vi.setRango("Enforcadora");
        vi.setDepartamento("Seguridad");
        personajeRepo.save(vi);

        Rebelde jinx = new Rebelde();
        jinx.setNombre("Jinx");
        jinx.setCiudad("Zaun");
        jinx.setMotivo("Libertad tecnológica");
        jinx.setGrupo("Químicos");
        personajeRepo.save(jinx);

        System.out.println("Datos iniciales cargados exitosamente!");
    }
}
