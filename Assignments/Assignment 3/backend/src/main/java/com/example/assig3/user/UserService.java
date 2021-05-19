package com.example.assig3.user;


import com.example.assig3.user.dto.*;
import com.example.assig3.user.dto.UserListDTO;
import com.example.assig3.user.dto.UserMinimalDTO;
import com.example.assig3.user.mapper.*;
import com.example.assig3.user.model.ERole;
import com.example.assig3.user.model.Role;
import com.example.assig3.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    private User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }

    private User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + username));
    }

    public void delete(Long id) {
        userRepository.deleteById((id));
    }

    public List<UserListDTO> allDoctors(){

        Role role = roleRepository.findByName(ERole.valueOf("DOCTOR")).orElseThrow(() -> new RuntimeException("Cannot find role: "));;
        return userRepository.findUsersByRoles(role)
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }


    public UserDTO create(UserDTO userDTO) {

        User user = userMapper.fromUserDTO(userDTO);
        Set<Role> set = userDTO.getRoles();


        if(set == null){
            Role doctor = roleRepository.findByName(ERole.DOCTOR)
                    .orElseThrow(() -> new RuntimeException("Cannot find DOCTOR role"));
            set.add(doctor);
        }
        user.setRoles(set);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setUsername(userDTO.getName());

        return userMapper.toUserDTO(userRepository
                .save(user));
    }

    public UserDTO edit(Long id, UserDTO userDTO) {
        User user = findById(id);
        user.setUsername(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        if(!userDTO.getPassword().equals("")){
            user.setPassword(encoder.encode(userDTO.getPassword()));
        }


        return userMapper.toUserDTO(userRepository.save(user));
    }

}
