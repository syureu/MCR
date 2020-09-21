import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;


public class TmdbMiner {
    public static void main(String[] args) throws Exception {
        int count = 1;
        String inline;
        Map<Integer, Integer> requestFail = new HashMap<>();
        String SQL = "insert into " +
                "tmdb_movies(" +
                "adult," +
                "backdrop_path," +
                "belongs_to_collection," +
                "budget," +
                "genres," +
                "homepage," +
                "id," +
                "imdb_id," +
                "original_language," +
                "original_title," +
                "overview," +
                "popularity," +
                "poster_path," +
                "production_companies," +
                "production_countries," +
                "release_date," +
                "revenue," +
                "runtime," +
                "spoken_languages," +
                "status," +
                "tagline," +
                "title," +
                "video," +
                "vote_average," +
                "vote_count" +
                ") " +
                "values(" +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                ")";

        Class.forName("org.mariadb.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://j3d104.p.ssafy.io:29000/ssafy?useUniCode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
        String mariaDBID = "ubuntu";
        String mariaDBPW = "ssafy!@#104ssafy!";
        Connection dbConn = DriverManager.getConnection(jdbcUrl, mariaDBID, mariaDBPW);

        BufferedReader br = new BufferedReader(new FileReader("resources/movie_ids_09_13_2020.json"));
        while ((inline = br.readLine()) != null) {
            JsonObject jsonObject = JsonParser.parseString(inline).getAsJsonObject();

            String myApiKey = "986040c68fbb4aebdf1e339b50aeafd6";
            StringBuilder queryURL = new StringBuilder();
            queryURL
                    .append("https://api.themoviedb.org/")
                    .append("3/movie/")
                    .append(jsonObject.get("id"))
                    .append("?api_key=")
                    .append(myApiKey)
                    .append("&language=en-US");

            HttpsURLConnection httpsConn = (HttpsURLConnection) new URL(queryURL.toString()).openConnection();
            httpsConn.setRequestMethod("GET");
            int resCode = httpsConn.getResponseCode();
            System.out.print("Count " + count++ + " : " + jsonObject.get("id") + " : ");
            System.out.print(resCode);
            if(resCode != 200) {
                requestFail.put(jsonObject.get("id").getAsInt(), resCode);
                System.out.println();
                continue;
            }

            BufferedReader connIn = new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));
            StringBuilder connSb = new StringBuilder();
            while ((inline = connIn.readLine()) != null) {
                connSb.append(inline);
            }
            connIn.close();
            httpsConn.disconnect();

            JsonObject responseJson = JsonParser.parseString(connSb.toString()).getAsJsonObject();
            System.out.println();
            PreparedStatement pstmt = dbConn.prepareStatement(SQL);
            if(!responseJson.get("adult").isJsonNull()) {
                pstmt.setBoolean(1, responseJson.get("adult").getAsBoolean());
            } else {
                pstmt.setNull(1, Types.BOOLEAN);
            }
            if(!responseJson.get("backdrop_path").isJsonNull()) {
                pstmt.setString(2, responseJson.get("backdrop_path").getAsString());
            } else {
                pstmt.setString(2, null);
            }
            if(!responseJson.get("belongs_to_collection").isJsonNull()) {
                pstmt.setString(3, responseJson.get("belongs_to_collection").toString());
            } else {
                pstmt.setString(3, null);
            }
            if(!responseJson.get("budget").isJsonNull()) {
                pstmt.setLong(4, responseJson.get("budget").getAsLong());
            } else {
                pstmt.setNull(4, Types.BIGINT);
            }
            if(!responseJson.get("genres").isJsonNull()) {
                pstmt.setString(5, responseJson.get("genres").toString());
            } else {
                pstmt.setString(5, null);
            }
            if(!responseJson.get("homepage").isJsonNull()) {
                pstmt.setString(6, responseJson.get("homepage").getAsString());
            } else {
                pstmt.setString(6, null);
            }
            pstmt.setLong(7, responseJson.get("id").getAsLong());
            if(!responseJson.get("imdb_id").isJsonNull()) {
                pstmt.setString(8, responseJson.get("imdb_id").getAsString());
            } else {
                pstmt.setString(8, null);
            }
            if(!responseJson.get("original_language").isJsonNull()) {
                pstmt.setString(9, responseJson.get("original_language").getAsString());
            } else {
                pstmt.setString(9, null);
            }
            if(!responseJson.get("original_title").isJsonNull()){
                pstmt.setString(10, responseJson.get("original_title").getAsString());
            } else {
                pstmt.setString(10, null);
            }
            if(!responseJson.get("overview").isJsonNull()) {
                pstmt.setString(11, responseJson.get("overview").getAsString());
            } else {
                pstmt.setString(11, null);
            }
            if(!responseJson.get("popularity").isJsonNull()) {
                pstmt.setDouble(12, responseJson.get("popularity").getAsDouble());
            } else {
                pstmt.setNull(12, Types.DOUBLE);
            }
            if(!responseJson.get("poster_path").isJsonNull()) {
                pstmt.setString(13, responseJson.get("poster_path").getAsString());
            } else {
                pstmt.setString(13, null);
            }
            if(!responseJson.get("production_companies").isJsonNull()) {
                pstmt.setString(14, responseJson.get("production_companies").toString());
            } else {
                pstmt.setString(14, null);
            }
            if(!responseJson.get("production_countries").isJsonNull()) {
                pstmt.setString(15, responseJson.get("production_countries").toString());
            } else {
                pstmt.setString(15, null);
            }
            if(!responseJson.get("release_date").isJsonNull()) {
                pstmt.setString(16, responseJson.get("release_date").getAsString());
            } else {
                pstmt.setString(16, null);
            }
            if(!responseJson.get("revenue").isJsonNull()) {
                pstmt.setLong(17, responseJson.get("revenue").getAsLong());
            } else {
                pstmt.setNull(17, Types.BIGINT);
            }
            if(!responseJson.get("runtime").isJsonNull()) {
                pstmt.setInt(18, responseJson.get("runtime").getAsInt());
            } else {
                pstmt.setNull(18, Types.INTEGER);
            }
            if(!responseJson.get("spoken_languages").isJsonNull()) {
                pstmt.setString(19, responseJson.get("spoken_languages").toString());
            } else {
                pstmt.setString(19, null);
            }
            if(!responseJson.get("status").isJsonNull()) {
                pstmt.setString(20, responseJson.get("status").getAsString());
            } else {
                pstmt.setString(20, null);
            }
            if(!responseJson.get("tagline").isJsonNull()) {
                pstmt.setString(21, responseJson.get("tagline").getAsString());
            } else {
                pstmt.setString(21, null);
            }
            if(!responseJson.get("title").isJsonNull()) {
                pstmt.setString(22, responseJson.get("title").getAsString());
            } else {
                pstmt.setString(22, null);
            }
            if(!responseJson.get("video").isJsonNull()) {
                pstmt.setBoolean(23, responseJson.get("video").getAsBoolean());
            } else {
                pstmt.setNull(23, Types.BOOLEAN);
            }
            if(!responseJson.get("vote_average").isJsonNull()) {
                pstmt.setDouble(24, responseJson.get("vote_average").getAsDouble());
            } else {
                pstmt.setNull(24, Types.DOUBLE);
            }
            if(!responseJson.get("vote_count").isJsonNull()) {
                pstmt.setInt(25, responseJson.get("vote_count").getAsInt());
            } else {
                pstmt.setNull(25, Types.INTEGER);
            }
            try {
                pstmt.executeUpdate();
            } catch(Exception e) {
                e.printStackTrace();
                requestFail.put(jsonObject.get("id").getAsInt(), 200);
            } finally {
                pstmt.close();
            }
        }
        br.close();

        requestFail.forEach((k,v)-> System.out.println("id : " + " : " + k + " is error : " + v));
    }
}
