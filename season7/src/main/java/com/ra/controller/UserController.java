package com.ra.controller;

import com.ra.exception.CustomException;
import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    // findById
    @GetMapping("/users/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws CustomException {
        UserResponseDTO userResponseDTO = userService.findById(id);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    // list user
    @GetMapping("/users")
    public ResponseEntity<Page<UserResponseDTO>> index(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<UserResponseDTO> responseDTO = userService.findAll(pageable);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // add
    @PostMapping("/users")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequestDTO userRequestDTO, BindingResult result) throws CustomException {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST);
        }
        UserResponseDTO userResponseDTO1 = userService.saceOrUpdate(userRequestDTO);
        return new ResponseEntity<>(userResponseDTO1, HttpStatus.OK);
    }

    // delete
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws CustomException {
        UserResponseDTO userResponseDTO = userService.findById(id);
        if (userResponseDTO != null) {
            userService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    // sua
    @PutMapping("/users/{id}")
    public ResponseEntity<?> update_user(@PathVariable("id") Long id, @RequestBody UserRequestDTO userRequestDTO) throws CustomException {
        userRequestDTO.setId(id);
        UserResponseDTO updatedUser = userService.saceOrUpdate(userRequestDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
