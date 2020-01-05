package com.mr;

import org.apache.commons.codec.DecoderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileChangerApplication {

    private static FileChangerFacade fileChangerFacade = new FileChangerFacade();

    public static void main(String[] args) throws IOException, DecoderException {
        Scanner scanner = new Scanner(System.in);
        String path = "";

        boolean isPathCorrect = false;
        while (!isPathCorrect) {
            System.out.println("Write the path you want to search: e.g C:/Users/dell/Desktop/tasks/tasks");
            path = scanner.nextLine();
            Path pathChecker = Paths.get(path);
            if (!Files.exists(pathChecker) || path.equals("")) {
                System.out.println("Probably you write a wrong path, try again");
            } else {
                isPathCorrect = true;
            }
        }

        String ext = "";
        List<String> extensions = new ArrayList<>();
        while (!(ext.equals("done"))) {
            System.out.println("Write the extension(one extension per line) of files you want to find: (e.g. txt) if you finish write \"done\"");
            ext = scanner.nextLine();
            extensions.add(ext);
        }

        boolean isChooseGood = false;
        while (!isChooseGood) {
            System.out.println("Write how you want represent bytes: Choose \"hex\" or \"binary\"");
            String system = scanner.nextLine();
            if (system.contains("hex")) {
                while (!isChooseGood) {
                    System.out.println("Write a bytes you want to  find: e.g. 6b6f7461, hex must be even");
                    String find = scanner.nextLine();
                    if (find.matches("[0-9A-Fa-f]+") && find.length() % 2 == 0) {
                        while (!isChooseGood) {
                            System.out.println("Write a bytes you want to  change: e.g. 78787878, hex must be even");
                            String replace = scanner.nextLine();
                            if (replace.matches("[0-9A-Fa-f]+") && replace.length() % 2 == 0) {
                                isChooseGood = true;
                                fileChangerFacade.fileHexChangerAndSave(path, extensions, find, replace);
                                System.out.println("Program end");
                            } else {
                                System.out.println("You write wrong hex.Try again");
                            }
                        }
                    } else {
                        System.out.println("You write wrong hex.Try again");
                    }
                }
            } else if (system.contains("binary")) {
                while (!isChooseGood) {
                    System.out.println("Write a bytes you want to find: e.g. 01101011 01101111 01110100 01100001, minimum have to be 8 numbers");
                    String find = scanner.nextLine();
                    if (find.matches("^[01\\s]+$") && find.replaceAll("\\s", "").length() % 8 == 0) {
                        while (!isChooseGood) {
                            System.out.println("Write a bytes you want to change: e.g. 01111000 01111000 01111000 01111000,  minimum have to be 8 numbers");
                            String replace = scanner.nextLine();
                            if (replace.matches("^[01\\s]+$") && replace.replaceAll("\\s", "").length() % 8 == 0) {
                                isChooseGood = true;
                                fileChangerFacade.fileBinaryChangerAndSave(path, extensions, find, replace);
                                System.out.println("Congratulations, bytes are changed");
                            } else {
                                System.out.println("You write wrong binary.Try again");
                            }
                        }
                    } else {
                        System.out.println("You write wrong binary.Try again");
                    }
                }
            } else {
                System.out.println("Write correct option");
            }
        }
        scanner.close();
    }
}


