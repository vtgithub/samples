package factory_method;

public class TxtFileReader implements CustomFileReader {
    public String getFileContentAsString() {
        return "txt file reader";
    }
}
