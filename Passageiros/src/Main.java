
import java.util.Scanner;

/**
 * @author julio_busarello
 */
public class Main {

    public static Scanner ler = new Scanner(System.in);
    public static Onibus[] onibus = new Onibus[1200];
    public static Linha[] linha = new Linha[15];
    public static Viagem[] viagem = new Viagem[20];

    public static int nLinha = 0;

    public static void main(String[] args) {
        int option;
        do {
            System.out.println("============ MENU ============");
            System.out.println("= 1- Cadastrar um onibus     =");
            System.out.println("= 2- Cadastrar uma linha     =");
            System.out.println("= 3- Cadastrar uma viagem    =");
            System.out.println("= 4- Decorrer uma viagem     =");
            System.out.println("= 0- Sair                    =");
            System.out.println("==============================");
            option = ler.nextInt();
            switch (option) {
                case 1:
                    criarOnibus();
                    break;
                case 2:
                    linhas();
                    break;
                case 3:
                    viagens();
                    break;
                case 4:
                    decorrer();
                    break;
                default:
                    throw new AssertionError();
            }
        } while (option != 0);
    }

    public static void linhas() {
        int nParadas;
        String terminal;

        System.out.println("Informe o numero de paradas da linha: ");
        nParadas = ler.nextInt();
        terminal = ler.nextLine();
        System.out.println("Informe o terminal da linha: ");
        terminal = ler.nextLine();

        linha[nLinha] = new Linha(nParadas, terminal);

        nLinha++;
        /*linha[1] = new Linha(5, "Centro");
        linha[2] = new Linha(8, "Araponguinhas");
        linha[3] = new Linha(6, "Tiroleses");*/
    }

    public static void criarOnibus() {
        String placa;
        int capMaxima;

        System.out.println("Informe a placa do onibus (XXX-0000): ");
        placa = ler.next();
        System.out.println("Informe a capacidade do onibus: ");
        capMaxima = ler.nextInt();

        onibus[0] = new Onibus(placa, capMaxima);
    }

    public static void viagens() {
        int id = 0;
        String data;
        String hora;
        int nLinha;
        int nViagem = 0;
        int verify = 0;

        for (int i = 0; i < onibus.length; i++) {
            if (onibus[i] != null) {
                System.out.println("-------------------------------------");
                System.out.println("Id do onibus: " + i);
                System.out.println("Placa do onibus: " + onibus[i].getPlaca());
            } else {
                verify++;
            }
        }
        if (verify == onibus.length) {
            System.err.println("Nenhum onibus cadastrado para realizar viagens");
        } else {
            System.out.println("-------------------------------------");
            System.out.println("Informe o ID do onibus: ");
            id = ler.nextInt();
            data = ler.nextLine();
            if (onibus[id] != null) {
                System.out.println("Informe a data da viagem: ");
                data = ler.nextLine();
                System.out.println("Informe a hora da viagem: ");
                hora = ler.nextLine();
                verify = 0;

                for (int i = 0; i < linha.length; i++) {
                    if (linha[i] != null) {
                        System.out.println("-------------------------------------");
                        System.out.println("Numero da linha: " + i);
                        System.out.println("Terminal da linha: " + linha[i].getTerminal());
                        System.out.println("Numero de paradas: " + linha[i].getnParadas());
                    } else {
                        verify++;
                    }
                }
                if (verify == linha.length) {
                    System.err.println("Nenhuma linha cadastrada");
                } else {
                    System.out.println("-------------------------------------");
                    System.out.println("Informe o numero da linha: ");
                    nLinha = ler.nextInt();

                    // Onibus onibus, Linha linha, String data, String hora
                    viagem[nViagem] = new Viagem(onibus[id], linha[nLinha], data, hora);
                    nViagem++;
                    System.out.println("Viagem cadastrada com sucesso!");
                }
            } else {
                System.err.println("Onibus nao encontrado");
            }
        }
    }

    public static void decorrer() {
        int embarque;
        int desembarque;
        int verify = 0;
        int nViagem;

        for (int i = 0; i < viagem.length; i++) {
            if (viagem[i] != null) {
                System.out.println(i + ". Data: " + viagem[i].getData() + ", Hora: " + viagem[i].getHora() + ", Onibus: " + viagem[i].getOnibus().getPlaca() + ",Linha: " + viagem[i].getLinha().getTerminal());
            } else {
                verify++;
            }
        }
        if (verify == viagem.length) {
            System.err.println("Nenhuma viagem cadastrada");
        } else {
            System.out.println("Informe o numero da viagem: ");
            nViagem = ler.nextInt();
            
            for (int i = 0; i < viagem[0].getLinha().getnParadas(); i++) {
                System.out.println("Quantos passageiros embarcaram? ");
                embarque = ler.nextInt();
                viagem[0].getOnibus().embarcar(embarque);
                viagem[0].embarcar(embarque);
                if (i != 0) {
                    System.out.println("Informe quantas pessoas desembarcaram: ");
                    desembarque = ler.nextInt();
                    viagem[0].getOnibus().desembarcar(desembarque);
                }
            }
            System.out.println("Passaram na viagem: " + viagem[0].getpassageirosAtual());
        }
    }
}
