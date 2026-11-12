 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImagemLivro {

    public static String salvar(Path origem, int idLivro)
            throws IOException {

        if (Files.size(origem) > 5 * 1024 * 1024) {
            throw new IOException(
                "A imagem deve ter até 5 MB."
            );
        }

        BufferedImage imagem =
            ImageIO.read(origem.toFile());

        if (imagem == null) {
            throw new IOException(
                "Arquivo de imagem inválido."
            );
        }

        String nome = idLivro + "-" +
            UUID.randomUUID() + ".png";

        Path pasta = Path.of("imagens", "livros");

        Files.createDirectories(pasta);

        Path destino = pasta.resolve(nome);

        if (!ImageIO.write(
                imagem, "png", destino.toFile())) {
            throw new IOException(
                "Não foi possível salvar a imagem."
            );
        }

        return destino.toString().replace('\\', '/');
    }
}

