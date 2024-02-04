package at.shtrans.rest.response;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class CustomerResponse extends RestResponse {

    public CustomerResponse() {
        super.setStatus(HttpStatus.OK);
        super.setMessage("The request was completed successfully!");
    }

    private Long id;

    private Integer version;

    private String firstName;

    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerResponse that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(id, that.id) && Objects.equals(version, that.version) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, version, firstName, lastName);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CustomerResponse{");
        sb.append("status='").append(getStatus()).append('\'');
        sb.append(", date=").append(getDate());
        sb.append(", message='").append(getMessage()).append('\'');
        sb.append(", id=").append(id);
        sb.append(", version=").append(version);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}