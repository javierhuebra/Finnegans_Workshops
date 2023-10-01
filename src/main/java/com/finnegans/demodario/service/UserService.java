package com.finnegans.demodario.service;

import com.finnegans.demodario.exception.custom.EmptyElementException;
import com.finnegans.demodario.exception.custom.NotCreatedException;
import com.finnegans.demodario.exception.custom.UnauthorizedException;
import com.finnegans.demodario.model.User;
import com.finnegans.demodario.model.dto.UserDTO;
import com.finnegans.demodario.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO) {
        if(checkUserDTO(userDTO, false)){//Si pasa la validación se crea el usuario
           return this.userRepository.save(new User(userDTO));
        }
        throw new NotCreatedException("Error in Save New User");
    }

    public UserDTO loginWithCredentials(String username, String password) {
        if(
                this.checkUserDTO(UserDTO
                                    .builder()
                                    .username(username)
                                    .password(password)
                                    .build(),
                                    Boolean.TRUE)
        ){
           return this.userRepository.findByUsernameAndPassword(username, password)
                    .orElseThrow(
                            () -> new UnauthorizedException("Invalid Credentials") //Esta es la funcion flecha de Java 8, es una forma de crear una función anonima, es decir, una función que no tiene nombre, en este caso se utiliza para crear una excepción.
                    ).toUserDTO();
        }

        throw new UnauthorizedException("Invalid Credentials");
    }

    public List<UserDTO> getListAllUsersInDB() { //Esto es nuevo, se agrega para poder listar todos los usuarios de la base de datos en una lista.
        return this.userRepository
                .findAll()
                .stream()
                .map(User::toUserDTO)         //El :: es para hacer referencia a un método, en este caso a toUserDTO, es como si fuera un puntero a la función.
                .collect(Collectors.toList());//El collect es para convertir el stream en una lista, Collectors.toList() es una función que devuelve una lista.
    }                                         //Es importante conocer los metodos terminales y los no terminales, los terminales son los que devuelven un valor, en este caso el collect, los no terminales son los que no devuelven un valor, en este caso el map, el interprete de java necesita ambos sino no funciona correctamente.

    private Boolean checkUserDTO(UserDTO userDTO, boolean isForLogin) {//Se valida que los campos no esten vacios

        if(!isForLogin){//Si isForLogin es false, se valida el name sino se intepreta que se esta utilizando para checkear el login, es para reutilizar el metodo.
            if(StringUtils.isEmpty(userDTO.getName())){
                throw new EmptyElementException("Name is empty");
            }
        }
        if(StringUtils.isEmpty(userDTO.getUsername())){
            throw new EmptyElementException("Username is empty");
        }
        if(StringUtils.isEmpty(userDTO.getPassword())){
            throw new EmptyElementException("Password is empty");
        }
        return Boolean.TRUE;
    }

}
