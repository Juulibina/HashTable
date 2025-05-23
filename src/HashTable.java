public abstract class HashTable {
    protected int capacidade = 32;
    protected int tamanho;
    protected int colisoes;
    protected String[] tabela;

    public HashTable(int capacidade) {
        this.capacidade = capacidade;
        this.tamanho = 0;
        this.colisoes = 0;
        this.tabela = new String[capacidade];
    }

    public abstract void inserir(String chave); //metodo para inserir dados na tabela
    public abstract boolean buscar(String chave); //metodo para buscar dados na tabela

    public int getCapacidade(){
        return capacidade;
    }

    public int getTamanho(){
        return tamanho;
    }

    public int getColisoes(){
        return colisoes;
    }

    protected int calcularIndice(String chave){ //calculo para transformar string em indice
        long hash = 5381; //numero sugerido pelo criador do calculo

        for (int i = 0; i < chave.length(); i++) {
            hash = ((hash << 5) + hash) + chave.charAt(i);
        }

        return (int) Math.abs(hash % capacidade);
    }

    public void imprimirTabela() {
        for (int i = 0; i < capacidade; i++) {
            System.out.println("[" + i + "] " + (tabela[i] != null ? tabela[i] : "-"));
        }
    }
}
