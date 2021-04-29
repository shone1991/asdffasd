package com.example.demo.controller;

import com.example.demo.mapstruct.dtos.UserGetDto;
import com.example.demo.mapstruct.dtos.UserPostDto;
import com.example.demo.mapstruct.mappers.MapStructMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private MapStructMapper mapstructMapper;

    private UserRepository userRepository;

    @Autowired
    public UserController(
            MapStructMapper mapstructMapper,
            UserRepository userRepository
    ) {
        this.mapstructMapper = mapstructMapper;
        this.userRepository = userRepository;
    }
    @PostMapping()
    public ResponseEntity<Void> create(
            @Valid @RequestBody UserPostDto userPostDto
    ) {
        userRepository.save(
                mapstructMapper.userPostDtoToUser(userPostDto)
        );

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getById(
            @PathVariable(value = "id") int id
    ) {
        return new ResponseEntity<>(
                mapstructMapper.userToUserGetDto(
                        userRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }
}
