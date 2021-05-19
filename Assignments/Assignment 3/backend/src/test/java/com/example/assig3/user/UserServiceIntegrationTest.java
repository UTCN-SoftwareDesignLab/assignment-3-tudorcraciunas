package com.example.assig3.user;

import com.example.assig3.TestCreationFactory;
import com.example.assig3.BaseControllerTest;
import com.example.assig3.user.dto.UserDTO;
import com.example.assig3.user.dto.UserListDTO;
import com.example.assig3.user.dto.UserMinimalDTO;
import com.example.assig3.user.mapper.UserMapper;
import com.example.assig3.user.model.ERole;
import com.example.assig3.user.model.Role;
import com.example.assig3.user.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.assig3.UrlMapping.ENTITY;
import static com.example.assig3.UrlMapping.USERS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    void findAll() {
        int nrUsers = 10;
        List<User> users = new ArrayList<>();
        for (int i = 0; i < nrUsers; i++) {
            User user = User.builder()
                    .username("User " + i)
                    .password(UUID.randomUUID().toString())
                    .email("user" + i + "@gmail.com")
                    .build();
            users.add(user);
            userRepository.save(user);
        }

        List<UserMinimalDTO> userMinimalDTOS = userService.allUsersMinimal();

        for (int i = 0; i < nrUsers; i++) {
            assertEquals(users.get(i).getId(), userMinimalDTOS.get(i).getId());
            assertEquals(users.get(i).getUsername(), userMinimalDTOS.get(i).getName());
        }
    }


    @Test
    void create() {
        List<User> users = TestCreationFactory.listOf(User.class);
        userRepository.saveAll(users);

        List<UserListDTO> userDtos = userService.allUsersForList();
        Assertions.assertEquals(userDtos.size(), users.size());
    }


    @Test
    void edit(){
        roleRepository.save(Role.builder().name(ERole.SECRETARY).build());

        UserDTO user = UserDTO.builder()
                .name("Firstuser")
                .password("password")
                .email("a@a.com")
                .build();

        user = userService.create(user);
        user.setName("bro");
        UserDTO updated = userService.edit(user.getId(), user);

        Assertions.assertEquals(updated.getName(), "bro");
    }

}