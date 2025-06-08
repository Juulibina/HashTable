public class HashEncadeado1 extends HashTable {

    private Node[] tabelaEncadeada;

    public HashEncadeado1() {
        super(32); // capacidade da tabela
        tabelaEncadeada = new Node[capacidade];
    }

    @Override
    public void inserir(String chave) {
        int indice = calcularIndice(chave);

        Node atual = tabelaEncadeada[indice];
        if (atual == null) {
            tabelaEncadeada[indice] = new Node(chave);
            tamanho++;
        } else {
            // Já tem algo na posição = colisão
            colisoes++;
            // Insere no início (ou final, como preferir)
            Node novo = new Node(chave);
            novo.proximo = atual;
            tabelaEncadeada[indice] = novo;
            tamanho++;
        }
    }

    @Override
    public boolean buscar(String chave) {
        int indice = calcularIndice(chave);
        Node atual = tabelaEncadeada[indice];

        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    // Sobrescrevendo o modo de imprimir
    @Override
    public void imprimirTabela() {
        for (int i = 0; i < capacidade; i++) {
            System.out.print("[" + i + "] ");
            Node atual = tabelaEncadeada[i];
            while (atual != null) {
                System.out.print(atual.chave + " -> ");
                atual = atual.proximo;
            }
            System.out.println("null");
        }
    }

    @Override
    protected int calcularIndice(String chave) {
        long hash = 5381;
        for (int i = 0; i < chave.length(); i++) {
            hash = ((hash << 5) + hash) + chave.charAt(i);
        }
        return (int) Math.abs(hash % capacidade);
    }

    @Override
    public void imprimirQuantidadePorIndice()
    {
        for(int i = 0; i < capacidade; i++)
        {
            int contador = 0;
            Node atual = tabelaEncadeada[i];
            while (atual != null)
            {
                contador++;
                atual = atual.proximo;
            }
            System.out.println("[" + i + "] tem" + contador);
        }

    }
}
