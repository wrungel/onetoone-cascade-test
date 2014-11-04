package frol;

import javax.persistence.*;

@Entity
public class Tranche {

    @Id
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
