package in.springsecurity.payload.carDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandDto {

    @JsonProperty("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
