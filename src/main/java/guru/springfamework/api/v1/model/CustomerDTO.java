package guru.springfamework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by jt on 9/24/17.
 */

@Data
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    @JsonProperty("url") // custom property name in json
    private String url;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public CustomerDTO withUrl(){
        setUrl("/api/v1/customers/" + id);
        return this;
    }
}
