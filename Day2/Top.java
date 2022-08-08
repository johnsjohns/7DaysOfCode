import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Top {
    public static void main(String[] args) {
        String json = pegaJson();
        String movieArray[] = parseJsonMovies(json);
        List<String> title = parseTitles(movieArray);
       
        title.forEach(System.out::println);

    }

    static private String pegaJson() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(new URI("https://imdb-api.com/en/API/Top250Movies/k_f21tv2ax")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return response.body();
    }

    static private String[] parseJsonMovies(String json){
        String[] item = json.split("\\[|\\]");
        String filmes[] = item[1].split("\\},\\{");
        return filmes;
    }

    private static List<String> parseTitles(String[] moviesArray) {
		return parseAttribute(moviesArray, 3);
	}
	
	private static List<String> parseUrlImages(String[] moviesArray) {
		return parseAttribute(moviesArray, 5);
	}
	
	private static List<String> parseAttribute(String[] moviesArray, int pos) {
        List<String> atributo = new ArrayList<String>();
		for (String movie : moviesArray) {
            String itens[] = movie.split(",") ;
             String[] item = itens[pos].split(":");
             if(item.length > 1) atributo.add(item[1].replace("\"","" ));
            }
        return atributo;	
    }
}