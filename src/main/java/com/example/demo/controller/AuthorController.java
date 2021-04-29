package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.mapstruct.dtos.AuthorAllDto;
import com.example.demo.mapstruct.dtos.AuthorDto;
import com.example.demo.mapstruct.mappers.MapStructMapper;
import com.example.demo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private MapStructMapper mapstructMapper;

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorController(
            MapStructMapper mapstructMapper,
            AuthorRepository authorRepository
    ) {
        this.mapstructMapper = mapstructMapper;
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody AuthorDto authorDto){
        Author author = authorRepository.save(mapstructMapper.authorDtoToAuthor(authorDto));
        return new ResponseEntity<>(author,HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorAllDto>> getAll() {
        return new ResponseEntity<>(
                mapstructMapper.authorsToAuthorAllDtos(
                        authorRepository.findAll()
                ),
                HttpStatus.OK
        );
    }
}
