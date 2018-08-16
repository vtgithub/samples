package ir.ord.application.dto;

/**
 * Created by vahid on 5/11/17.
 */
public class AddGiftDto {
    private String phoneNumber;
    private GiftDto giftDto;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public GiftDto getGiftDto() {
        return giftDto;
    }

    public void setGiftDto(GiftDto giftDto) {
        this.giftDto = giftDto;
    }
}
