package udla.edu.programacion;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PersonajeRepository extends CrudRepository<Personaje, Long> {
    @EntityGraph(attributePaths = {"afiliaciones"})
    List<Personaje> findAll();
}
