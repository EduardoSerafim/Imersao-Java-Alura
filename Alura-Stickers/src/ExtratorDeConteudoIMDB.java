import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements ExtratorDeConteudo{
    public List<Conteudo> extrair(String json){
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String,String> atributos: listaDeAtributos) {
            String titulo = atributos.get("title");
            String url = atributos.get("image");
            var conteudo = new Conteudo(titulo, url);

            conteudos.add(conteudo);
        }
        return conteudos;
    }
}
