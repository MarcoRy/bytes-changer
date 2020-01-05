package com.mr;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {
    public List<String> specificFileExtensionFinder(String path, List<String> extensions) throws IOException {
        File filesPath = new File(path);
        String[] extensionsAdd = extensions.toArray(new String[0]);
        List<File> files = (List<File>) FileUtils.listFiles(filesPath, extensionsAdd, true);
        List<String> filesPathList = new ArrayList<>();
        for (File file : files) {
            try {
                filesPathList.add(file.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesPathList;
    }
}
