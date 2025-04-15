package udla.edu.programacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PersonajeRepository personajeRepo;
    @Autowired private TecnologiaRepository tecnologiaRepo;
    @Autowired private OrganizacionRepository organizacionRepo;
    @Autowired private AfiliacionRepository afiliacionRepo;
    @Autowired private UsoTecnologiaRepository usoTecnologiaRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Limpiar datos existentes
        afiliacionRepo.deleteAll();
        usoTecnologiaRepo.deleteAll();
        personajeRepo.deleteAll();
        organizacionRepo.deleteAll();
        tecnologiaRepo.deleteAll();

        // 1. Crear tecnologías
        Tecnologia hextech = new Tecnologia();
        hextech.setNombre("Hextech");
        hextech.setTipo("Magia");
        hextech.setDescripcion("Tecnología de gemas mágicas");
        hextech.setRestringida(true);
        tecnologiaRepo.save(hextech);

        Tecnologia shimmer = new Tecnologia();
        shimmer.setNombre("Shimmer");
        shimmer.setTipo("Química");
        shimmer.setDescripcion("Compuesto que mejora habilidades");
        shimmer.setRestringida(false);
        tecnologiaRepo.save(shimmer);

        // 2. Crear organizaciones
        Organizacion consejo = new Organizacion();
        consejo.setNombre("Consejo de Piltover");
        consejo.setCiudad("Piltover");
        consejo.setTipo("Gobierno");
        organizacionRepo.save(consejo);

        Organizacion quimicos = new Organizacion();
        quimicos.setNombre("Químicos de Zaun");
        quimicos.setCiudad("Zaun");
        quimicos.setTipo("Criminal");
        organizacionRepo.save(quimicos);

        // 3. Crear personajes
        Defensor vi = new Defensor();
        vi.setNombre("Vi");
        vi.setCiudad("Piltover");
        vi.setRango("Enforcadora");
        personajeRepo.save(vi);

        Rebelde jinx = new Rebelde();
        jinx.setNombre("Jinx");
        jinx.setCiudad("Zaun");
        jinx.setMotivo("Caos");
        personajeRepo.save(jinx);

        // 4. Crear afiliaciones
        Afiliacion afilVi = new Afiliacion();
        afilVi.setPersonaje(vi);
        afilVi.setOrganizacion(consejo);
        afilVi.setRol("Líder");
        afilVi.setActiva(true);
        afiliacionRepo.save(afilVi);

        Afiliacion afilJinx = new Afiliacion();
        afilJinx.setPersonaje(jinx);
        afilJinx.setOrganizacion(quimicos);
        afilJinx.setRol("Saboteadora");
        afilJinx.setActiva(true);
        afiliacionRepo.save(afilJinx);

        // 5. Asignar tecnologías
        UsoTecnologia usoHextech = new UsoTecnologia();
        usoHextech.setPersonaje(vi);
        usoHextech.setTecnologia(hextech);
        usoHextech.setProposito("Guantes de combate");
        usoTecnologiaRepo.save(usoHextech);

        UsoTecnologia usoShimmer = new UsoTecnologia();
        usoShimmer.setPersonaje(jinx);
        usoShimmer.setTecnologia(shimmer);
        usoShimmer.setProposito("Mejora de reflejos");
        usoTecnologiaRepo.save(usoShimmer);

        System.out.println("¡Datos de prueba cargados completamente!");
    }
}
