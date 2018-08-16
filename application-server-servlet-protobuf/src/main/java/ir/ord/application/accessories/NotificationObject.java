package ir.ord.application.accessories;

import java.util.List;

/**
 * Created by vahid on 5/12/17.
 */
//master-key = a914ccbc-5f39-4dd7-86d8-c9a443376996
public class NotificationObject {
    private String app_id = "4c1e5435-df7f-4b9a-8c93-cf132b16f05f";
    private List<String> include_ios_tokens;
    private Boolean content_available;
    private Integer priority;
    private ContentsObject contents = new ContentsObject();
    private HeadingsObject headings = new HeadingsObject();
    private SubtitleObject subtitle = new SubtitleObject();
    private DataObject data = new DataObject();


    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public List<String> getInclude_ios_tokens() {
        return include_ios_tokens;
    }

    public void setInclude_ios_tokens(List<String> include_ios_tokens) {
        this.include_ios_tokens = include_ios_tokens;
    }

    public ContentsObject getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents.setEn(contents);
    }

    public HeadingsObject getHeadings() {
        return headings;
    }

    public void setHeadings(String headings) {
        this.headings.setEn(headings);
    }

    public SubtitleObject getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle.setEn(subtitle);
    }

    public Boolean getContent_available() {
        return content_available;
    }

    public void setContent_available(Boolean content_available) {
        this.content_available = content_available;
    }

    public DataObject getData() {
        return data;
    }

    public void setData(Integer type, Integer event) {
        this.data.setType(type);
        this.data.setEvent(event);
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    //----- helper classes
    class ContentsObject{
        private String en;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }
    }

    class HeadingsObject{
        private String en;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }
    }

    class SubtitleObject{
        private String en;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }
    }

    class DataObject{
        private Integer type;
        private Integer event;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public Integer getEvent() {
            return event;
        }

        public void setEvent(Integer event) {
            this.event = event;
        }
    }
}
