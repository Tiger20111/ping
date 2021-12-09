package sender;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request implements Runnable {
    private final int key;

    Request(int key) {
        this.key = key;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection c= (HttpURLConnection) new URL("http://localhost:8080/request/" + this.key).openConnection();
            c.setRequestMethod("POST");
            c.getInputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
