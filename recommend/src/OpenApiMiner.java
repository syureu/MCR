import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.sound.midi.SoundbankResource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;

class OpenApiMiner {
    public static void main(String[] args) throws Exception {
        int curPage = 1;
        String inline;
        String SQL = "insert into " +
                "movie(" +
                "movie_cd," +
                "movie_nm," +
                "movie_nm_en," +
                "movie_nm_og," +
                "year," +
                "genre," +
                "rep_nation_nm," +
                "director" +
                ")" +
                "values(" +
                "?,?,?,?,?,?,?,?" +
                ")";

        Class.forName("org.mariadb.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://j3d104.p.ssafy.io:29000/ssafy?useUniCode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
        String mariaDBID = "ubuntu";
        String mariaDBPW = "ssafy!@#104ssafy!";
        Connection dbConn = DriverManager.getConnection(jdbcUrl, mariaDBID, mariaDBPW);

        while (curPage < 786) {
            String myApiKey = "53de84ffa0def33241ee679d9bcc0d1f";
            StringBuilder queryURL = new StringBuilder();
            queryURL
                    .append("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=")
                    .append(myApiKey)
                    .append("&itemPerPage=100&curPage=")
                    .append(curPage);

            HttpURLConnection httpConn = (HttpURLConnection) new URL(queryURL.toString()).openConnection();
            httpConn.setRequestMethod("GET");
            int resCode = httpConn.getResponseCode();
            System.out.print("curPage " + curPage++ + " : " + resCode);
            System.out.println();
            if (resCode != 200) {
                continue;
            }

            BufferedReader connIn = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            StringBuilder connSb = new StringBuilder();
            while ((inline = connIn.readLine()) != null) {
                connSb.append(inline);
            }
            connIn.close();
            httpConn.disconnect();

            JsonObject responseJson = JsonParser.parseString(connSb.toString()).getAsJsonObject();

            for (JsonElement movieList : responseJson.get("movieListResult").getAsJsonObject().getAsJsonArray("movieList")) {
                JsonObject movie = movieList.getAsJsonObject();
                PreparedStatement pstmt = dbConn.prepareStatement(SQL);
                if (!movie.get("movieCd").isJsonNull()) {
                    pstmt.setString(1, movie.get("movieCd").getAsString());
                } else {
                    pstmt.setNull(1, Types.VARCHAR);
                }
                if (!movie.get("movieNm").isJsonNull()) {
                    pstmt.setString(2, movie.get("movieNm").getAsString());
                } else {
                    pstmt.setNull(2, Types.VARCHAR);
                }
                if (!movie.get("movieNmEn").isJsonNull()) {
                    pstmt.setString(3, movie.get("movieNmEn").getAsString());
                } else {
                    pstmt.setNull(3, Types.VARCHAR);
                }
                pstmt.setNull(4, Types.VARCHAR);
                if (!movie.get("prdtYear").isJsonNull()) {
                    pstmt.setString(5, movie.get("prdtYear").getAsString());
                } else {
                    pstmt.setNull(5, Types.VARCHAR);
                }
                if (!movie.get("genreAlt").isJsonNull()) {
                    pstmt.setString(6, movie.get("genreAlt").getAsString());
                } else {
                    pstmt.setNull(6, Types.VARCHAR);
                }
                if (!movie.get("repNationNm").isJsonNull()) {
                    pstmt.setString(7, movie.get("repNationNm").getAsString());
                } else {
                    pstmt.setNull(7, Types.VARCHAR);
                }
                if (!movie.get("directors").isJsonNull()) {
                    if (movie.get("directors").getAsJsonArray().size() > 0) {
                        pstmt.setString(8, movie.get("directors").getAsJsonArray().get(0).getAsJsonObject().get("peopleNm").getAsString());
                    } else {
                        pstmt.setNull(8, Types.VARCHAR);
                    }
                } else {
                    pstmt.setNull(8, Types.VARCHAR);
                }
                try {
                    pstmt.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    pstmt.close();
                }
            }
        }
    }
}