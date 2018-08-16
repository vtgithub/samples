package ir.ord.application.services;

import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.Helper;
import ir.ord.application.dal.entities.SuccessPaymentObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by vahid on 5/8/17.
 */
@Path("/rest/pages")
public class PagesService {


    @Path("/bank")
    @GET
    @Produces("text/html")
    public String getBankPage() throws FileNotFoundException {
        String html = "<html>\n" +
                "<head>\n" +
                "    <title>payment Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>شبیه ساز پرداخت بانکی</h1>\n" +
                "    <form method=\"get\" action=\"http://localhost:8180/application-war/pages/user\">\n" +
                "        <input type=\"submit\" value=\"پرداخت\"/>\n" +
                "    </form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";

            return html;

    }

    @Path("/user")
    @GET
    @Produces("text/html")
    public String getBankToUserPage() throws FileNotFoundException {
        String html="<html>\n" +
                "<head>\n" +
                "    <title>payment Page</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>تاییدیه پرداخت</h1>\n" +
                "    <form method=\"post\" enctype=\"application/x-www-form-urlencoded\" action=\"http://localhost:8180/application-war/payment/fromUser\">\n" +
                "        <input type=\"submit\" value=\"تایید\"/>\n" +
                "        <input type=\"hidden\" name=\"Token\" value=\"abcde\">\n" +
                "        <input type=\"hidden\" name=\"OrderId\" value=\"16d9507a-459f-46df-b26e-b8e7fb42f338\">\n" +
                "        <input type=\"hidden\" name=\"ResCode\" value=\"0\">\n" +
                "    </form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";


        return html;
    }


}
