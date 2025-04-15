package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "alineacion", discriminatorType = DiscriminatorType.STRING)
public abstract class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;
    private String habilidades;

    @OneToMany(mappedBy = "origen", cascade = CascadeType.ALL)
    private List<Vinculo> vinculos = new ArrayList<>();

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<Afiliacion> afiliaciones = new ArrayList<>();

    @OneToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<UsoTecnologia> tecnologias = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getHabilidades() { return habilidades; }
    public void setHabilidades(String habilidades) { this.habilidades = habilidades; }
    public List<Vinculo> getVinculos() { return vinculos; }
    public List<Afiliacion> getAfiliaciones() { return afiliaciones; }
    public List<UsoTecnologia> getTecnologias() { return tecnologias; }
}