import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> nomes = LoaderNames.carregarNomes("C:\\Users\\julia\\Downloads\\nomes.txt");

            System.out.println("Total de nomes lidos: " + nomes.size());

            HashAberto hash = new HashAberto();
            for (String nome : nomes) {
                hash.inserir(nome);
            }


            System.out.println("Tamanho final da tabela: " + hash.getTamanho());
            System.out.println("Total de colisões: " + hash.getColisoes());
            hash.imprimirTabela();


        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
        }


    }
}
