import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelUrlExecution {

    public static void main(String[] args) {
        List<String> urls = new ArrayList<>();
        urls.add("https://www.example.com/page1");
        urls.add("https://www.example.com/page2");
        urls.add("https://www.example.com/page3");

        executeUrlsInParallel(urls);
    }

    public static void executeUrlsInParallel(List<String> urls) {
        int numberOfThreads = Math.min(urls.size(), Runtime.getRuntime().availableProcessors());
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (String url : urls) {
            executorService.execute(() -> executeUrl(url));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle exception as needed
        }
    }

    public static void executeUrl(String url) {
        // Replace this with the actual command or logic to execute the URL
        try {
            Process process = new ProcessBuilder("curl", url).start();
            int exitCode = process.waitFor();
            System.out.println("URL: " + url + " executed with exit code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception as needed
        }
    }
}
