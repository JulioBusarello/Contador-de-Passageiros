
import java.io.*;
import java.util.*;

/**
 * @author julio_busarello
 */
public class Main {

    public static Scanner ler = new Scanner(System.in);
    public static ArrayList<Onibus> listaOnibus = new ArrayList();
    public static ArrayList<Linha> listaLinha = new ArrayList();
    public static ArrayList<Viagem> listaViagem = new ArrayList();

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
                    cadastrarOnibus();
                    break;
                case 2:
                    cadastrarLinha();
                    break;
                case 3:
                    cadastrarViagem();
                    break;
                case 4:
                    decorrerViagem();
                    break;
                default:
                    throw new AssertionError();
            }
        } while (option != 0);
    }

    public static void cadastrarOnibus() throws IOException {
        String placa;
        int capMaxima;

        System.out.println("Informe a placa do onibus (XXX-0000): ");
        placa = ler.next();
        System.out.println("Informe a capacidade do onibus: ");
        capMaxima = ler.nextInt();
        System.out.println("Onibus cadastrado!");

        Onibus onibus = new Onibus(placa, capMaxima);
        listaOnibus.add(onibus);

        FileWriter arquivo = new FileWriter("registroOnibus.txt", true);
        PrintWriter gravador = new PrintWriter(arquivo);
        gravador.println(onibus);
        gravador.close();
    }

    public static void cadastrarLinha() throws IOException {
        int nParadas;
        String terminal;

        System.out.println("Informe o numero de paradas da linha: ");
        nParadas = ler.nextInt();
        terminal = ler.nextLine();
        System.out.println("Informe o terminal da linha: ");
        terminal = ler.nextLine();

        Linha linha = new Linha(nParadas, terminal);

        listaLinha.add(linha);

        FileWriter arquivo = new FileWriter("registroLinha.txt", true);
        PrintWriter gravador = new PrintWriter(arquivo);
        gravador.println(linha);
        gravador.close();
    }

    public static void cadastrarViagem() throws IOException {
        String data;
        String hora;
        int idOnibus;
        int nLinha;

        if (listaOnibus.isEmpty()) {
            System.err.println("Nenhum onibus cadastrado!");
        } else {
            System.out.println("Selecione um onibus: ");
            for (int i = 0; i < listaOnibus.size(); i++) {
                System.out.println((i + 1) + "." + listaOnibus.get(i).getPlaca());
            }
            System.out.print("ID do onibus: ");
            idOnibus = ler.nextInt();
            Onibus onibusViagem = listaOnibus.get(idOnibus - 1);

            if (listaLinha.isEmpty()) {
                System.err.println("Nenhuma linha cadastrada!");
            } else {
                System.out.println("Selecione uma linha: ");
                for (int i = 0; i < listaOnibus.size(); i++) {
                    System.out.println((i + 1) + "." + listaLinha.get(i).getTerminal());
                }
                System.out.print("Informe o numero da linha: ");
                nLinha = Integer.parseInt(ler.nextLine().trim());
                Linha linhaViagem = listaLinha.get(nLinha - 1);

                System.out.println("Informe a data da viagem: ");
                data = ler.nextLine();
                System.out.println("Informe a hora da viagem: ");
                hora = ler.nextLine();

                Viagem viagem = new Viagem(onibusViagem, linhaViagem, data, hora);
                listaViagem.add(viagem);

                System.out.println("A viagem foi cadastrada com sucesso!");

                decorrerViagem(viagem, 0);

                FileWriter arquivo = new FileWriter("registroViagem.txt", true);
                PrintWriter gravador = new PrintWriter(arquivo);
                gravador.println(viagem);
                gravador.close();
            }
        }
    }

    public static void decorrerViagem(Viagem viagem, int totalEmbarque) throws IOException {
        int embarque;

        if (listaViagem.isEmpty()) {
            System.err.println("Nenhuma viagem cadastrada!");
        } else {
            System.out.println("Decorrendo viagem...");
            for (int i = 0; i < viagem.getLinha().getnParadas(); i++) {
                if (i < 1) {
                    System.out.println("Informe quantos passageiros embarcaram: ");
                    embarque = ler.nextInt();
                    viagem.getOnibus().embarcar(embarque);
                } else {
                    System.out.println("Informe quantos passageiros embarcaram: ");
                    embarque = ler.nextInt();
                    viagem.getOnibus().embarcar(embarque);

                    System.out.println("Informe quantos passageiros desembarcaram: ");
                    viagem.getOnibus().desembarcar(ler.nextInt());
                }
                totalEmbarque += embarque;
            }
            System.out.println("Viagem concluida, passaram " + totalEmbarque + " passageiros na viagem.");
        }

    }
}
