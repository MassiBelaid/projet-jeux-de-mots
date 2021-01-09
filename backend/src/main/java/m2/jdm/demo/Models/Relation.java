package m2.jdm.demo.Models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "relation")
@Access(AccessType.FIELD)
public class Relation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int poids;

    @ManyToOne
    @JoinColumn(name="terme1", nullable=false)
    private Terme terme1;

    @ManyToOne
    @JoinColumn(name="terme2", nullable=false)
    private Terme terme2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public Terme getTerme1() {
        return terme1;
    }

    public void setTerme1(Terme terme1) {
        this.terme1 = terme1;
    }

    public Terme getTerme2() {
        return terme2;
    }

    public void setTerme2(Terme terme2) {
        this.terme2 = terme2;
    }
}
