package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest {
    // private static Map<String, Geldbetrag> WERTEMENGE = new HashMap<>();

    @Test
    public void testeKonstruktoren1() {
        Geldbetrag g = new Geldbetrag(7550);
        assertEquals(75, g.getEuroAnteil());
        assertEquals(50, g.getCentAnteil());
    }

    /**
     * @Test
     *       public void testeKonstruktoren2()
     *       {
     *       Geldbetrag g1 = new Geldbetrag(75, 50);
     *       assertEquals(75, g1.getEuroAnteil());
     *       assertEquals(50, g1.getCentAnteil());
     *       }
     */

    @Test
    public void testeSelect() {
        Geldbetrag g1 = Geldbetrag.select(200, 50);
        Geldbetrag g2 = Geldbetrag.select(20, 50);
        Geldbetrag g3 = Geldbetrag.select(020, 5);
        Geldbetrag g4 = Geldbetrag.select(200, 50);
        Geldbetrag g5 = Geldbetrag.select(20, 50);
        Geldbetrag g6 = Geldbetrag.select(020, 5);
        assertEquals(g4, g1);
        assertEquals(g5, g2);
        assertEquals(g6, g3);
    }

    @Test
    public void testeIstGueltigerEuroAnteil() {
        assertFalse(Geldbetrag.istGueltigerEuroAnteil(-50));
        assertTrue(Geldbetrag.istGueltigerEuroAnteil(1));
        assertTrue(Geldbetrag.istGueltigerEuroAnteil(10));
    }

    @Test
    public void testeIstGueltigerCentAnteil() {
        assertFalse(Geldbetrag.istGueltigerCentAnteil(-50));
        assertFalse(Geldbetrag.istGueltigerCentAnteil(100));
        assertTrue(Geldbetrag.istGueltigerCentAnteil(1));
        assertTrue(Geldbetrag.istGueltigerCentAnteil(10));
    }

    @Test
    public void testeAddiere1() {
        Geldbetrag g1 = Geldbetrag.select(5, 01);
        Geldbetrag g2 = Geldbetrag.select(10, 50);
        Geldbetrag g3 = Geldbetrag.select(10, 70);

        Geldbetrag g4 = Geldbetrag.select(15, 51);
        assertEquals(g4, Geldbetrag.addiere(g1, g2));

        Geldbetrag g5 = Geldbetrag.select(21, 20);
        assertEquals(g5, Geldbetrag.addiere(g2, g3));
    }

    @Test
    public void testeAddiere2() {
        Geldbetrag g1 = Geldbetrag.select(5, 01);
        Geldbetrag g2 = Geldbetrag.select(10, 50);
        Geldbetrag g3 = Geldbetrag.select(10, 70);

        Geldbetrag g4 = Geldbetrag.select(15, 51);
        g1 = g1.addiere(g2);
        assertEquals(g4, g1);

        Geldbetrag g5 = Geldbetrag.select(21, 20);
        g2 = g2.addiere(g3);
        assertEquals(g5, g2);
    }

    @Test
    public void testeIstGroesserGleich() {
        Geldbetrag g1 = Geldbetrag.select(5, 01);
        Geldbetrag g2 = Geldbetrag.select(10, 50);
        Geldbetrag g3 = Geldbetrag.select(10, 70);
        Geldbetrag g4 = Geldbetrag.select(10, 70);

        assertTrue(Geldbetrag.istGroesserGleich(g2, g1));
        assertTrue(Geldbetrag.istGroesserGleich(g3, g1));
        assertTrue(Geldbetrag.istGroesserGleich(g4, g3));

        assertFalse(Geldbetrag.istGroesserGleich(g1, g2));
        assertFalse(Geldbetrag.istGroesserGleich(g2, g3));
        assertFalse(Geldbetrag.istGroesserGleich(g1, g4));
    }

    @Test
    public void testeBerechneDifferenz() {
        Geldbetrag g1 = Geldbetrag.select(5, 01);
        Geldbetrag g2 = Geldbetrag.select(10, 50);
        Geldbetrag g3 = Geldbetrag.select(10, 70);

        assertEquals(Geldbetrag.select(5, 49),
                Geldbetrag.berechneDifferenz(g1, g2));
        // assertEquals(Geldbetrag.select(0, 0),Geldbetrag.berechneDifferenz(g1, g1));
        // assertEquals(Geldbetrag.select(0, 20),Geldbetrag.berechneDifferenz(g2, g3));
        assertEquals(Geldbetrag.select(5, 69),
                Geldbetrag.berechneDifferenz(g1, g3));
    }

    @Test
    public void testeMultipliziere() {
        Geldbetrag g1 = Geldbetrag.select(5, 10);
        // Geldbetrag g2 = Geldbetrag.select(10, 00);
        Geldbetrag g3 = Geldbetrag.select(10, 50);

        assertEquals(Geldbetrag.select(10, 20), g1.multipliziere(2));
        assertEquals(Geldbetrag.select(31, 50), g3.multipliziere(3));
        assertEquals(Geldbetrag.select(25, 50), g1.multipliziere(5));
        // assertEquals(Geldbetrag.select(20, 00), g1.multipliziere(2));
    }

}
