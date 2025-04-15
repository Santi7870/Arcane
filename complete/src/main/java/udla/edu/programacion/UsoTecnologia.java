package udla.edu.programacion;

import jakarta.persistence.*;

@Entity
public class UsoTecnologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Personaje personaje;

    @ManyToOne
    private Tecnologia tecnologia;

    private String proposito;

    // Getters y Setters
    public Long getId() { return id; }
    public Personaje getPersonaje() { return personaje; }
    public void setPersonaje(Personaje personaje) { this.personaje = personaje; }
    public Tecnologia getTecnologia() { return tecnologia; }
    public void setTecnologia(Tecnologia tecnologia) { this.tecnologia = tecnologia; }
    public String getProposito() { return proposito; }
    public void setProposito(String proposito) { this.proposito = proposito; }
}

