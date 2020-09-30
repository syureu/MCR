package com.ssafy.mcr.recommend;

import com.ssafy.mcr.dto.RecommendListV1;
import com.ssafy.mcr.dto.RecommendV1;
import com.ssafy.mcr.exception.UnknownEnvironmentException;
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

    private String[] execPython(List<String> command) throws UnknownEnvironmentException{
        CommandLine envCheckCommand = CommandLine.parse("uname");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        CommandLine commandLine = CommandLine.parse(command.get(0));
        for (int i = 1; i < command.size(); ++i) {
            commandLine.addArgument(command.get(i));
        }
        try {
            executor.execute(envCheckCommand);
            // if pass it's aws(ubuntu 18.04) environment
            executor.setWorkingDirectory(new File("/home/ubuntu/source/s03p23d104/recommend/recommend_try"));
            executor.execute(commandLine);
            return outputStream.toString("UTF-8").split("\n");
        } catch(IOException e) {
            // it's maybe local windows(windows 10 1909) environment
            try {
                executor.setWorkingDirectory(new File("C:\\Users\\multicampus\\Documents\\s03p23d104\\recommend\\recommend_try"));
                executor.execute(commandLine);
                return outputStream.toString("Cp949").split("\r\n");
            } catch(IOException windowsE) {
                windowsE.printStackTrace();
                throw new UnknownEnvironmentException();
            }
        }
    }

    public RecommendListV1 simpleRecommendByGenre(String genre) throws UnknownEnvironmentException {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("daum_movie_simple_recommend_by_qualified.py");
        command.add(genre);
        String[] retList = execPython(command);
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
