package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VinculoRepository extends JpaRepository<Vinculo, Long> {
    List<Vinculo> findByOrigenOrDestino(Personaje origen, Personaje destino);
}
