package LinkedIn_Scrapper;

import com.dropbox.core.*;
import com.dropbox.core.v2.*;
import com.dropbox.core.v2.files.*;

import java.io.*;
import java.util.*;

public class DropboxUploadExample {

    // The access token from OAuth2
    private static final String ACCESS_TOKEN = "sl.u.AFvU4MknD3CDY4jR2f-8u4-1Y1EyRIH6GDvtkYHG90Zo2K05vx64lsKjN0hRxUItdrRDNq-FA7j1z6l4PWR7Vr4X_K0XmTXQOcCeFl_V8p4D8WcZWkbhzG09ha6rmwhlVzqrqxkPd6_X4nTKDJXPl0JgXTZ0ww7wVfd3pWt7eNj8sK8ZKu539iAClUIeBIFVTExICYKycJf3sKczMVPWB0EwVGiWJDadY0i0Xhh2oiVaC5wQtMsXs9WtSLQYI7qO8aYXQZ74bfCAN0kD6Fmmvx8o1Q8vDwHKDokFVQTFjI8RYF3_k0BTThCBsetPOxcRJA7RTe9BEv_B2zbh2w9Oj2JifQAh7B8trsR5N97WwcnxxTZX2aUGHZ3d_ZrKpLy5crNPGtlRFyH9Mt0svJmvDOJIOdz-bqK6LAdfMEGYrHnV6-KgdV7XKJDqzMMczjUN-_ADY9-97BnYj0CmitRHXi9DAGv0D211cbsWtsHSBd0hJkVoa6TgCUviOQHESeRWIRuCxeL-I9_dH8fuS8VBy_Dz1NyXCgr8PI7fUS2JlKMpibdnNmnMYLDoeoHi-doY1YSeIU3gLYaRlGmHYMiOtX_XOJ5PAlg1-9lXfXz9maUivc3URrZi9y_IKo9_gywQC2uUXkTxIp15qw2_TLPja5crsEXDsAOGfHOLIzN7yRpF8sccfY8KSKME8OAVTfSQwpa7e-YX62toupdUMAVh5-R3Ohvj1L7d0UmJc76HZcP_HabiCgGz5U-dYta2asQ2KUymMqTmGnN6ha8MVeYnO7KAgIA2lVTwiJNzXagxzy4sBmcKDA381u7t9QwvhPMq0bENqNUyhrOT3604nwZz8cNT4k2xpsHcNLRrN-YBXW-rccWHqhw7kPDeArHZcVOT-eyp_JPW31zoehL1SZl7wWip21KZgmRKfTYfnB-nKiEirljQ765B9S8b1Yiwb_Ht0RGbIIHv9M8s6sZpVBHxcZIU5bN3vVHeHmjG5nvYuQq_gvgduwv-EMx3DTe4hWWuxMFwcfEkb7hPRpwNJXzlrWHjWbo4Hz5WyDU8UZLiGFmH2-6WLZMdadkK51PNT9WmcIJjyPdEyXDAXlATqxo74Fc0j_q_F1oHnTmqW8Enj8kGffYtGxl00J4PyNsA6d5d3EoV4JrdhuGlRvzf_Pgg3WRGpvXmF8w5sD42KczvczDYwAV_uQialOTGC5S6R31kQRonss9C4ufA7JelWH3QVFoL66MRRmGxJZPqMSK3U__DSvELyKhOjjA5cjMYSxYqcFth7S4NkWkFy6OL6zYMKLjG";
    private static final String FILE_PATH = "C:\\Users\\hp5pr\\Downloads\\LinkedIn-Scrapper-and-Mail-Bot\\EmailOutput.txt"; // Local file path
    private static final String DROPBOX_PATH = "/Demo/EmailOutput.txt"; // Dropbox path (in your app folder or /)

    public static void main(String[] args) {
        try {
            // Create Dropbox client
            DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
            DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

            // Upload file to Dropbox
            uploadFileToDropbox(client);
        } catch (DbxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void uploadFileToDropbox(DbxClientV2 client) throws DbxException, IOException {
        File file = new File(FILE_PATH);

        // Read the file to upload
        FileInputStream inputStream = new FileInputStream(file);

        // Upload the file to Dropbox (you can choose either upload to root or specific folder)
        FileMetadata metadata = client.files().uploadBuilder(DROPBOX_PATH)
                .withMode(WriteMode.ADD)
                .uploadAndFinish(inputStream);

        System.out.println("File uploaded: " + metadata.getPathDisplay());
    }
}
