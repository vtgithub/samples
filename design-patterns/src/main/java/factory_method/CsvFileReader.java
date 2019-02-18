package factory_method;

public class CsvFileReader implements CustomFileReader {
    public String getFileContentAsString() {
        return "csv file reader";
    }
}
