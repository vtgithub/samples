package ir.ord.application;

/**
 * Created by vahid on 4/9/17.
 */
public enum CommodityState {
    CANCELED(0),//لغو شده
    REQUESTED(1),//ثبت شده
    PAID(2),//
    UNPAID(3),//عدم موجودی
    USER_CONFIRMED(4),//
    WAREHOUSE_PENDING(5),
    WAREHOUSE_PROCESSING(6),//پردازش انبار
    WAREHOUSE_CONFIRMED(7),
    LOGISTIC_PENDING(8),//آماده ارسال
    SENT(9),//ارسال شده
    DELIVERED(10),//تحویل شده
    UNDELIVERED(11);//عدم دریافت
//    UNPAIED_REQUESTED(11);

    private final Integer code;

    CommodityState(Integer i) {
        this.code = i;
    }

    public Integer getCode(){
        return code;
    }
}
