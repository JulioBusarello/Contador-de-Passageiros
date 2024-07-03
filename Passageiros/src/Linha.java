
/**
 * @author julio_busarello
 */
public class Linha {

    private int nParadas;
    private String terminal;

    // ----- Construtor
    public Linha() {

    }

    public Linha(int nParadas, String terminal) {
        this.nParadas = nParadas;
        this.terminal = terminal;
    }

    // ----- Getter and Setter
    public int getnParadas() {
        return nParadas;
    }

    public String getTerminal() {
        return terminal;
    }

    // ----- Métodos
}
