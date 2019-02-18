package factory_method;

public class Executor {
    public static void main(String[] args) {
        CustomFileReader fileReader = FileReaderFactory.getFileReader(FileReaderEnum.TXT);
        System.out.println(fileReader.getFileContentAsString());
        fileReader = FileReaderFactory.getFileReader(FileReaderEnum.CSV);
        System.out.println(fileReader.getFileContentAsString());
    }
}
