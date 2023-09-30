package com.finnegans.demodario.service;

import com.finnegans.demodario.exception.custom.EmptyElementException;
import com.finnegans.demodario.exception.custom.NotCreatedException;
import com.finnegans.demodario.model.User;
import com.finnegans.demodario.model.dto.UserDTO;
import com.finnegans.demodario.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(UserDTO userDTO) {
        if(checkUserDTO(userDTO)){//Si pasa la validación se crea el usuario
           return this.userRepository.save(new User(userDTO));
        }
        throw new NotCreatedException("Error in Save New User");
    }

    private Boolean checkUserDTO(UserDTO userDTO) {//Se valida que los campos no esten vacios
        if(StringUtils.isEmpty(userDTO.getName())){
            throw new EmptyElementException("Name is empty");
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
