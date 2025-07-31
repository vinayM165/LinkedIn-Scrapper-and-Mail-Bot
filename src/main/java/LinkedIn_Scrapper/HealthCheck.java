package LinkedIn_Scrapper;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HealthCheck {

    /**
     * This method checks the health of the application.
     * @return true if the application is healthy, false otherwise.
     */
    public static boolean isHealthy() {
        return areRequiredFilesPresent() && isNetworkAvailable();
    }

    /**
     * Checks if required files are present.
     * @return true if all required files are present, false otherwise.
     */
    private static boolean areRequiredFilesPresent() {
        File keyJson = new File("key.json");
        File cookiesData = new File("cookies.data");

        if (!keyJson.exists()) {
            System.err.println("Health Check Failed: key.json not found.");
            return false;
        }

        if (!cookiesData.exists()) {
            System.err.println("Health Check Failed: cookies.data not found.");
            return false;
        }

        return true;
    }

    /**
     * Checks if the network is available by trying to connect to Google and LinkedIn.
     * @return true if the network is available, false otherwise.
     */
    private static boolean isNetworkAvailable() {
        return isHostAvailable("https://www.google.com") && isHostAvailable("https://www.linkedin.com");
    }

    /**
     * Checks if a host is available by sending an HTTP HEAD request.
     * @param hostUrl the URL of the host to check.
     * @return true if the host is available, false otherwise.
     */
    private static boolean isHostAvailable(String hostUrl) {
        try {
            URL url = new URL(hostUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000); // 5 seconds
            connection.setReadTimeout(5000); // 5 seconds
            int responseCode = connection.getResponseCode();
            return (200 <= responseCode && responseCode <= 399);
        } catch (IOException e) {
            System.err.println("Health Check Failed: Could not connect to " + hostUrl);
            return false;
        }
    }
}
