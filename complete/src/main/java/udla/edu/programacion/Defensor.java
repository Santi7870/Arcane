package udla.edu.programacion;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEFENSOR")
public class Defensor extends Personaje {
    public Defensor() {
        super();
    }

    // Constructor alternativo
    public Defensor(String nombre, String ciudad) {
        this.setNombre(nombre);
        this.setCiudad(ciudad);
    }
}
