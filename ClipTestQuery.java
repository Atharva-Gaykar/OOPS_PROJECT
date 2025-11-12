import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ClipTestQuery {

    public static String processQuery(String search_query) {
        return search_query;
    }

    public static int handleQuery(String search_query) {
        int foodIdInt = -1;
        String query = processQuery(search_query);
        int top_k = 1;

        try {
            String urlString = "http://127.0.0.1:8500/query/text";
            URI uri = URI.create(urlString);
            URL url = uri.toURL();

            System.out.println(" URL created successfully: " + url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String data = "query=" + URLEncoder.encode(query, StandardCharsets.UTF_8.toString())
                        + "&top_k=" + top_k;

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

            //  Use Gson to parse the JSON response
            JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
            if (jsonObject.has("id") && !jsonObject.get("id").isJsonNull()) {
                foodIdInt = jsonObject.get("id").getAsInt();
                System.out.println(" Extracted Food ID: " + foodIdInt);
            } else {
                System.out.println(" No valid ID found in API response.");
            }

            conn.disconnect();
        } catch (Exception e) {
            System.err.println(" Error during API call: " + e.getMessage());
        }

        return foodIdInt;
    }
}