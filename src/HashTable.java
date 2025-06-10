public abstract class HashTable {
    protected int capacidade = 32;
    protected int tamanho;
    protected int colisoes;
    protected final double fatorDeCarga = 0.75;

    public HashTable(int capacidade) {
        this.capacidade = capacidade;
        this.tamanho = 0;
        this.colisoes = 0;
    }

    public abstract void inserir(String chave);
    public abstract boolean buscar(String chave);
    protected abstract int calcularIndice(String chave);
    protected abstract void reinserirTodos(Node[] novaTabela, int novaCapacidade);
    protected abstract void atualizarTabela(Node[] novaTabela);
    public abstract void imprimirQuantidadePorIndice();

    protected int calcularIndice(String chave, int novaCapacidade) {
        int hash = 7;
        for (char c : chave.toCharArray()) {
            hash = hash * 31 + c;
        }
        return Math.abs(hash) % novaCapacidade;
    }

    protected void rehash() {
        int novaCapacidade = capacidade * 2;
        Node[] novaTabela = new Node[novaCapacidade];

        reinserirTodos(novaTabela, novaCapacidade);

        this.capacidade = novaCapacidade;
        this.colisoes = 0;
        this.tamanho = calcularNovoTamanho(novaTabela);
        atualizarTabela(novaTabela);
    }

    protected int calcularNovoTamanho(Node[] novaTabela) {
        int count = 0;
        for (Node head : novaTabela) {
            Node atual = head;
            while (atual != null) {
                count++;
                atual = atual.proximo;
            }
        }
        return count;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getTamanho() {
        return tamanho;
    }

    public int getColisoes() {
        return colisoes;
    }

    public void imprimirTabela() {
    }
}