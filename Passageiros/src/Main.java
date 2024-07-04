
import java.io.*;
import java.util.*;

/**
 * @author julio_busarello
 */
public class Main {

    public static Scanner ler = new Scanner(System.in);
    public static ArrayList<Onibus> listaOnibus = new ArrayList();
    /*public static ArrayList<Linha> listaLinha = new ArrayList();
    public static ArrayList<Viagem> listaViagem = new ArrayList();*/
    //public static Onibus[] onibus = new Onibus[1200];
    public static Linha[] linha = new Linha[15];
    public static Viagem[] viagem = new Viagem[20];

    public static int nLinha = 0;

    public static void main(String[] args) throws IOException {
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

    public static void criarOnibus() throws IOException {
        String placa;
        int capMaxima;

        System.out.println("Informe a placa do onibus (XXX-0000): ");
        placa = ler.next();
        System.out.println("Informe a capacidade do onibus: ");
        capMaxima = ler.nextInt();
        System.out.println("Onibus cadastrado!");

        Onibus onibus = new Onibus(placa,capMaxima);
        listaOnibus.add(onibus);
        
        FileWriter arquivo = new FileWriter("registroOnibus.txt", true);
        PrintWriter gravador = new PrintWriter(arquivo);
        gravador.println(onibus);
        gravador.close();
    }

    public static void linhas() throws IOException {
        int nParadas;
        String terminal;

        System.out.println("Informe o numero de paradas da linha: ");
        nParadas = Integer.parseInt(ler.nextLine().trim());
        System.out.println("Informe o terminal da linha: ");
        terminal = ler.nextLine();

        linha[nLinha] = new Linha(nParadas, terminal);

        nLinha++;
    }

    public static void viagens() throws IOException {
        int id = 0;
        String data;
        String hora;
        int nLinha;
        int nViagem = 0;
        int verify = 0;

        for (int i = 0; i < onibus.length; i++) {
            if (onibus[i] != null) {
                System.out.println("-------------------------------------");
                System.out.println("Id do onibus: " + (i + 1));
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
            id = Integer.parseInt(ler.nextLine().trim());
            if (onibus[id - 1] != null) {
                verify = 0;

                for (int i = 0; i < linha.length; i++) {
                    if (linha[i] != null) {
                        System.out.println("-------------------------------------");
                        System.out.println("Numero da linha: " + (i + 1));
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
                    nLinha = Integer.parseInt(ler.nextLine().trim());
                    System.out.println("Informe a data da viagem: ");
                    data = ler.nextLine();
                    System.out.println("Informe a hora da viagem: ");
                    hora = ler.nextLine();

                    // Onibus onibus, Linha linha, String data, String hora
                    viagem[nViagem] = new Viagem(onibus[id - 1], linha[nLinha - 1], data, hora);
                    nViagem++;
                    System.out.println("Viagem cadastrada com sucesso!");
                }
            } else {
                System.err.println("Onibus nao encontrado");
            }
        }
    }

    public static void decorrer() throws IOException {
        int embarque;
        int desembarque;
        int verify = 0;
        int nViagem;

        for (int i = 0; i < viagem.length; i++) {
            if (viagem[i] != null) {
                System.out.println((i + 1) + ". Data: " + viagem[i].getData() + ", Hora: " + viagem[i].getHora() + ", Onibus: " + viagem[i].getOnibus().getPlaca() + ",Linha: " + viagem[i].getLinha().getTerminal());
            } else {
                verify++;
            }
        }
        if (verify == viagem.length) {
            System.err.println("Nenhuma viagem cadastrada");
        } else {
            System.out.println("Informe o numero da viagem: ");
            nViagem = Integer.parseInt(ler.nextLine().trim());

            if (viagem[nViagem - 1] != null) {
                for (int i = 0; i < viagem[nViagem - 1].getLinha().getnParadas(); i++) {
                    System.out.println("Quantos passageiros embarcaram? ");
                    embarque = Integer.parseInt(ler.nextLine().trim());
                    viagem[nViagem - 1].getOnibus().embarcar(embarque);
                    viagem[nViagem - 1].embarcar(embarque);
                    if (i != 0) {
                        System.out.println("Informe quantas pessoas desembarcaram: ");
                        desembarque = Integer.parseInt(ler.nextLine().trim());
                        viagem[nViagem - 1].getOnibus().desembarcar(desembarque);
                    }
                }
                System.out.println("Passaram na viagem: " + viagem[nViagem - 1].getpassageirosAtual());
            } else {
                System.err.println("Viagem nao encontrada");
            }
        }
    }
}
