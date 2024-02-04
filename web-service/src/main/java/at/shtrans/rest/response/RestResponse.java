package at.shtrans.rest.response;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class RestResponse {

    public RestResponse() {
    }

    public RestResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private HttpStatus status;

    private final Date date = new Date();

    private String message;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestResponse that)) return false;
        return Objects.equals(status, that.status) && Objects.equals(date, that.date) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, date, message);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RestResponse{");
        sb.append("status='").append(status).append('\'');
        sb.append(", date=").append(date);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
