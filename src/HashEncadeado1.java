public class HashEncadeado1 extends HashTable {

    private Node[] tabelaEncadeada;

    public HashEncadeado1() {
        super(32);
        tabelaEncadeada = new Node[capacidade];
    }

    @Override
    public void inserir(String chave) {
        if (tamanho >= capacidade * fatorDeCarga) {
            rehash();
        }
        int indice = calcularIndice(chave);
        Node atual = tabelaEncadeada[indice];
        if (atual == null) {
            tabelaEncadeada[indice] = new Node(chave);
        } else {
            colisoes++;
            Node novo = new Node(chave);
            novo.proximo = atual;
            tabelaEncadeada[indice] = novo;
        }
        tamanho++;
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

    @Override
    protected int calcularIndice(String chave) {
        long hash = 5381;
        for (int i = 0; i < chave.length(); i++) {
            hash = ((hash << 5) + hash) + chave.charAt(i);
        }
        return (int) Math.abs(hash % capacidade);
    }

    @Override
    protected void reinserirTodos(Node[] novaTabela, int novaCapacidade) {
        for (Node head : tabelaEncadeada) {
            Node atual = head;
            while (atual != null) {
                String chave = atual.chave;
                int novoIndice = calcularIndice(chave, novaCapacidade);
                Node novo = new Node(chave);
                novo.proximo = novaTabela[novoIndice];
                novaTabela[novoIndice] = novo;
                atual = atual.proximo;
            }
        }
    }

    @Override
    protected void atualizarTabela(Node[] novaTabela) {
        this.tabelaEncadeada = novaTabela;
    }

    @Override
    public void imprimirQuantidadePorIndice() {
        for (int i = 0; i < capacidade; i++) {
            int contador = 0;
            Node atual = tabelaEncadeada[i];
            while (atual != null) {
                contador++;
                atual = atual.proximo;
            }
            System.out.println("[" + i + "] tem " + contador);
        }
    }

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
}