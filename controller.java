import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonDownloader {

    public static void main(String[] args) {
        // Define the REST API URL
        String apiUrl = "https://example.com/api/data";

        // Define the directory path to store the JSON
        String directoryPath = "/path/to/your/directory";

        // Make the REST API request
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        // Check if the request was successful (HTTP status code 200)
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Get the JSON response body
            String jsonResponse = responseEntity.getBody();

            // Create the directory if it doesn't exist
            createDirectory(directoryPath);

            // Save the JSON to a file in the specified directory
            saveJsonToFile(directoryPath, "response.json", jsonResponse);

            System.out.println("JSON downloaded and saved successfully.");
        } else {
            System.out.println("Failed to retrieve JSON. HTTP Status Code: " + responseEntity.getStatusCodeValue());
        }
    }

    private static void createDirectory(String directoryPath) {
        Path directory = Paths.get(directoryPath);

        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle directory creation failure
            }
        }
    }

    private static void saveJsonToFile(String directoryPath, String fileName, String jsonContent) {
        Path filePath = Paths.get(directoryPath, fileName);

        try (FileWriter fileWriter = new FileWriter(filePath.toString())) {
            fileWriter.write(jsonContent);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle file writing failure
        }
    }
}
