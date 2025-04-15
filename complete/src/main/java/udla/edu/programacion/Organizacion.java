package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Organizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOrganizacion;

    private String nombre;
    private String ciudadBase;
    private String tipo;

    // Getters y Setters
    public Long getIdOrganizacion() { return idOrganizacion; }
    public void setIdOrganizacion(Long id) { this.idOrganizacion = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCiudadBase() { return ciudadBase; }
    public void setCiudadBase(String ciudadBase) { this.ciudadBase = ciudadBase; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
