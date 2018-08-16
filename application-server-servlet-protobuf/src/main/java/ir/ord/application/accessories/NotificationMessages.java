package ir.ord.application.accessories;

import ir.ord.application.BankEnum;
import ir.ord.application.UpdateNotifCreditEvent;

/**
 * Created by vahid on 5/12/17.
 */
public class NotificationMessages {

    public static String successOrderTitle = "ثبت موفق سفارش";
    public static String creditLowTitle = "کمبود اعتبار";
    public static String orderCanceledTitle = "لغو سفارش";
    public static String walletCharge = "افزایش اعتبار کیف پول";
    public static String walletChargeViaGift = "افزایش اعتبار کیف پول از طریق کارت شارژ";

    public static String getSuccessOrderMessage(String packageName, String orderId) {
        return "سفارش  "+ packageName + " با موفقیت ثبت شد." + "شماره سفارش "+Helper.getTrackNum(orderId);
    }

    public static String getCreditLowMessage(String packageName, String orderId, Integer expirationHour) {
        return "شماره سفارش شما "+Helper.getTrackNum(orderId)+" اعتبار شما برای خرید "+packageName+" کافی نمی باشد. " +
                "لطفا اعتبار خود را افزایش دهید. در غیر اینصورت سفارش شما تا "+expirationHour+" ساعت دیگر لغو خواهد شد.";

    }

    public static String getOrderCanceledMessage(String packageName, String orderId) {
        return "سفارش "+packageName +" با شماره "+ Helper.getTrackNum(orderId) +" به علت کمبود اعتبار لغو شد.";
    }

    public static String getWelcomMessage(String firstName, String lastName) {
        return firstName +" "+ lastName + " عزیز به ارد خوش آمدید.";

    }

    public static String getActivationMessage(String activationCode) {
        return  "کدفعال سازی دستگاه شما در ارد:"+ "\n" + activationCode;
    }


    public static String getChangePhoneNumber(String firstName, String lastName) {
        return firstName +" "+ lastName + " عزیز زین پس از طریق این شماره با شما در ارتباط خواهیم بود.";
    }

    public static String getBankChargeSMSMessage(Double amount, Integer bankId, Double newBalance) {
        return "پرداخت مبلغ " + Helper.getPersianNumbers(amount.longValue()) + " تومان" +  " از طریق درگاه " + Helper.getBankName(bankId) + " با موفقیت انجام شد."
                + " موجودی فعلی کیف پول شما: " + Helper.getPersianNumbers(newBalance.longValue()) + " تومان\n";
    }

    public static String getBankChargeNotificationMessage(Double amount, Integer bankId) {
        return "پرداخت مبلغ " + Helper.getPersianNumbers(amount.longValue()) + " تومان" +  " از طریق درگاه " + Helper.getBankName(bankId) + " با موفقیت انجام شد.";
    }

    public static String getGiftChargeSMSMessage(Double amount, String chargeCode,Double newBalance) {
        return "اعتبار " + Helper.getPersianNumbers(amount.longValue()) + " تومان از طریق کارت هدیه به شماره "+chargeCode+" به کیف پول شما افزوده شد."
                + "\nموجودی فعلی کیف پول شما: " + Helper.getPersianNumbers(newBalance.longValue()) + " تومان";
    }

    public static String getGiftChargeNotificationMessage(Double amount) {
        return "اعتبار " + Helper.getPersianNumbers(amount.longValue()) + " تومان از طریق کارت هدیه به کیف پول شما افزوده شد.";
    }
}
