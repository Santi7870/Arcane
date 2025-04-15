package udla.edu.programacion;

import jakarta.persistence.*;

@Entity
public class Relacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_personaje_a")
    private Personaje personajeA;

    @ManyToOne
    @JoinColumn(name = "id_personaje_b")
    private Personaje personajeB;

    private String tipoRelacion;

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Personaje getPersonajeB() {
        return personajeB;
    }

    public void setPersonajeB(Personaje personajeB) {
        this.personajeB = personajeB;
    }

    public Personaje getPersonajeA() {
        return personajeA;
    }

    public void setPersonajeA(Personaje personajeA) {
        this.personajeA = personajeA;
    }
}

