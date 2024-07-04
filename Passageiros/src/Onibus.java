
/**
 * @author julio_busarello
 */
public class Onibus {

    private String placa;
    private int capMaxima;
    private int passageirosAtual = 0;

    // ----- Construtor
    public Onibus() {

    }

    public Onibus(String placa, int capMaxima) {
        this.placa = placa;
        this.capMaxima = capMaxima;
    }

    // ----- Getters and Setters
    public String getPlaca() {
        return placa;
    }

    public int getCapMaxima() {
        return capMaxima;
    }

    public int getPassageirosAtual() {
        return passageirosAtual;
    }

    // ----- Métodos
    public void embarcar(int passageiros) {
        int sobra = 0;
        if (passageiros > this.capMaxima - this.passageirosAtual) {
            sobra = passageiros - (this.capMaxima - this.passageirosAtual);
            this.passageirosAtual = this.capMaxima;
            System.out.println("O onibus esta com a lotacao maxima e ficaram de fora " + sobra + " passageiros");
        } else {
            this.passageirosAtual += passageiros;
            System.out.println("Subiram " + passageiros + " passageiros");
            System.out.println("Agora o onibus possui " + this.passageirosAtual + " passageiros");
        }
    }

    public void desembarcar(int passageiros) {
        if (passageiros > this.passageirosAtual) {
            System.out.println("Desceram " + this.passageirosAtual + " passageiros");
            this.passageirosAtual = 0;
        } else if (this.passageirosAtual == 0) {
            System.out.println("O onibus esta vazio");
        } else {
            this.passageirosAtual -= passageiros;
            System.out.println("Desceram " + passageiros + " passageiros");
            System.out.println("Agora o onibus possui " + this.passageirosAtual + " passageiros");
        }
    }

    public String toString() {
        return this.placa + "," + this.capMaxima;
    }

}
