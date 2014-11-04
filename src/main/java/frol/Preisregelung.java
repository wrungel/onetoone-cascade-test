package frol;

import javax.persistence.*;
import java.util.List;

@Entity
public class Preisregelung {
    @Id
    private Long id;

    @OneToMany(mappedBy = "preisregelung", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Tranchenmodell> tranchenmodell;


    public Long getId() {
        return id;
    }

    public Tranchenmodell getTranchenmodell() {
        return tranchenmodell.isEmpty() ? null : tranchenmodell.get(0);
    }

    public void setTranchenmodell(Tranchenmodell tranchenmodell) {
        if (!this.tranchenmodell.isEmpty())
            this.tranchenmodell.remove(0);
        this.tranchenmodell.add(tranchenmodell);
    }
}
