package udla.edu.programacion;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DEFENSOR")
public class Defensor extends Personaje {
    private String rango;
    private String departamento;

    public String getRango() { return rango; }
    public void setRango(String rango) { this.rango = rango; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}
