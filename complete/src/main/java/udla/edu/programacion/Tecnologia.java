package udla.edu.programacion;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo; // HEXTECH, SHIMMER, etc.
    private String descripcion;
    private boolean restringida;

    @OneToMany(mappedBy = "tecnologia", cascade = CascadeType.ALL)
    private List<UsoTecnologia> usos = new ArrayList<>();

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isRestringida() { return restringida; }
    public void setRestringida(boolean restringida) { this.restringida = restringida; }
}
