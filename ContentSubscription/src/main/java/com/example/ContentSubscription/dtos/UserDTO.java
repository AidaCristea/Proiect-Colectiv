package com.example.ContentSubscription.dtos;

import com.example.ContentSubscription.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private Long userId;
    private String email;
    private String password;
    private String userType;
    private String photoURL;

}
