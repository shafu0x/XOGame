package shafou.xospiel;

import org.junit.Test;

import java.util.ArrayList;

import shafou.xospiel.SpielLogik.Position;

import static org.junit.Assert.assertEquals;

/**
 *
 * Diese Klasse enthält Tests zur Berechnung gleich grosser Rechtecke
 * {@link DisplayRechtecke} zur einer bestimmten Anzahl von Reihen und Spalten.
 *
 * Die Tests werden anhand verschiedenen Display Größen durchgeführt.
 *
 * (0|0)  --------------------------------> X   (X|0)
 *        |
 *        |
 *        |
 *        |
 *        |
 *        |
 *        |
 * (0|Y)  Y                                     (X|Y)
 *
 * @author Sharif Elfouly
 * @version 1.0
 *
 * Änderungshistorie:
 * 1) 26.05.2017 ELF Test Klasse erstellt.
 */

public class DisplayRechnerTest {

    @Test
    public void layoutDisplayGeneratorTest3x3() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 120;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 120;

        DisplayRechtecke lDQ1 = new DisplayRechtecke(
                new Position(0,0),
                new Position(40,0),
                new Position(40,40),
                new Position(0,40)
        );

        DisplayRechtecke lDQ2 = new DisplayRechtecke(
                new Position(40,0),
                new Position(80,0),
                new Position(80,40),
                new Position(40,40)
        );

        DisplayRechtecke lDQ3 = new DisplayRechtecke(
                new Position(80,0),
                new Position(120,0),
                new Position(120,40),
                new Position(80,40)
        );

        DisplayRechtecke lDQ4 = new DisplayRechtecke(
                new Position(0,40),
                new Position(40,40),
                new Position(40,80),
                new Position(0,80)
        );

        DisplayRechtecke lDQ5 = new DisplayRechtecke(
                new Position(40,40),
                new Position(80,40),
                new Position(80,80),
                new Position(40,80)
        );

        DisplayRechtecke lDQ6 = new DisplayRechtecke(
                new Position(80,40),
                new Position(120,40),
                new Position(120,80),
                new Position(80,80)
        );

        DisplayRechtecke lDQ7 = new DisplayRechtecke(
                new Position(0,80),
                new Position(40,80),
                new Position(40,120),
                new Position(0,120)
        );

        DisplayRechtecke lDQ8 = new DisplayRechtecke(
                new Position(40,80),
                new Position(80,80),
                new Position(80,120),
                new Position(40,120)
        );

        DisplayRechtecke lDQ9 = new DisplayRechtecke(
                new Position(80,80),
                new Position(120,80),
                new Position(120,120),
                new Position(80,120)
        );

        ArrayList<DisplayRechtecke> generierteLayoutDisplayQuadrate
                = LayoutDisplayRechteckGenerator.teileDisplayInRechtecke(
                new LayoutDisplay(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE), 3, 3);

        ArrayList<DisplayRechtecke> testLayoutDisplayListe = new ArrayList<>();

        testLayoutDisplayListe.add(lDQ1);
        testLayoutDisplayListe.add(lDQ2);
        testLayoutDisplayListe.add(lDQ3);
        testLayoutDisplayListe.add(lDQ4);
        testLayoutDisplayListe.add(lDQ5);
        testLayoutDisplayListe.add(lDQ6);
        testLayoutDisplayListe.add(lDQ7);
        testLayoutDisplayListe.add(lDQ8);
        testLayoutDisplayListe.add(lDQ9);

