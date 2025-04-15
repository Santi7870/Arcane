package udla.edu.programacion;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
    List<Tecnologia> findByRestringidaTrue();
    List<Tecnologia> findByTipo(String tipo);
}

