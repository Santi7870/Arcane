package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipo_personaje",
        discriminatorType = DiscriminatorType.STRING
)
public abstract class Personaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Afiliacion> afiliaciones = new ArrayList<>();

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsoTecnologia> tecnologias = new ArrayList<>();

    // Constructor por defecto
    public Personaje() {}

    // Constructor con parámetros básicos
    public Personaje(String nombre, String ciudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
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

    public List<UsoTecnologia> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<UsoTecnologia> tecnologias) {
        this.tecnologias = tecnologias;
    }

    // Métodos utilitarios
    public void addAfiliacion(Afiliacion afiliacion) {
        afiliaciones.add(afiliacion);
        afiliacion.setPersonaje(this);
    }

    public void removeAfiliacion(Afiliacion afiliacion) {
        afiliaciones.remove(afiliacion);
        afiliacion.setPersonaje(null);
    }

    public void addTecnologia(UsoTecnologia tecnologia) {
        tecnologias.add(tecnologia);
        tecnologia.setPersonaje(this);
    }

    public void removeTecnologia(UsoTecnologia tecnologia) {
        tecnologias.remove(tecnologia);
        tecnologia.setPersonaje(null);
    }

    @Override
    public String toString() {
        return "Personaje{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}