package com.ssafy.mcr.recommend;

import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.dto.RecommendV1;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Recommend {

    /*
    public RecommendListV1 simpleRecommend(String genre) {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("daum_movie_simple_recommend_by_qualified.py");
        command.add("코미디");
        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     */

    private String execPython(List<String> command) throws IOException {
        CommandLine commandLine = CommandLine.parse(command.get(0));
        for (int i = 1; i < command.size(); ++i) {
            commandLine.addArgument(command.get(i));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        executor.setWorkingDirectory(new File("C:\\Users\\multicampus\\Documents\\s03p23d104\\recommend\\recommend_try"));
        // executor.setWorkingDirectory(new File("$HOME\\source\\recommend\\recommend_try");

        executor.execute(commandLine);
        return outputStream.toString("Cp949");
        // return outputStream.toString("UTF-8");
    }

    public RecommendListV1 simpleRecommendByGenre(String genre) throws IOException {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("daum_movie_simple_recommend_by_qualified.py");
        command.add(genre);
        String[] retList = execPython(command).split("\r\n");
        List<RecommendV1> list = new ArrayList<>();
        for (int i = 0; i < retList.length / 3; ++i) {
            RecommendV1 rv1 = new RecommendV1(
                    Long.parseLong(retList[i]),
                    retList[i + (retList.length / 3 * 2)],
                    retList[i + (retList.length / 3)]
            );
            list.add(rv1);
        }
        return new RecommendListV1(
                genre + " 장르의 추천 영화",
                list
        );

    }
}
