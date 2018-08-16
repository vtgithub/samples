package ir.ord.application.dto;

/**
 * Created by vahid on 5/20/17.
 */
public class ChangeTimeDto {
    private String orderId;
    private TimePeriodDto timePeriodDto;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public TimePeriodDto getTimePeriodDto() {
        return timePeriodDto;
    }

    public void setTimePeriodDto(TimePeriodDto timePeriodDto) {
        this.timePeriodDto = timePeriodDto;
    }
}
