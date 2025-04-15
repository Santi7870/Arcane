package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tecnologia") // Nombre exacto de tabla
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnologia;

    private String nombre;
    private String tipo;
    private String descripcion;

    // Getters y Setters
    public Long getIdTecnologia() { return idTecnologia; }
    public void setIdTecnologia(Long id) { this.idTecnologia = id; }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    // ... (otros getters/setters)
}
