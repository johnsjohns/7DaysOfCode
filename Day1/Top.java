import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;

public class Top {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        {
            try {
                request = HttpRequest
                        .newBuilder()
                        .uri(new URI("https://imdb-api.com/en/API/Top250Movies/k_f21tv2ax"))
                        .GET()
                        .build();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(pageResponse -> {
                    String responseBody = pageResponse.toString();
                    System.out.println(responseBody);
                })
                .join();

    }
}

