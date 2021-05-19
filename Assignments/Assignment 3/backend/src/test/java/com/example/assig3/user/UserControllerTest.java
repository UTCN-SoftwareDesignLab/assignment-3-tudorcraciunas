package com.example.assig3.user;

import com.example.assig3.BaseControllerTest;
import com.example.assig3.TestCreationFactory;
import com.example.assig3.security.AuthService;
import com.example.assig3.user.dto.UserDTO;
import com.example.assig3.user.dto.UserListDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.example.assig3.TestCreationFactory.*;
import static com.example.assig3.UrlMapping.ENTITY;
import static com.example.assig3.UrlMapping.USERS;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends BaseControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService userService;

    @Mock
    private AuthService authService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        controller = new UserController(userService, authService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allUsers() throws Exception {
        List<UserListDTO> userListDTOs = TestCreationFactory.listOf(UserListDTO.class);
        when(userService.allUsersForList()).thenReturn(userListDTOs);

        ResultActions result = mockMvc.perform(get(USERS));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(userListDTOs));
    }

    @Test
    void delete() throws Exception{
        long id = randomLong();
        doNothing().when(userService).delete(id);

        ResultActions result = performDeleteWIthPathVariable(USERS + ENTITY, id);
        verify(userService, times(1)).delete(id);

        result.andExpect(status().isOk());
    }

//    @Test
//    void createUser() throws Exception {
//        UserDTO dto = TestCreationFactory.newUserListDTO();
//        when(userService.create(dto)).thenReturn(dto);
//
//        ResultActions result = performPostWithRequestBody(USERS, dto);
//        result.andExpect(status().isOk());
//    }

    @Test
    void create() throws Exception {
        UserDTO dto = UserDTO.builder()
                .name(randomString())
                .email(randomEmail())
                .password(randomString())
                .id(randomLong())
                .build();

        when(userService.create(dto)).thenReturn(dto);

        ResultActions result = performPostWithRequestBody(USERS, dto);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(dto));

    }

    @Test
    void edit() throws Exception {
        long id = randomLong();
        UserDTO dto = UserDTO.builder()
                .id(id)
                .name(randomString())
                .build();

        when(userService.edit(id, dto)).thenReturn(dto);

        ResultActions result = performPatchWithRequestBodyAndPathVariable(USERS + ENTITY, dto, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(dto));
    }
}