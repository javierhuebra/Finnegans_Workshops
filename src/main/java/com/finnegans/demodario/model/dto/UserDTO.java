package com.finnegans.demodario.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("user") //Al poner esto si envio el json bajo el nombre user me lo mapea a username
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("name")
    private String name;

}
