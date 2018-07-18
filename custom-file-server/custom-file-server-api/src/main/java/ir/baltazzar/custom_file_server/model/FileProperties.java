package ir.baltazzar.custom_file_server.model;

public class FileProperties {
    private Integer width;
    private Integer height;
    private String tag;
    private String name;

    public FileProperties() {
    }

    public FileProperties(Integer width, Integer height, String tag) {
        this.width = width;
        this.height = height;
        this.tag = tag;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
