/**
 * @author julio_busarello
 */
public class Viagem {

    private String data;
    private String hora;
    private int passageirosAtual = 0;
    private Onibus onibus;
    private Linha linha;

    // ----- Construtor
    public Viagem() {

    }

    public Viagem(Onibus onibus, Linha linha, String data, String hora) {
        this.data = data;
        this.hora = hora;
        this.onibus = onibus;
        this.linha = linha;
    }

    // ----- Getters and Setters

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getpassageirosAtual() {
        return passageirosAtual;
    }

    public Onibus getOnibus() {
        return onibus;
    }

    public Linha getLinha() {
        return linha;
    }

    // ----- Métodos
    public void embarcar(int passageiros) {
        this.passageirosAtual += passageiros;
    }
}
