package frol;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class H2Test {

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeClass
    public static void beforeClass() {
        Map<String, String> props = new HashMap<>();

        props.put("javax.persistence.schema-generation.drop-source", "script");
        props.put("javax.persistence.schema-generation.drop-script-source", "drop-script.sql");
        props.put("javax.persistence.schema-generation.database.action", "drop-and-create");
        props.put("javax.persistence.schema-generation.create-source", "script");
        props.put("javax.persistence.schema-generation.create-script-source", "create-script.sql");
        props.put("javax.persistence.sql-load-script-source", "load-script.sql");

        emf = Persistence.createEntityManagerFactory("h2", props);
    }

    @Before
    public void before() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }


    @After
    public void after() {
        em.getTransaction().rollback();
    }

    @Test
    public void removeTranchenmodell() {
        Tranchenmodell tranchenmodell = em.find(Tranchenmodell.class, 1951L);

        assert tranchenmodell != null;
        assert tranchenmodell.getTranchen().size() == 2;
        em.remove(tranchenmodell);

        assert !em.contains(tranchenmodell);

        System.out.println("*********** START DELETE");
        em.flush();
        System.out.println("*********** END DELETE");

        em.clear();

        tranchenmodell = em.find(Tranchenmodell.class, 1951L);
        assert tranchenmodell == null;

        Tranche tranche1 = em.find(Tranche.class, 1951L);
        assert tranche1 == null;
        Tranche tranche2 = em.find(Tranche.class, 1952L);
        assert tranche2 == null;
    }

    @Test
    public void removeTranche() {
        Tranche tranche = em.find(Tranche.class, 1951L);

        assert tranche != null;
        em.remove(tranche);

        System.out.println("*********** START DELETE");
        em.flush();
        System.out.println("*********** END DELETE");
        em.clear();

        tranche = em.find(Tranche.class, 1951L);
        assert tranche == null;
    }


    @Test
    public void removeOrphanTranche() {
        Tranchenmodell tranchenmodell = em.find(Tranchenmodell.class, 1951L);
        assert tranchenmodell != null;
        assert tranchenmodell.getTranchen().size() == 2;

        tranchenmodell.getTranchen().clear();

        System.out.println("*********** START DELETE");
        em.flush();
        System.out.println("*********** END DELETE");

        em.clear();

        tranchenmodell = em.find(Tranchenmodell.class, 1951L);
        assert tranchenmodell != null;

        Tranche tranche1 = em.find(Tranche.class, 1951L);
        assert tranche1 == null;
        Tranche tranche2 = em.find(Tranche.class, 1952L);
        assert tranche2 == null;
    }


    /**
     * This test will fail with (foreign key) ConstraintViolationException:
     * Tranchenmodell is beeing deteted before Tranche.
     */
    @Test
    public void removeOrphanTranchenmodell() {
        Preisregelung preisregelung = em.find(Preisregelung.class, 17960L);
        assert preisregelung != null;
        assert preisregelung.getTranchenmodell() != null;

        preisregelung.getTranchenmodell().setPreisregelung(null);
        preisregelung.setTranchenmodell(null);

        System.out.println("*********** START DELETE");
        em.flush(); // ConstraintViolationException: Tranchenmodell is beeing deteted before Tranche
        System.out.println("*********** END DELETE");
        em.clear();

        Tranchenmodell tranchenmodell = em.find(Tranchenmodell.class, 1951L);
        assert tranchenmodell == null;
        Tranche tranche1 = em.find(Tranche.class, 1951L);
        assert tranche1 == null;
        Tranche tranche2 = em.find(Tranche.class, 1952L);
        assert tranche2 == null;
    }


    @Test
    public void removePreisregelung() {
        Preisregelung preisregelung = em.find(Preisregelung.class, 17960L);
        assert preisregelung != null;
        //assert preisregelung.getTranchenmodell() != null;

        em.remove(preisregelung);

        System.out.println("*********** START DELETE");
        em.flush();
        System.out.println("*********** END DELETE");
        em.clear();

        preisregelung = em.find(Preisregelung.class, 17960L);
        assert  preisregelung == null;
        Tranchenmodell tranchenmodell = em.find(Tranchenmodell.class, 1951L);
        assert tranchenmodell == null;
        Tranche tranche1 = em.find(Tranche.class, 1951L);
        assert tranche1 == null;
        Tranche tranche2 = em.find(Tranche.class, 1952L);
        assert tranche2 == null;
    }

}
