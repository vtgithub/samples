package ir.ord.application.accessories;

/**
 * Created by vahid on 8/14/17.
 */
public class PersianDate {

    private Short Year;
    private Byte month;
    private Byte day;
    private Byte dayOfWeek;
    private Byte hour;
    private Byte minute;

    public PersianDate(Short year, Byte month, Byte day, Byte dayOfWeek, Byte hour, Byte minute) {
        Year = year;
        this.month = month;
        this.day = day;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.minute = minute;
    }

    public Short getYear() {
        return Year;
    }

    public void setYear(Short year) {
        Year = year;
    }

    public Byte getMonth() {
        return month;
    }

    public void setMonth(Byte month) {
        this.month = month;
    }

    public Byte getDay() {
        return day;
    }

    public void setDay(Byte day) {
        this.day = day;
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
        return "PersianDate{" +
                "Year=" + Year +
                ", month=" + month +
                ", day=" + day +
                ", dayOfWeek=" + dayOfWeek +
                ", hour=" + hour +
                ", minute=" + minute +
                '}';
    }
}
