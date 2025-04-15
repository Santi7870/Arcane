package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personaje") // Nombre exacto de tabla
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipologia", discriminatorType = DiscriminatorType.STRING)
public abstract class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<Afiliacion> afiliaciones = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<Afiliacion> getAfiliaciones() {
        return afiliaciones;
    }

    public void setAfiliaciones(List<Afiliacion> afiliaciones) {
        this.afiliaciones = afiliaciones;
    }
    // ... (otros getters/setters)
}