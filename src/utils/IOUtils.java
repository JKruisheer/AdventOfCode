package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IOUtils {

    public static List<String> loadInputByPath(String path){
        Path syspath = Paths.get(path);
        try {
            return Files.readAllLines(syspath);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load path. It is present and/or correct?");
        }
    }
}
