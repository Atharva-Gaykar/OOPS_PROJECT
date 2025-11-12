import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ClipImageTestQuery {

    public static String processQuery(String search_imagePath) {
        return search_imagePath;
    }

    public static int handleQuery(String search_imagePath) {
        int foodIdInt = -1;

        try {
            String imagePath = processQuery(search_imagePath);
            int top_k = 3;

            String urlString = "http://127.0.0.1:8500/query/image";
            URI uri = URI.create(urlString);
            URL url = uri.toURL();

            String data = "image_path=" + URLEncoder.encode(imagePath, StandardCharsets.UTF_8.toString())
                        + "&top_k=" + top_k;

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int status = conn.getResponseCode();
            InputStream responseStream = (status < HttpURLConnection.HTTP_BAD_REQUEST)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            String response;
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(responseStream, StandardCharsets.UTF_8))) {
                response = in.lines().collect(Collectors.joining());
            }

            System.out.println("\n Response from API:\n" + response);

            // Use Gson to parse the JSON response
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            if (jsonObject.has("id") && !jsonObject.get("id").isJsonNull()) {
                foodIdInt = jsonObject.get("id").getAsInt();
                System.out.println(" Extracted Food ID: " + foodIdInt);
            } else {
                System.out.println("No valid ID found in API response.");
            }

            conn.disconnect();
        } catch (Exception e) {
            System.err.println(" Error during API call: " + e.getMessage());
        }

        return foodIdInt;
    }
}