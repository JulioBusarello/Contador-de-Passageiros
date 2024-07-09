
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
        try {
            // Recuperar todos os dados registrados anteriormente
            recuperarOnibus();
            recuperarLinha();

        } catch (Exception e) {
            System.err.println("Erro ao recuperar arquivos: " + e.getMessage());
        }

        int option;
        do {
            System.out.println("============ MENU ============");
            System.out.println("= 1- Cadastrar um onibus     =");
            System.out.println("= 2- Cadastrar uma linha     =");
            System.out.println("= 3- Cadastrar uma viagem    =");
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
                nLinha = ler.nextInt();
                Linha linhaViagem = listaLinha.get(nLinha - 1);

                System.out.println("Informe a data da viagem: ");
                data = ler.next();

                System.out.println("Informe a hora da viagem: ");
                hora = ler.next();

                Viagem viagem = new Viagem(onibusViagem, linhaViagem, data, hora);
                listaViagem.add(viagem);

                System.out.println("A viagem foi cadastrada com sucesso!");

                int embarque;
                int totalEmbarque = 0;

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

                FileWriter arquivo = new FileWriter("registroViagem.txt", true);
                PrintWriter gravador = new PrintWriter(arquivo);
                gravador.println(viagem);
                gravador.close();

            }
        }
    }

    private static void recuperarOnibus() throws IOException {
        String aarq = "registroOnibus.txt";
        String linha = "";
        File arq = new File(aarq);
        if (arq.exists()) {

            try {
                FileReader abrindo = new FileReader(aarq);
                BufferedReader leitor = new BufferedReader(abrindo);
                while (true) {
                    linha = leitor.readLine();
                    if (linha == null) {
                        break;
                    }
                    String[] linhaAtualOnibusArquivo = linha.split(",");
                    Onibus onibus = new Onibus(linhaAtualOnibusArquivo[0], Integer.parseInt(linhaAtualOnibusArquivo[1]));
                    listaOnibus.add(onibus);
                }
                leitor.close();
            } catch (Exception erro) {
                System.err.println("Erro ao recuperar dados do arquivo registroOnibus.txt: " + erro.getMessage());
            }

        }
    }

    private static void recuperarLinha() throws IOException {
        String aarq = "registroLinha.txt";
        String linhaCod = "";
        File arq = new File(aarq);
        if (arq.exists()) {

            try {
                FileReader abrindo = new FileReader(aarq);
                BufferedReader leitor = new BufferedReader(abrindo);
                while (true) {
                    linhaCod = leitor.readLine();
                    if (linhaCod == null) {
                        break;
                    }
                    String[] linhaAtualLinhaArquivo = linhaCod.split(",");
                    Linha linha = new Linha(Integer.parseInt(linhaAtualLinhaArquivo[0]), linhaAtualLinhaArquivo[1]);
                    listaLinha.add(linha);
                }
                leitor.close();
            } catch (Exception erro) {
                System.err.println("Erro ao recuperar dados do arquivo registroOnibus.txt: " + erro.getMessage());
            }

        }
    }

}
