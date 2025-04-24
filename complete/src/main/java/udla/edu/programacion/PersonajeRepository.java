package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    @Query("SELECT p FROM Personaje p WHERE TYPE(p) = Defensor")
    List<Defensor> findAllDefensores();

    @Query("SELECT p FROM Personaje p WHERE TYPE(p) = Rebelde")
    List<Rebelde> findAllRebeldes();

    @Query("SELECT p FROM Personaje p WHERE p.ciudad = :ciudad")
    List<Personaje> findByCiudad(String ciudad);

    @Query("SELECT t FROM Tecnologia t WHERE t.id NOT IN " +
            "(SELECT ut.tecnologia.id FROM UsoTecnologia ut WHERE ut.personaje.id = :personajeId)")
    List<Tecnologia> findTecnologiasNoAsignadas(@Param("personajeId") Long personajeId);
}