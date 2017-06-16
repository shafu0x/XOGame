package shafou.xospiel.SpielLogik;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shafou.xospiel.PlayingField.OToken;
import shafou.xospiel.PlayingField.Token;
import shafou.xospiel.PlayingField.XToken;
import shafou.xospiel.View.XOPlayingField;

/**
 *
 * Diese Klasse enthält die X/O Spiel Steuerung.
 *
 * @author Sharif Elfouly
 * @version 1.0
 *
 * Änderungshistorie:
 * 1) 11.06.2017 ELF Klasse erstellt.
 */

public final class XOGame {

    /** Die X/O Spiel Referenz */
    @SuppressLint("StaticFieldLeak")
    private static XOGame xOGame;

    /** Enthält die bereits gespielten Züge */
    private static List<Turn> gespielteZuege;

    /** Das Spielfeld View */
    private static XOPlayingField xOSpielView;

    /** Kontext der aufrufenden Aktivität */
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    /** Anzahl der Spalten und Reihen dieses Spiels */
    private static int spalten;

    /** Gibt die Minimale Anzahl von Steinen zum Gewinn an */
    private static final int MINIMALE_ANZAHL_ZUM_GEWINN = 3;

    private XOGame() {}

    /**
     * Gibt die verfügbaren Spielarten an.
     *
     * Eine Spielart besteht aus einem String der die Spielart beschreibt,
     * die Anzahl von Reihen und Spalten des Spielfelds und die verschiedenen
     * möglichen Anzahlen von Spiel Steinen zum Gewinn.
     */
    public enum Mode {

        DREI("3 x 3", 3),
        VIER("4 x 4", 4),
        FUENF("5 x 5", 5);

        private final String name;
        private final int reihenSpalten;
        private final int[] anzahlSteineZumGewinn;

        Mode(String name, int reihenSpalten) {

            this.name = name;
            this.reihenSpalten = reihenSpalten;
            this.anzahlSteineZumGewinn = berechneAnzahlSteineZumGewinn();
        }

        /**
         * Berechnet zu einer Spielart, die Möglichen Anzahl von Spielsteinen
         * zum Gewinn.
         *
         * @return Möglichkeiten zum Gewinn
         */
        public int[] berechneAnzahlSteineZumGewinn() {

            int[] gewinnMoeglichkeiten = new int[reihenSpalten - 2];

            for(int i = 0; i < reihenSpalten - 2; i++) {

                gewinnMoeglichkeiten[i] = MINIMALE_ANZAHL_ZUM_GEWINN + i;
            }

            return gewinnMoeglichkeiten;
        }

        /**
         * Gibt alle Spiel Modi Namen zurück.
         *
         * @return Liste von Spiel Namen
         */
        public static List<String> getModeNames() {

            List<String> gameModeNames = new ArrayList<>();

            for(Mode gameMode: values()) {

                gameModeNames.add(gameMode.getName());
            }

            return gameModeNames;
        }

        public int[] getAnzahlSteineZumGewinn() {
            return anzahlSteineZumGewinn;
        }

        public int getReihenSpalten() {
            return reihenSpalten;
        }

        public String getName() {
            return name;
        }

    }

    /**
     * Das X/O Spiel wurde als Singelton implementiert.
     * Zur Laufzeit existiert nur 1 Spiel.
     *
     * @param context Kontext der aufrufenden Aktivität
     * @param columnsAndRows
     * @return X/O Spiel Objekt
     */
    public static XOGame getInstance(Context context, int columnsAndRows) {

        if(xOGame == null) {

            xOGame = new XOGame();
            XOGame.context = context;
            xOSpielView = new XOPlayingField(context, columnsAndRows);
            gespielteZuege = new ArrayList<>();
            XOGame.spalten = spalten;
        }

        return xOGame;
    }

    /**
     * Wird aufgerufen wenn ein neuer Turn im Spiel gesetzt wurde.
     * Nur Züge auf neuen Spielfeldern werden akzeptiert.
     *
     * @param turn Gesetzter Turn.
     */
    public static void zugGespielt(Turn turn) {

        if(!gespielteZuege.contains(turn)) {

            gespielteZuege.add(turn);
            if(feldAuswerten()) {

                new AlertDialog.Builder(context)
                        .setTitle("Das Spiel ist vorbei")
                        .setMessage("Spiel neu starten?")
                        .setCancelable(false)
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                XOGame.neuStarten(context, 3);
                            }
                        }).show();
            }
        }
    }

    /**
     * Gibt alle Züge der X Spielsteine zurück.
     *
     * @return Liste von Zügen
     */
    private static List<Turn> getXZuege() {

        List<Turn> xZuege = new ArrayList<>();

        for(Turn turn : gespielteZuege) {

            if(turn.getToken() instanceof XToken) {

                xZuege.add(turn);
            }
        }

        return xZuege;
    }

    /**
     * Gibt alle Züge der O Spielsteine zurück.
     *
     * @return Liste von Zügen
     */
    private static List<Turn> getOZuege() {

        List<Turn> oZuege = new ArrayList<>();

        for(Turn turn : gespielteZuege) {

            if(turn.getToken() instanceof OToken) {

                oZuege.add(turn);
            }
        }

        return oZuege;
    }

    /**
     * Auswertung des aktuellen Spiels.
     *
     * @return <code>true</code> falls das Spiel gewonnen wurde.
     */
    private static boolean feldAuswerten() {

        List<Turn> gespielteZuege;

        if(gibLetztenZug().getToken() instanceof XToken) {

            gespielteZuege = getXZuege();
        } else {

            gespielteZuege = getOZuege();
        }

        List<Position> gespieltePositionen = new ArrayList<>();
        for(Turn turn : gespielteZuege) {

            gespieltePositionen.add(turn.getField().getPosition());
        }

        return Standings.hatGewonnen(gespieltePositionen, spalten);
    }



    /**
     * Gibt den Token an, der momentan gesetzt werden kann.
     *
     * @return Aktueller Token
     */
    public static Token gibAktuellenSpielstein() {

        if(gespielteZuege.size() > 0) {

            if(gibLetztenZug().getToken() instanceof XToken) {

                return new OToken(context);
            } else {

                return new XToken(context);
            }
        } else {

            return gibZufaelligenSpielstein();
        }
    }

    /**
     * Gibt den zu letzt gespielten Turn zurück.
     *
     * @return Letzter Turn
     */
    public static Turn gibLetztenZug() {

        return gespielteZuege.get(gespielteZuege.size() - 1);
    }

    /**
     * Wird am Anfang jedes Spiels benutzt, um zu ermitteln welcher
     * Token anfängt.
     *
     * @return Gibt einen zufäligen Token zurück
     */
    private static Token gibZufaelligenSpielstein() {

        List<Token> token = new ArrayList<>();
        token.add(new XToken(context));
        token.add(new OToken(context));

        Random random = new Random();
        return token.get(random.nextInt(2));
    }

    /** Gibt das Spielfeld zurück */
    public View spielfeld() {

        return xOSpielView;
    }

    /**
     * Startet das Spiel neu.
     *
     * @param context Kontext der aufrufenden Aktivität
     * @param columnsAndRows
     * @return Eine neue Spiel Referenz.
     */
    public static XOGame neuStarten(Context context, int columnsAndRows) {

        XOGame.spalten = spalten;
        gespielteZuege = new ArrayList<>();
        xOSpielView.restart(columnsAndRows);
        return getInstance(context, columnsAndRows);
    }

    public static List<Turn> getGespielteZuege() {
        return gespielteZuege;
    }
}