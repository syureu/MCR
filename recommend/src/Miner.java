import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;

public class Miner {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("resources/movie_ids_09_13_2020.json"));
        JsonObject jsonObject = JsonParser.parseString(br.readLine()).getAsJsonObject();
        System.out.println(jsonObject);
        System.out.println(jsonObject.get("id"));
    }
}
