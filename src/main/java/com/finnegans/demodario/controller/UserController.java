package com.finnegans.demodario.controller;

import com.finnegans.demodario.model.User;
import com.finnegans.demodario.model.dto.UserDTO;
import com.finnegans.demodario.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Se deja el value vacío porque el endpoint lo detecta por ser el unico post que vamos a tener.
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)//Prestar atencion a lo que dice consumes, no es lo mismo que el produces.
    public User saveUser(@RequestBody UserDTO userDTO) { //cambie el void por User para que me retorne el usuario creado
      return this.userService.saveUser(userDTO); //Le agrega el this para asegurarse de que estoy accediendo al atributo de esta clase, si no se repite el nombre no haria falta, pero por las dudas vio.
    }

    //Para hacer el login se utiliza un get, ya que no se va a crear nada, solo se va a consultar si existe el usuario.
    @GetMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)//Cambie el APPLICATION_JSON_VALUE por ALL_VALUE porque no se le envia nada en el body, solo se le envia por parametros y me tiraba un error 415. (El error era que ponia consumes en lugar de produces)
    public UserDTO loginUser(@RequestParam String username, @RequestParam String password) {
       return this.userService.loginWithCredentials(username, password);
    }

    //Get para obtener todos los usuarios
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDTO> getAllUsers() {
        return this.userService.getListAllUsersInDB();
    }

}
