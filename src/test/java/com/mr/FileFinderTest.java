package com.mr;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FileFinderTest {

    private FileFinder fileFinder = new FileFinder();

    @Test
    public void testSpecificFileExtensionFinder() throws IOException {
        //Given
        String dir = "E:/Projects/bytes-changer/src/test/files";

        List<String> extensions = Arrays.asList("txt", dir);
        //When
        List<String> result = fileFinder.specificFileExtensionFinder(dir, extensions);
        //Then
        assertEquals(2, result.size());
    }
}

