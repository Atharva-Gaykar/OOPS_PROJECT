import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.stream.Collectors;

// line 54 -> id will be mapped


public class ClipImageTestQuery {

    public static String processQuery(InputQuery.Query query_object) {
        return query_object.getQuery();
    }

    public static void handleQuery(InputQuery.Query query_object) {
        try {
            // --------------------------------------------
            // Local image path input
            // --------------------------------------------
            String imagePath = processQuery(query_object);
            int top_k = 3;

            // System.out.println("🖼️ Image Path: " + imagePath);
            // System.out.println("🔍 Top K: " + top_k);

            // --------------------------------------------
            // ⛔ API CALL COMMENTED OUT (for offline mode)
            // --------------------------------------------
    
              String urlString = "http://127.0.0.1:8500/query/image_path";
              URI uri = URI.create(urlString);
              URL url = uri.toURL();
              
              String data = "image_path=" + URLEncoder.encode(imagePath,
              StandardCharsets.UTF_8.toString())
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
             
             System.out.println("\n🌐 Response from API:\n" + response);
             String foodId = extractIdFromJson(response);
             

            // --------------------------------------------
            // ✅ Pseudo Response Generator (offline test)
            // --------------------------------------------
            // String foodId = generatePseudoId();

            // --------------------------------------------
            // Display the simulated API behavior
            // --------------------------------------------
            System.out.println("\n🌐 Simulated Response (Offline Mode):");
            System.out.println("{\"path\": \"" + imagePath + "\", \"id\": " + foodId + "}");
            System.out.println("🎯 Extracted Food ID: " + foodId);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --------------------------------------------
    // Helper: Random pseudo-ID generator
    // --------------------------------------------
    // private static String generatePseudoId() {
    //     Random random = new Random();
    //     int id = 1000 + random.nextInt(9000); // random 4-digit ID
    //     return String.valueOf(id);
    // }

    // --------------------------------------------
    // Helper (still kept for API version)
    // --------------------------------------------
    private static String extractIdFromJson(String json) {
        if (json == null || !json.contains("\"id\""))
            return null;
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
