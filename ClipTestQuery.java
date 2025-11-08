
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.Collectors;

public class ClipTestQuery {

    public static void main(String[] args) {

        // --- Toy setup ---
        String query = "pani puri";
        int top_k = 1;

        // -------------------------------
        // 🧠 Simulated API response mode
        // -------------------------------
        System.out.println("⚙️ Running in pseudo-mode (no API connection).");
        String pseudoResponse = simulateApiResponse(query, top_k);

        System.out.println("\n🌐 Simulated Response:\n" + pseudoResponse);

        // Extract the "id" from JSON manually
        String foodId = extractIdFromJson(pseudoResponse);
        if (foodId != null) {
            System.out.println("🎯 Extracted Food ID: " + foodId);
        } else {
            System.out.println("⚠️ No valid ID found in response.");
        }

        // ----------------------------------------------------------
        // 🛰️ Real API mode (commented out — can be re-enabled later)
        // ----------------------------------------------------------
        /*
        try {
            String urlString = "http://127.0.0.1:8500/query/text";
            URI uri = URI.create(urlString);
            URL url = uri.toURL();

            System.out.println("✅ URL created successfully: " + url);

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

            System.out.println("\n🌐 Response from API:\n" + response);

            String foodIdApi = extractIdFromJson(response);
            if (foodIdApi != null) {
                System.out.println("🎯 Extracted Food ID: " + foodIdApi);
            } else {
                System.out.println("⚠️ No valid ID found in API response.");
            }

            conn.disconnect();

        } catch (IOException e) {
            System.err.println("⚠️ Network/IO error:");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("⚠️ Unexpected error:");
            e.printStackTrace();
        }
        */
    }

    // -----------------------------------------------
    // 🧩 Simulated API Response Generator
    // -----------------------------------------------
    private static String simulateApiResponse(String query, int top_k) {
        Random rand = new Random();
        int pseudoId = 1000 + rand.nextInt(9000); // random ID between 1000–9999
        return "{\"query\": \"" + query + "\", \"top_k\": " + top_k + ", \"id\": " + pseudoId + "}";
    }

    // -----------------------------------------------
    // Helper: Extract numeric ID from JSON response
    // -----------------------------------------------
    private static String extractIdFromJson(String json) {
        if (json == null || !json.contains("\"id\"")) return null;
        try {
            int start = json.indexOf("\"id\"") + 5;
            int end = json.indexOf("}", start);
            String id = json.substring(start, end).replaceAll("[^0-9]", "");
            return id.isEmpty() ? null : id;
        } catch (Exception e) {
            return null;
        }
    }
}
