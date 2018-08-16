package ir.ord.application.dal.entities.source_entities;

import ir.ord.application.dal.entities.ButtonEntity;
import ir.ord.application.dto.ButtonDto;

/**
 * Created by vahid on 7/26/17.
 */
public class ButtonSourceEntity {
    String buttonVersion;
    String ip;
    String funcKey;
    ButtonEntity ordButton;

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

    public ButtonEntity getOrdButton() {
        return ordButton;
    }

    public void setOrdButton(ButtonEntity ordButton) {
        this.ordButton = ordButton;
    }
}
