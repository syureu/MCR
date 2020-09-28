package com.ssafy.mcr.recommend;

import com.ssafy.mcr.dto.RecommendListV1;
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

    public RecommendListV1 simpleRecommend(String genre) {
        List<String> command = new ArrayList<>();
        command.add("python");
        command.add("simple_recommend.py");
        command.add("Science Fiction");
        try {
            execPython(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void execPython(List<String> command) throws IOException {
        CommandLine commandLine = CommandLine.parse(command.get(0));
        for(int i=1; i<command.size(); ++i) {
            commandLine.addArgument(command.get(i));
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setStreamHandler(new PumpStreamHandler(outputStream));
        executor.setWorkingDirectory(new File("C:\\Users\\multicampus\\Documents\\s03p22d104\\recommend\\recommend_try"));

        executor.execute(commandLine);
        System.out.println(outputStream.toString());
    }
}
