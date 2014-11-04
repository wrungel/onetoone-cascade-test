package frol;

import javax.persistence.*;

@Entity
public class Preisregelung {
    @Id
    private Long id;

    @OneToOne(mappedBy = "preisregelung", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Tranchenmodell tranchenmodell;


    public Long getId() {
        return id;
    }

    public Tranchenmodell getTranchenmodell() {
        return tranchenmodell;
    }

    public void setTranchenmodell(Tranchenmodell tranchenmodell) {
        this.tranchenmodell = tranchenmodell;
    }
}
