package ir.ord.application.accessories;

/**
 * Created by vahid on 8/14/17.
 */
public class CustomDate {

    private Byte dayOfWeek;
    private Byte hour;
    private Byte minute;

    public CustomDate(Byte dayOfWeek, Byte hour, Byte minute) {
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
    }

    public Byte getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Byte dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Byte getHour() {
        return hour;
    }

    public void setHour(Byte hour) {
        this.hour = hour;
    }

    public Byte getMinute() {
        return minute;
    }

    public void setMinute(Byte minute) {
        this.minute = minute;
    }

    @Override
    public String toString() {
        return "CustomDate{" +
                "dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
