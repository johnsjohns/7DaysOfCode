import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Top {
    public static void main(String[] args) {
        Top top = new Top();
        String json = top.pegaJson();
        String movieArray[] = top.parseJsonMovies(json);
        for(String filme: movieArray){
            System.out.println("Filme: "+filme);
        }


    }

    public String pegaJson() {
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
        System.out.println(response.body());
        return response.body();
    }

    public String[] parseJsonMovies(String json){
        String[] item = json.split("\\[|\\]");
        String filmes[] = item[1].split("\\{");
        return filmes;
    }
}