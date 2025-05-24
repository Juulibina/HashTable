import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> nomes = LoaderNames.carregarNomes("C:\\Users\\julia\\Downloads\\nomes.txt");

            System.out.println("Total de nomes lidos: " + nomes.size());

            //HashEncadeado1 hash = new HashEncadeado1();
            HashEncadeado2 hash = new HashEncadeado2();
            for (String nome : nomes) {
                hash.inserir(nome);
            }

            //função de tempo
            long inicioBusca = System.nanoTime();
            int encontrados = 0;
            for (String nome : nomes) {
                if (hash.buscar(nome)) {
                    encontrados++;
                }
            }
            long fimBusca = System.nanoTime();
            double tempoBuscaMs = (fimBusca - inicioBusca) / 1_000_000.0;


            System.out.println("Tempo total de busca: " + tempoBuscaMs + " ms");
            System.out.println("Tamanho final da tabela: " + hash.getTamanho());
            System.out.println("Total de colisões: " + hash.getColisoes());
            hash.imprimirTabela();


        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
        }


    }
}
