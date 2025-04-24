package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface UsoTecnologiaRepository extends JpaRepository<UsoTecnologia, Long> {

    // Método para buscar por personaje
    List<UsoTecnologia> findByPersonajeId(Long personajeId);

    // Método alternativo con JOIN FETCH para evitar N+1
    @Query("SELECT ut FROM UsoTecnologia ut JOIN FETCH ut.tecnologia WHERE ut.personaje.id = :personajeId")
    List<UsoTecnologia> findWithTecnologiaByPersonajeId(@Param("personajeId") Long personajeId);

    // Método para verificar si ya existe una relación
    boolean existsByPersonajeAndTecnologia(Personaje personaje, Tecnologia tecnologia);
}

