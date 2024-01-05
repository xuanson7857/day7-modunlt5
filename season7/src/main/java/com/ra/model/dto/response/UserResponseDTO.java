package com.ra.model.dto.response;

import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private Long id;
    private String userName;
    private String fullName;
    private boolean status;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.fullName = user.getFullName();
        this.status = user.isStatus();
    }
}
