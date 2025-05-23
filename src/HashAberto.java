public class HashAberto extends HashTable {

    private int limiteInsercao; // define até onde vai a área de inserção

    public HashAberto() {
        super(32); // capacidade fixa
        this.limiteInsercao = 16; // metade para inserção direta, metade para colisão
    }

    @Override
    public void inserir(String chave) {
        int indice = calcularIndice(chave);

        // Verifica se está dentro da área de inserção
        if (indice < limiteInsercao) {
            if (tabela[indice] == null) {
                tabela[indice] = chave;
                tamanho++;
            } else {
                colisoes++;
                // procurar na área de colisões
                boolean inserido = false;
                for (int i = limiteInsercao; i < capacidade; i++) {
                    if (tabela[i] == null) {
                        tabela[i] = chave;
                        tamanho++;
                        inserido = true;
                        break;
                    }
                }
                if (!inserido) {
                    System.out.println("⚠️ Área de colisões cheia! Não foi possível inserir: " + chave);
                }
            }
        } else {
            // hash apontou pra fora da área de inserção, busca posição válida
            System.out.println("⚠️ Hash fora da área de inserção, tentando realocar para chave: " + chave);
            for (int i = 0; i < limiteInsercao; i++) {
                if (tabela[i] == null) {
                    tabela[i] = chave;
                    tamanho++;
                    return;
                }
            }
            // se não couber nem na inserção, tenta nas colisões
            for (int i = limiteInsercao; i < capacidade; i++) {
                if (tabela[i] == null) {
                    tabela[i] = chave;
                    tamanho++;
                    colisoes++;
                    return;
                }
            }
            System.out.println("❌ Nenhum espaço disponível para: " + chave);
        }
    }

    @Override
    public boolean buscar(String chave) {
        // Busca em toda a tabela
        for (int i = 0; i < capacidade; i++) {
            if (chave.equals(tabela[i])) {
                return true;
            }
        }
        return false;
    }
}
