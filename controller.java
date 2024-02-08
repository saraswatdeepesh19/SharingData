import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlEncoderDecoder {

    public static String encodeUrls(List<String> urls) {
        StringBuilder encodedUrls = new StringBuilder();

        for (String url : urls) {
            try {
                String encodedUrl = URLEncoder.encode(url, "UTF-8");
                encodedUrls.append(encodedUrl).append(",");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace(); // Handle encoding exception as needed
            }
        }

        return encodedUrls.toString();
    }

    public static List<String> decodeUrls(String encodedUrls) {
        List<String> decodedUrls = new ArrayList<>();
        String[] urlArray = encodedUrls.split(",");

        for (String encodedUrl : urlArray) {
            try {
                String decodedUrl = URLDecoder.decode(encodedUrl, "UTF-8");
                decodedUrls.add(decodedUrl);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace(); // Handle decoding exception as needed
            }
        }

        return decodedUrls;
    }

    public static void main(String[] args) {
        List<String> originalUrls = new ArrayList<>();
        originalUrls.add("https://www.example.com/page1");
        originalUrls.add("https://www.example.com/page2");
        originalUrls.add("https://www.example.com/page3");

        // Encode URLs
        String encodedUrls = encodeUrls(originalUrls);
        System.out.println("Encoded URLs: " + encodedUrls);

        // Decode URLs
        List<String> decodedUrls = decodeUrls(encodedUrls);
        System.out.println("Decoded URLs: " + decodedUrls);
    }
}
