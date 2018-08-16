package ir.ord.application.dto.source_dtos;

import ir.ord.application.dto.ButtonDto;

/**
 * Created by vahid on 7/26/17.
 */
public class ButtonSourceDto {
    String buttonVersion;
    String ip;
    String funcKey;
    Object ordButton;

    public String getButtonVersion() {
        return buttonVersion;
    }

    public void setButtonVersion(String buttonVersion) {
        this.buttonVersion = buttonVersion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFuncKey() {
        return funcKey;
    }

    public void setFuncKey(String funcKey) {
        this.funcKey = funcKey;
    }

    public Object getOrdButton() {
        return ordButton;
    }

    public void setOrdButton(Object ordButton) {
        this.ordButton = ordButton;
    }
}
