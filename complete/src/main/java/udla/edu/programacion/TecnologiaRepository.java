package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    List<Tecnologia> findByRestringidaTrue();
    List<Tecnologia> findByTipo(String tipo);

    // Método CORREGIDO para tecnologías no asignadas
    @Query("SELECT t FROM Tecnologia t WHERE t NOT IN " +
            "(SELECT ut.tecnologia FROM UsoTecnologia ut WHERE ut.personaje.id = :personajeId)")
    List<Tecnologia> findTecnologiasNoAsignadas(@Param("personajeId") Long personajeId);
}
