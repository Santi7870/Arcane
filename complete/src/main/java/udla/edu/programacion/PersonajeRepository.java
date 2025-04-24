package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    @Query("SELECT p FROM Personaje p WHERE TYPE(p) = Defensor")
    List<Defensor> findAllDefensores();

    @Query("SELECT p FROM Personaje p WHERE TYPE(p) = Rebelde")
    List<Rebelde> findAllRebeldes();

    @Query("SELECT p FROM Personaje p WHERE p.ciudad = :ciudad")
    List<Personaje> findByCiudad(String ciudad);
}