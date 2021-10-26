package de.neuefische.devquiz.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CodeToAccessToken {

    @JsonProperty("client_id")
    private final String clientId;
    @JsonProperty("client_secret")
    private final String clientSecret;
    @JsonProperty
    private final String code;

}
