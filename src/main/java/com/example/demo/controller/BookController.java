package com.example.demo.controller;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.mapstruct.dtos.AuthorDto;
import com.example.demo.mapstruct.dtos.BookDto;
import com.example.demo.mapstruct.dtos.BookDtoPost;
import com.example.demo.mapstruct.mappers.MapStructMapper;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {
    private MapStructMapper mapstructMapper;

    private BookRepository bookRepository;

    private AuthorRepository authorRepository;

    @Autowired
    public BookController(
            MapStructMapper mapstructMapper,
            BookRepository bookRepository,
            AuthorRepository authorRepository) {
        this.mapstructMapper = mapstructMapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody BookDtoPost bookDtoPost) throws ParseException {
//    public ResponseEntity<Void> create(@Valid @RequestBody BookDto bookDto) throws ParseException {

//        Book bookEntity = new Book();
//        bookEntity.setTitle(bookDto.getTitle());
//        bookEntity.setReleaseDate(bookDto.getReleaseDate());
//
//        Book preSave = bookRepository.save(bookEntity);
//
//        List<Author> authorList = bookDto.getAuthors().stream()
//                .map(authorDto -> {
//                    Author author=new Author();
//                    author.setName(authorDto.getName());
//                    author.setSurname(authorDto.getSurname());
//                    author.setBirthDate(authorDto.getBirthDate());
//                    return author;
//                }).collect(Collectors.toList());
//
//
//        List<Author> authors = authorRepository.saveAll(authorList);
//
//        preSave.getAuthors().addAll(authors);
//
//        Book savedBook = bookRepository.save(preSave);
//-----------------------------------------------------------------------------------
//        Book bookEntity = new Book();
//        bookEntity.setTitle(bookDto.getTitle());
//        bookEntity.setReleaseDate(bookDto.getReleaseDate());
//
//        List<Author> authorList = bookDto.getAuthors().stream()
//                .map(authorDto -> {
//                    Author author=new Author();
//                    author.setName(authorDto.getName());
//                    author.setSurname(authorDto.getSurname());
//                    author.setBirthDate(authorDto.getBirthDate());
//                    return author;
//                }).collect(Collectors.toList());
//
//        bookEntity.getAuthors().addAll(authorList);
//
//        authorList.stream().forEach(author -> {
//            author.getBooks().add(bookEntity);
//        });
//
//        Book savedBook = bookRepository.save(bookEntity);
//------------------------------------------------------------------------------------
//        Book bookEntity = new Book();
//        bookEntity.setTitle(bookDto.getTitle());
//        bookEntity.setReleaseDate(bookDto.getReleaseDate());
//
//        List<Author> authorList = bookDto.getAuthors().stream()
//                .map(mapstructMapper::authorDtoToAuthor).collect(Collectors.toList());
//
//        bookEntity.getAuthors().addAll(authorList);
//
//        authorList.stream().forEach(author -> {
//            author.getBooks().add(bookEntity);
//        });

        Book savedBook = bookRepository.save(mapstructMapper.bookDtoPostToBook(bookDtoPost));


        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(
            @PathVariable(value = "id") int id
    ) {
        return new ResponseEntity<>(
                mapstructMapper.bookToBookDto(
                        bookRepository.findById(id).get()
                ),
                HttpStatus.OK
        );
    }
}
