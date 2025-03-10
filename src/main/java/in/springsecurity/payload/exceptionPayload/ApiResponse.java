package in.springsecurity.payload.exceptionPayload;

import java.util.Date;


public class ApiResponse {
    private String message;
    private Date date;
    private String request;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
