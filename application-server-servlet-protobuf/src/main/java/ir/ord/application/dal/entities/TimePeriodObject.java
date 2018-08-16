package ir.ord.application.dal.entities;

/**
 * Created by vahid on 4/26/17.
 */
//@Embeddable
public class TimePeriodObject {

    private Long timeStamp;
//    @Embedded
    private TimePeriodEntity timePeriodEntity;
    private String description;
    private String actor;
    private Long date;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public TimePeriodEntity getTimePeriodEntity() {
        return timePeriodEntity;
    }

    public void setTimePeriodEntity(TimePeriodEntity timePeriodEntity) {
        this.timePeriodEntity = timePeriodEntity;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