        assertEquals(testLayoutDisplayListe, generierteLayoutDisplayQuadrate);
    }

    @Test
    public void layoutDisplayGeneratorTest3x3MitRealistischerDisplayGroesse() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 768;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 1280;

        DisplayRechtecke lDQ1 = new DisplayRechtecke(
                new Position(0,0),
                new Position(1f/3f * X_LAYOUT_GROESSE, 0),
                new Position(1f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(0, 1f/3f * Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ2 = new DisplayRechtecke(
                new Position(1f/3f * X_LAYOUT_GROESSE, 0),
                new Position(2f/3f * X_LAYOUT_GROESSE, 0),
                new Position(2f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ3 = new DisplayRechtecke(
                new Position(2f/3f * X_LAYOUT_GROESSE, 0),
                new Position(X_LAYOUT_GROESSE, 0),
                new Position(X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ4 = new DisplayRechtecke(
                new Position(0, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(0, 2f/3f * Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ5 = new DisplayRechtecke(
                new Position(1f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ6 = new DisplayRechtecke(
                new Position(2f/3f * X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(X_LAYOUT_GROESSE, 1f/3f * Y_LAYOUT_GROESSE),
                new Position(X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ7 = new DisplayRechtecke(
                new Position(0, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE),
                new Position(0, Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ8 = new DisplayRechtecke(
                new Position(1f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE),
                new Position(1f/3f * X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE)
        );

        DisplayRechtecke lDQ9 = new DisplayRechtecke(
                new Position(2f/3f * X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(X_LAYOUT_GROESSE, 2f/3f * Y_LAYOUT_GROESSE),
                new Position(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE),
                new Position(2f/3f * X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE)
        );

        ArrayList<DisplayRechtecke> generierteLayoutDisplayQuadrate
                = LayoutDisplayRechteckGenerator.teileDisplayInRechtecke(
                new LayoutDisplay(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE), 3, 3);

        ArrayList<DisplayRechtecke> testLayoutDisplayListe = new ArrayList<>();

        testLayoutDisplayListe.add(lDQ1);
        testLayoutDisplayListe.add(lDQ2);
        testLayoutDisplayListe.add(lDQ3);
        testLayoutDisplayListe.add(lDQ4);
        testLayoutDisplayListe.add(lDQ5);
        testLayoutDisplayListe.add(lDQ6);
        testLayoutDisplayListe.add(lDQ7);
        testLayoutDisplayListe.add(lDQ8);
        testLayoutDisplayListe.add(lDQ9);

        assertEquals(testLayoutDisplayListe.get(0), generierteLayoutDisplayQuadrate.get(0));
        assertEquals(testLayoutDisplayListe.get(1), generierteLayoutDisplayQuadrate.get(1));
        assertEquals(testLayoutDisplayListe.get(2), generierteLayoutDisplayQuadrate.get(2));
        assertEquals(testLayoutDisplayListe.get(3), generierteLayoutDisplayQuadrate.get(3));
        assertEquals(testLayoutDisplayListe.get(4), generierteLayoutDisplayQuadrate.get(4));
        assertEquals(testLayoutDisplayListe.get(5), generierteLayoutDisplayQuadrate.get(5));
        assertEquals(testLayoutDisplayListe.get(6), generierteLayoutDisplayQuadrate.get(6));
        assertEquals(testLayoutDisplayListe.get(7), generierteLayoutDisplayQuadrate.get(7));
        assertEquals(testLayoutDisplayListe.get(8), generierteLayoutDisplayQuadrate.get(8));

        assertEquals(testLayoutDisplayListe, generierteLayoutDisplayQuadrate);
    }

    @Test
    public void layoutDisplayGeneratorTest1x1() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 100;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 100;

        DisplayRechtecke lDQ1 = new DisplayRechtecke(
                new Position(0,0),
                new Position(100,0),
                new Position(100, 100),
                new Position(0, 100)
        );

        ArrayList<DisplayRechtecke> testListe = new ArrayList<>();

        testListe.add(lDQ1);

        ArrayList<DisplayRechtecke> generierteQuadrate
                = LayoutDisplayRechteckGenerator
                .teileDisplayInRechtecke(new LayoutDisplay(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE), 1, 1);

        assertEquals(testListe, generierteQuadrate);
    }

    @Test
    public void layoutDisplayGeneratorTest2x1() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 100;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 100;

        DisplayRechtecke lDQ1 = new DisplayRechtecke(
                new Position(0,0),
                new Position(50,0),
                new Position(50, 100),
                new Position(0, 100)
        );

        DisplayRechtecke lDQ2 = new DisplayRechtecke(
                new Position(50,0),
                new Position(100,0),
                new Position(100, 100),
                new Position(50, 100)
        );

        ArrayList<DisplayRechtecke> testListe = new ArrayList<>();

        testListe.add(lDQ1);
        testListe.add(lDQ2);

        ArrayList<DisplayRechtecke> generierteQuadrate
                = LayoutDisplayRechteckGenerator
                .teileDisplayInRechtecke(new LayoutDisplay(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE), 2, 1);

        assertEquals(testListe.get(0), generierteQuadrate.get(0));
        assertEquals(testListe.get(1), generierteQuadrate.get(1));

        assertEquals(testListe, generierteQuadrate);
    }

    @Test
    public void layoutDisplayGeneratorTest1x3() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 120;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 120;

        DisplayRechtecke lDQ1 = new DisplayRechtecke(
                new Position(0, 0),
                new Position(120, 0),
                new Position(120, 40),
                new Position(0, 40)
        );

        DisplayRechtecke lDQ2 = new DisplayRechtecke(
                new Position(0, 40),
                new Position(120, 40),
                new Position(120, 80),
                new Position(0, 80)
        );

        DisplayRechtecke lDQ3 = new DisplayRechtecke(
                new Position(0, 80),
                new Position(120, 80),
                new Position(120, 120),
                new Position(0, 120)
        );

        ArrayList<DisplayRechtecke> testListe = new ArrayList<>();

        testListe.add(lDQ1);
        testListe.add(lDQ2);
        testListe.add(lDQ3);

        ArrayList<DisplayRechtecke> generierteQuadrate
                = LayoutDisplayRechteckGenerator
                .teileDisplayInRechtecke(new LayoutDisplay(120, 120), 1, 3);

        assertEquals(testListe.get(0), generierteQuadrate.get(0));
        assertEquals(testListe.get(1), generierteQuadrate.get(1));

        assertEquals(testListe, generierteQuadrate);
    }

    @Test
    public void layoutDisplayGeneratorGleichGrosseRechteckeTest() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 100;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 100;

        final float RECHTECK_GROESSE = 5000;

        ArrayList<DisplayRechtecke> generierteQuadrate
                = LayoutDisplayRechteckGenerator
                .teileDisplayInRechtecke(new LayoutDisplay(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE), 2, 1);

        for(DisplayRechtecke displayRechtecke : generierteQuadrate) {

            float breite = displayRechtecke.getX2().getxPosition() - displayRechtecke.getX1().getxPosition();
            float hoehe = displayRechtecke.getX4().getyPosition() - displayRechtecke.getX1().getyPosition();

            assertEquals(RECHTECK_GROESSE, hoehe * breite, 0.0);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void layoutDisplayGeneratorTestNegativeParameter() {

        /** X Display Größe in Pixel */
        final float X_LAYOUT_GROESSE = 120;

        /** Y Display Größe in Pixel */
        final float Y_LAYOUT_GROESSE = 120;

        LayoutDisplayRechteckGenerator
                .teileDisplayInRechtecke(
                        new LayoutDisplay(X_LAYOUT_GROESSE, Y_LAYOUT_GROESSE), -1, -3);
    }
}
