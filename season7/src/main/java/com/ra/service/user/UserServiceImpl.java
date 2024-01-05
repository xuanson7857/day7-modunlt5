package com.ra.service.user;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.model.entity.User;
import com.ra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(UserResponseDTO::new);
    }

    @Override
    public UserResponseDTO saceOrUpdate(UserRequestDTO userRequestDTO) throws CustomException {
        // check da ton tai
        if (userRepository.existsByUserName(userRequestDTO.getUserName())) {
            throw new CustomException("userName da ton tai");
        }
        User user = User.builder().
                id(userRequestDTO.getId()).
                userName(userRequestDTO.getUserName()).
                fullName(userRequestDTO.getFullName()).
                password(userRequestDTO.getPassword()).build();
        User newUser = userRepository.save(user);
        return UserResponseDTO.builder().id(newUser.getId()).userName(newUser.getUserName()).fullName(newUser.getFullName()).build();
    }

    @Override
    public UserResponseDTO findById(Long id) throws CustomException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return UserResponseDTO.builder().
                    userName(user.getUserName()).
                    fullName(user.getFullName()).
                    status(user.isStatus())
                    .build();
        }
        throw new CustomException("Not Found");
    }

    @Override
    public void delete(Long id) throws CustomException {
        userRepository.deleteById(id);
    }
}
