package com.ra.service.user;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponseDTO> findAll(Pageable pageable);

    UserResponseDTO saceOrUpdate(UserRequestDTO userRequestDTO) throws CustomException;

    UserResponseDTO findById(Long id) throws CustomException;

    void delete(Long id) throws CustomException;
}
