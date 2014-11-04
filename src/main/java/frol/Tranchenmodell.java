package frol;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tranchenmodell {

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tranchenmodell", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tranche> tranchen = new ArrayList<>();

    @OneToOne(optional = true, fetch = FetchType.EAGER)
    private Preisregelung preisregelung;



    public Long getId() {
        return id;
    }

    public List<Tranche> getTranchen() {
        return tranchen;
    }

    public Preisregelung getPreisregelung() {
        return preisregelung;
    }

    public void setPreisregelung(Preisregelung preisregelung) {
        this.preisregelung = preisregelung;
    }


}
