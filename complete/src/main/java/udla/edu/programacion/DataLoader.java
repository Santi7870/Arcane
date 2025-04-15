package udla.edu.programacion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataLoader implements CommandLineRunner {

    private final TecnologiaRepository tecnologiaRepository;
    private final PersonajeRepository personajeRepository;
    private final OrganizacionRepository organizacionRepository;
    private final AfiliacionRepository afiliacionRepository;

    public DataLoader(TecnologiaRepository tecnologiaRepository,
                      PersonajeRepository personajeRepository,
                      OrganizacionRepository organizacionRepository,
                      AfiliacionRepository afiliacionRepository) {
        this.tecnologiaRepository = tecnologiaRepository;
        this.personajeRepository = personajeRepository;
        this.organizacionRepository = organizacionRepository;
        this.afiliacionRepository = afiliacionRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Verificar si ya hay datos para no duplicar
        if (tecnologiaRepository.count() == 0) {
            // Crear tecnologías
            Tecnologia hextech = new Tecnologia();
            hextech.setNombre("Hextech");
            hextech.setTipo("Arma");
            hextech.setDescripcion("Tecnología mágica de Piltover");
            tecnologiaRepository.save(hextech);

            Tecnologia shimmer = new Tecnologia();
            shimmer.setNombre("Shimmer");
            shimmer.setTipo("Droga");
            shimmer.setDescripcion("Compuesto químico de Zaun");
            tecnologiaRepository.save(shimmer);

            // Crear organizaciones
            Organizacion guardianes = new Organizacion();
            guardianes.setNombre("Guardianes de Piltover");
            guardianes.setCiudadBase("Piltover");
            guardianes.setTipo("Defensa");
            organizacionRepository.save(guardianes);

            // Crear personajes
            Defensor vi = new Defensor();
            vi.setNombre("Vi");
            vi.setCiudad("Piltover");
            personajeRepository.save(vi);

            Rebelde jinx = new Rebelde();
            jinx.setNombre("Jinx");
            jinx.setCiudad("Zaun");
            personajeRepository.save(jinx);

            // Crear afiliaciones
            Afiliacion afiliacionVi = new Afiliacion();
            afiliacionVi.setPersonaje(vi);
            afiliacionVi.setOrganizacion(guardianes);
            afiliacionVi.setRol("Líder");
            afiliacionVi.setFechaIngreso(java.time.LocalDate.now());
            afiliacionRepository.save(afiliacionVi);

            System.out.println("Datos iniciales cargados exitosamente!");
        }
    }
}
