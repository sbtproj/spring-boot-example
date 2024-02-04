package at.shtrans.rest.response;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ErrorResponse extends RestResponse {

    private List<String> errors = new LinkedList<>();

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorResponse that)) return false;
        return Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(errors);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ErrorResponse{");
        sb.append("status='").append(getStatus()).append('\'');
        sb.append(", date=").append(getDate());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }
}
