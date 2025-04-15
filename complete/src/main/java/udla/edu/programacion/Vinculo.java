package udla.edu.programacion;

import jakarta.persistence.*;

@Entity
public class Vinculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Personaje origen;

    @ManyToOne
    private Personaje destino;

    private String tipo; // AMISTAD, ENEMISTAD, FAMILIA, etc.
    private String descripcion;

    // Getters y Setters
    public Long getId() { return id; }
    public Personaje getOrigen() { return origen; }
    public void setOrigen(Personaje origen) { this.origen = origen; }
    public Personaje getDestino() { return destino; }
    public void setDestino(Personaje destino) { this.destino = destino; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}
