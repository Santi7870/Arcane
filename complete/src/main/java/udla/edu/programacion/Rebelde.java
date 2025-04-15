package udla.edu.programacion;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("REBELDE")
public class Rebelde extends Personaje {
    public Rebelde() {
        super();
    }

    // Constructor alternativo
    public Rebelde(String nombre, String ciudad) {
        this.setNombre(nombre);
        this.setCiudad(ciudad);
    }
}

