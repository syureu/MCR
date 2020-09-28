import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OpenApiTmdbSequencer {
    public static void main(String[] args) throws Exception {
        Class.forName("org.mariadb.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://j3d104.p.ssafy.io:29000/ssafy?useUniCode=yes&characterEncoding=UTF-8&serverTimezone=Asia/Seoul";
        String mariaDBID = "ubuntu";
        String mariaDBPW = "ssafy!@#104ssafy!";
        Connection dbConn = DriverManager.getConnection(jdbcUrl, mariaDBID, mariaDBPW);

        StringBuilder selectCountSQL = new StringBuilder("select\n" +
                "\tcount(*)\n" +
                "from\n" +
                "\t(\n" +
                "\t\tselect\n" +
                "\t\t\ttitle\n" +
                "\t\tfrom\n" +
                "\t\t\ttmdb_movies\n" +
                "\t\twhere\n" +
                "\t\t\tleft(release_date,4) = ?\n" +
                "\t) as tmsq,\n" +
                "\t(\n" +
                "\t\tselect\n" +
                "\t\t\tmovie_nm_en\n" +
                "\t\tfrom\n" +
                "\t\t\tmovie\n" +
                "\t\twhere\n" +
                "\t\t\tyear = ?\n" +
                "\t) as oasq\n" +
                "where\n" +
                "\ttmsq.title=oasq.movie_nm_en");

        StringBuilder selectSQL = new StringBuilder("select\n" +
                "\ttitle, vote_average, vote_count, poster_path, overview\n" +
                "from\n" +
                "\t(\n" +
                "\t\tselect\n" +
                "\t\t\ttitle, vote_average, vote_count, poster_path, overview\n" +
                "\t\tfrom\n" +
                "\t\t\ttmdb_movies\n" +
                "\t\twhere\n" +
                "\t\t\tleft(release_date,4) = ?\n" +
                "\t) as tmsq,\n" +
                "\t(\n" +
                "\t\tselect\n" +
                "\t\t\tmovie_nm_en\n" +
                "\t\tfrom\n" +
                "\t\t\tmovie\n" +
                "\t\twhere\n" +
                "\t\t\tyear = ?\n" +
                "\t) as oasq\n" +
                "where\n" +
                "\ttmsq.title=oasq.movie_nm_en");

        StringBuilder updateSQL = new StringBuilder("update\n" +
                "\tmovie\n" +
                "set\n" +
                "\ttmdb_vote_average = ?,\n" +
                "\ttmdb_vote_count = ?,\n" +
                "\ttmdb_poster_path = ?,\n" +
                "\ttmdb_overview = ?\n" +
                "where\n" +
                "\tmovie_nm_en = ? and\n" +
                "\tyear = ?;");

        int year = 1899;

        while(++year<2025) {
            PreparedStatement countPstmt = dbConn.prepareStatement(selectCountSQL.toString());
            countPstmt.setInt(1, year);
            countPstmt.setInt(2, year);
            ResultSet countRs = countPstmt.executeQuery();
            countRs.next();
            int count = countRs.getInt(1);
            System.out.println(year + " : " + count);
            if(count<1) continue;
            PreparedStatement selectPstmt = dbConn.prepareStatement(selectSQL.toString());
            selectPstmt.setInt(1, year);
            selectPstmt.setInt(2, year);
            ResultSet rs = selectPstmt.executeQuery();
            while (rs.next()) {
                System.out.println(year + " " + rs.getString(1) + " " + rs.getDouble(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5));
                PreparedStatement updatePstmt = dbConn.prepareStatement(updateSQL.toString());
                updatePstmt.setDouble(1, rs.getDouble(2));
                updatePstmt.setInt(2, rs.getInt(3));
                updatePstmt.setString(3, rs.getString(4));
                updatePstmt.setString(4, rs.getString(5));
                updatePstmt.setString(5, rs.getString(1));
                updatePstmt.setInt(6, year);
                updatePstmt.executeUpdate();
            }
        }
    }
}
