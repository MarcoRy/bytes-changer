package com.mr;

import org.apache.commons.codec.DecoderException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileChangerFacade {
    FileChanger fileChanger = new FileChanger();
    FileFinder fileFinder = new FileFinder();

    public void fileHexChangerAndSave(String pathToSearch, List<String> extensions, String bytesYouWantToChange, String changeOnThisBytes) throws IOException, DecoderException {
        List<String> pathToSpecificFilesList = fileFinder.specificFileExtensionFinder(pathToSearch, extensions);
        List<byte[]> filesListInByte = fileChanger.changerFileToBytesList(pathToSpecificFilesList);
        for (int i = 0; i < filesListInByte.size(); i++) {
            byte[] a = fileChanger.replaceBytesWriteInHex(filesListInByte.get(i), bytesYouWantToChange, changeOnThisBytes);
            FileOutputStream fos = new FileOutputStream(pathToSpecificFilesList.get(i));
            fos.write(a);
            fos.close();
        }
    }

    public void fileBinaryChangerAndSave(String pathToSearch, List<String> extensions, String bytesYouWantToChange, String changeOnThisBytes) throws IOException, DecoderException {
        List<String> pathToSpecificFilesList = fileFinder.specificFileExtensionFinder(pathToSearch, extensions);
        List<byte[]> filesListInByte = fileChanger.changerFileToBytesList(pathToSpecificFilesList);
        for (int i = 0; i < filesListInByte.size(); i++) {
            byte[] a = fileChanger.replaceBytesWriteInBinary(filesListInByte.get(i), bytesYouWantToChange, changeOnThisBytes);
            FileOutputStream fos = new FileOutputStream(pathToSpecificFilesList.get(i));
            fos.write(a);
            fos.close();
        }
    }
}