package strategy;

import java.io.File;
import java.util.ArrayList;

//used  when multiple algorithm for specific task
//behavioral design pattern
//differ from state design pattern by passing context (wich algorithm used) by user
//multiple algorithm in runtime

public class Execute {
    public static void main(String[] args) {
        FileCompressor fileCompressor = new ZipFileCompressor();
        fileCompressor.compressFiles(new ArrayList<File>());
        fileCompressor = new RarFileCompressor();
        fileCompressor.compressFiles(new ArrayList<File>());
    }
}
