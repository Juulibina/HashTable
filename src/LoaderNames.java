import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoaderNames {
    public static List<String> carregarNomes(String caminhoArquivo) throws IOException {
        return Files.readAllLines(Paths.get(caminhoArquivo));
    }
}

