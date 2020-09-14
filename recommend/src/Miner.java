import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Miner {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/movie_ids_09_13_2020.json"));
        JsonObject jsonObject = JsonParser.parseString(br.readLine()).getAsJsonObject();

        String myApiKey = "986040c68fbb4aebdf1e339b50aeafd6";
        StringBuilder queryURL = new StringBuilder();
        queryURL
                .append("https://api.themoviedb.org/")
                .append("3/movie/")
                .append(jsonObject.get("id"))
                .append("?api_key=")
                .append(myApiKey)
                .append("&language=en-US");
        System.out.println(queryURL);
        HttpsURLConnection httpsConn = (HttpsURLConnection) new URL(queryURL.toString()).openConnection();
        httpsConn.setRequestMethod("GET");
        System.out.println(httpsConn.getResponseCode());
    }
}
