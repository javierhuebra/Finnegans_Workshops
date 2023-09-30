package com.finnegans.demodario.controller;

import com.finnegans.demodario.model.User;
import com.finnegans.demodario.model.dto.UserDTO;
import com.finnegans.demodario.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Se deja el value vacío porque el endpoint lo detecta por ser el unico post que vamos a tener.
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody UserDTO userDTO) { //cambie el void por User para que me retorne el usuario creado
      return this.userService.saveUser(userDTO); //Le agrega el this para asegurarse de que estoy accediendo al atributo de esta clase, si no se repite el nombre no haria falta, pero por las dudas vio.
    }

}
