package com.mr;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FileChangerFacadeTest {

    FileChangerFacade fileChangerFacade = new FileChangerFacade();

    @Test
    public void testBytesFilesByHexChanger() throws IOException, DecoderException {

        //Given
        String textToFindInHex = "78";
        String textToReplaceInHex = "6B6B";
        String dir = "E:/Projects/bytes-changer/src/test/files";
        List<String> extensions = singletonList("txt");

        //When
        long fileSizeBeforeChanging = FileUtils.sizeOf(new File("E:/Projects/bytes-changer/src/test/files/test.txt"));
        fileChangerFacade.fileHexChangerAndSave(dir, extensions, textToFindInHex, textToReplaceInHex);

        //Then
        long fileSizeAfterChanging = FileUtils.sizeOf(new File("E:/Projects/bytes-changer/src/test/files/test.txt"));
        assertNotEquals(fileSizeBeforeChanging, fileSizeAfterChanging);

        //Revert
        String textToFindInHexAfter = "6B6B";
        String textToReplaceInHexAfter = "78";
        fileChangerFacade.fileHexChangerAndSave(dir, extensions, textToFindInHexAfter, textToReplaceInHexAfter);
    }

    @Test
    public void testBytesFilesByBinaryChanger() throws IOException, DecoderException {

        //Given
        String textToFindInBinary = "01111000";
        String textToReplaceInBinary = "01101011 01101011";
        String dir = "E:/Projects/bytes-changer/src/test/files";
        List<String> extensions = singletonList("txt");

        //When
        long fileSizeBeforeChanging = FileUtils.sizeOf(new File("E:/Projects/bytes-changer/src/test/files/test.txt"));
        fileChangerFacade.fileBinaryChangerAndSave(dir, extensions, textToFindInBinary, textToReplaceInBinary);

        //Then
        long fileSizeAfterChanging = FileUtils.sizeOf(new File("E:/Projects/bytes-changer/src/test/files/test.txt"));
        assertNotEquals(fileSizeBeforeChanging, fileSizeAfterChanging);

        //Revert
        String textToFindInBinaryRev = "01101011 01101011";
        String textToReplaceInBinaryRev = "01111000";
        fileChangerFacade.fileBinaryChangerAndSave(dir, extensions, textToFindInBinaryRev, textToReplaceInBinaryRev);
    }
}