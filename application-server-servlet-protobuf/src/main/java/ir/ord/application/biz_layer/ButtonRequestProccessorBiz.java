package ir.ord.application.biz_layer;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;

import javax.ejb.Stateless;

/**
 * Created by vahid on 4/17/17.
 */
@Stateless
public class ButtonRequestProccessorBiz {

    public int proccessButtonRequest(String deviceId,
                                      String deviceVersion,
                                      String bundleId,
                                      String key,
                                      byte[] body) {

        //TODO proccess this req and trigger a request to proxy server

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Helper.appLogger.error("proccessButtonRequest", e);
        }

        return ResponseStatus.OK.getCode();

    }

    public int proccessButtonCancelRequest(String deviceId,
                                      String deviceVersion,
                                      String bundleId,
                                      String key,
                                      byte[] body) {

        //TODO proccess this req and trigger a request to proxy server

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            Helper.appLogger.error("proccessButtonCancelRequest", e);
        }
        return ResponseStatus.OK.getCode();
    }
}
