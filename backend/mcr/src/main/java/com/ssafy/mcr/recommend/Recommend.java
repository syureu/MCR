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
import java.util.Arrays;
import java.util.List;

@Component
public class Recommend {

    public RecommendListV1 simpleGenreRecommend(String genre) {
        return simpleGenreRecommend(genre, 15);
    }

    public RecommendListV1 simpleGenreRecommend(String genre, int count) {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("open_api_simple_recommend_use_qualified.py");
        command.add(genre);
        String ret = null;
        try {
            ret = execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ret == null) {
            return null;
        } else {
            List<String> iRetList = Arrays.asList(ret.split("\r\n"));
            List<RecommendV1> list = new ArrayList<>();
            for (int i = 0; i < iRetList.size() / 3; ++i) {

                list.add(new RecommendV1(
                        Long.parseLong(iRetList.get(i)),
                        iRetList.get(i + (iRetList.size() / 3)),
                        iRetList.get(i + (iRetList.size() / 3 * 2))
                ));
            }

            RecommendListV1 recommendList = new RecommendListV1(
                    "장르가 " + genre + "인 영화 추천",
                    list
            );
            return recommendList;
        }
    }

    private String execPython(List<String> command) throws IOException {
        CommandLine commandLine = CommandLine.parse(command.get(0));
        for (int i = 1; i < command.size(); ++i) {
            commandLine.addArgument(command.get(i));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        executor.setWorkingDirectory(new File("C:\\Users\\multicampus\\Documents\\s03p22d104\\recommend\\recommend_try"));

        executor.execute(commandLine);
        // System.out.println(outputStream.toString("utf8"));
        return outputStream.toString("Cp949");
    }
}
