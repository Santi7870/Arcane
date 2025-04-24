package udla.edu.programacion;

import jakarta.persistence.*;

@Entity
public class UsoTecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personaje_id", nullable = false)
    private Personaje personaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tecnologia_id", nullable = false)
    private Tecnologia tecnologia;

    @Column(length = 500)
    private String proposito;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Personaje getPersonaje() { return personaje; }
    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Tecnologia getTecnologia() { return tecnologia; }
    public void setTecnologia(Tecnologia tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String getProposito() { return proposito; }
    public void setProposito(String proposito) {
        this.proposito = proposito;
    }

    // Método toString para debugging
    @Override
    public String toString() {
        return "UsoTecnologia{" +
                "id=" + id +
                ", personaje=" + (personaje != null ? personaje.getId() : null) +
                ", tecnologia=" + (tecnologia != null ? tecnologia.getId() : null) +
                ", proposito='" + proposito + '\'' +
                '}';
    }
}

