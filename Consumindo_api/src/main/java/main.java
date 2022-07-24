import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class main {
    public static void main(String[] args) throws  Exception {
// fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://api.themoviedb.org/3/trending/movie/week?api_key=872995efee79646f5b0d834c33522673"; //URL do arquivo JSON
        URI endereco = URI.create(url);  // Salvando o URL em uma URI (URL é o endereço de um recurso na internet , URI é o identificador uniforme de recurso)
        var client = HttpClient.newHttpClient(); // criando um novo httpClient
        var request = HttpRequest.newBuilder(endereco).GET().build(); //Fazendo um request
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); //Implementações do BodyHandler que implementam vários manipuladores úteis, como lidar com o corpo de resposta como uma String ou transmitir o corpo de resposta para um arquivo.
        String body = response.body(); //Criando uma String para salvar a resposta (body() é  um metodo da classe HttpResponse)
        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser(); //O parser é um analisador sintático. Sua função é ler uma entrada de dados que possuem certas regras específicas (O arquivo foi separado por alguns regex)
        List<Map<String, String>> listaDeFilmes = parser.parse(body); //Criando uma lista mapeada por uma chave em String e conteudo String para salvar cada Filme em um objeto
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("overview"));
            System.out.println(filme.get("vote_average"));
            System.out.println();
        }
    }
    }

