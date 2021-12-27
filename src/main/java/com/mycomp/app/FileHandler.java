package com.mycomp.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static List<String> readQueryFromFile(String fileName) throws IOException {
        List<String> stringList = Files.readAllLines(Path.of(fileName));
        return stringList;
    }
}
