package com.example.assig3.user.dto;


import com.example.assig3.user.model.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@Data
@SuperBuilder
@RequiredArgsConstructor
public class UserDTO {

    @Builder.Default
    private Long id = -1L;

    @Builder.Default
    private String name = "";

    @Builder.Default
    private String email = "";

    @Builder.Default
    private String password = "";

    @Builder.Default
    private Set<Role> roles =  new HashSet<>();

}