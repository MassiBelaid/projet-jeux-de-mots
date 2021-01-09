package m2.jdm.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "terme")
@Access(AccessType.FIELD)
public class Terme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String terme;
    private String raffinement;
    private boolean importe;

    @OneToMany(mappedBy = "terme1")
    private Set<Relation> sortantes;
    @OneToMany(mappedBy = "terme2")
    private Set<Relation> entrantes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerme() {
        return terme;
    }

    public void setTerme(String terme) {
        this.terme = terme;
    }

    public String getRaffinement() {
        return raffinement;
    }

    public void setRaffinement(String raffinement) {
        this.raffinement = raffinement;
    }

    public boolean isImporte() {
        return importe;
    }

    public void setImporte(boolean importe) {
        this.importe = importe;
    }

    public Set<Relation> getSortantes() {
        return sortantes;
    }

    public void setSortantes(Set<Relation> sortantes) {
        this.sortantes = sortantes;
    }

    public Set<Relation> getEntrantes() {
        return entrantes;
    }

    public void setEntrantes(Set<Relation> entrantes) {
        this.entrantes = entrantes;
    }
}
