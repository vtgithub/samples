package factory_method;

public class DefaultFileReader implements CustomFileReader {
    public String getFileContentAsString() {
        return "default file reader";
    }
}
