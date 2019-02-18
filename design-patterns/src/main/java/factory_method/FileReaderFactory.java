package factory_method;

public class FileReaderFactory {

    public  static CustomFileReader getFileReader(FileReaderEnum fileReaderType){
        switch (fileReaderType){
            case TXT:
                return new TxtFileReader();
            case CSV:
                return new CsvFileReader();
        }
        return new DefaultFileReader();
    }
}
