package com.mr;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileChanger {

    public List<byte[]> changerFileToBytesList(List<String> pathsFiles) throws IOException {
        List<byte[]> fileInBytesList = new ArrayList<>();
        for (String pathsFile : pathsFiles) {
            Path path = Paths.get(pathsFile);
            fileInBytesList.add(Files.readAllBytes(path));
        }
        return fileInBytesList;
    }

    public byte[] bytesReplacer(byte[] source, byte[] find, byte[] replace) {
        byte[] result = new byte[0];
        for (int i = 0; i < source.length; i++) {
            boolean founded = false;
            for (int j = 0; j < find.length; j++) {
                int toCheck = i + j;
                if (toCheck >= source.length || source[toCheck] != find[j]) {
                    break;
                }
                if (j == find.length - 1) {
                    founded = true;
                }
            }
            if (founded) {
                result = ArrayUtils.addAll(result, replace);
                i = i + find.length - 1;
            } else {
                result = ArrayUtils.add(result, source[i]);
            }
        }
        return result;
    }

    public byte[] replaceBytesWriteInHex(byte[] source, String hexFindToReplace, String hexToReplace) throws DecoderException {
        byte[] bytesHexToReplace = Hex.decodeHex(hexToReplace.toCharArray());
        byte[] bytesFindToReplace = Hex.decodeHex(hexFindToReplace.toCharArray());

        return bytesReplacer(source, bytesFindToReplace, bytesHexToReplace);
    }

    public byte[] replaceBytesWriteInBinary(byte[] source, String binaryFindToReplace, String binaryToReplace) throws DecoderException {
        byte[] bytesToReplace = new BigInteger(binaryToReplace.replaceAll("\\s", ""), 2).toByteArray();
        byte[] bytesFindToReplace = new BigInteger(binaryFindToReplace.replaceAll("\\s", ""), 2).toByteArray();
        return bytesReplacer(source, bytesFindToReplace, bytesToReplace);
    }
}


