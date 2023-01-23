import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        Properties props = ManipuladorProperties.getProp();
        String chaveApiNasa = props.getProperty("chave_api_nasa");
        String chaveApiIMDB = props.getProperty("chave_api_IMDB");


        //String url = "https://imdb-api.com/en/API/Top250Movies/" + chaveApiIMDB ;
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();


        //String url = "https://api.nasa.gov/planetary/apod?api_key="+chaveApiNasa+"&start_date=2023-01-19";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();

        String url = "http://localhost:8080/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoLinguagens();


        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        // Exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extrair(json);

        for (int i = 0; i < 3; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = conteudo.getTitulo().replace(":", "-") + ".png";
 
            var geradora = new GeradorDeFigurinhas();
            geradora.criar(inputStream, nomeArquivo);

            System.out.printf("Título: \u001b[1m %s \u001b[0m \n", conteudo.getTitulo());

            System.out.println();

        }


    }
}