package ir.ord.application.dal.entities;



import ir.ord.application.accessories.DaoHelper;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

/**
 * Created by vahid on 4/26/17.
// */
//@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"weekDay","fromTime","toTime"}))
public class TimePeriodEntity{
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
    @DecimalMin("0")
    @DecimalMax("6")
    private Integer weekDay;
    @DecimalMin("0")
    @DecimalMax("24")
    private Integer fromTime;
    @DecimalMin("0")
    @DecimalMax("24")
    private Integer toTime;

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getFromTime() {
        return fromTime;
    }

    public void setFromTime(Integer fromTime) {
        this.fromTime = fromTime;
    }

    public Integer getToTime() {
        return toTime;
    }

    public void setToTime(Integer toTime) {
        this.toTime = toTime;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
