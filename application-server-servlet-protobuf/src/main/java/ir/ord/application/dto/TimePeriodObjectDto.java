package ir.ord.application.dto;

/**
 * Created by vahid on 5/3/17.
 */
public class TimePeriodObjectDto {
    private Long timeStamp;
    private TimePeriodDto timePeriodDto;
    private String description;
    private String actor;
    private Long date;

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TimePeriodDto getTimePeriodDto() {
        return timePeriodDto;
    }

    public void setTimePeriodDto(TimePeriodDto timePeriodDto) {
        this.timePeriodDto = timePeriodDto;
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

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
