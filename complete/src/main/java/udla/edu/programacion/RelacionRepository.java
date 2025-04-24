package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RelacionRepository extends JpaRepository<Relacion, Long> {
    List<Relacion> findByPersonajeAOrPersonajeB(Personaje a, Personaje b);
}

