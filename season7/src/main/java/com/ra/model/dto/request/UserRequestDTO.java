package com.ra.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDTO {
    private Long id;
    @NotEmpty(message = "vui long dien vao userName")
    private String userName;
    private String fullName;
    @Size(min = 3, message = "nhap 10 so")
    private String password;
}
