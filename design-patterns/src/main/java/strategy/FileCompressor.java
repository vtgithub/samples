package strategy;

import java.io.File;
import java.util.List;

public interface FileCompressor {
    void compressFiles(List<File> fileList);
}
