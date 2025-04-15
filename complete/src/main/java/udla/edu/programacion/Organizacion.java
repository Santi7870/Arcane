package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String ciudad;
    private String tipo; // Gobierno, Criminal, Científico, etc.

    @OneToMany(mappedBy = "organizacion", cascade = CascadeType.ALL)
    private List<Afiliacion> miembros = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
