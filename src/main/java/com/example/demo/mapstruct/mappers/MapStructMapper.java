package com.example.demo.mapstruct.mappers;

import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.mapstruct.dtos.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
    BookSlimDto bookToBookSlimDto(Book book);

    BookDto bookToBookDto(Book book);

    Book bookDtoToBook(BookDto bookDto);

    AuthorDto authorToAuthorDto(Author author);

    Author authorDtoToAuthor(AuthorDto authorDto);

    AuthorAllDto authorToAuthorAllDto(Author author);

    List<AuthorAllDto> authorsToAuthorAllDtos(List<Author> authors);

    UserGetDto userToUserGetDto(User user);

    User userPostDtoToUser(UserPostDto userPostDto);

    Book bookDtoPostToBook(BookDtoPost bookDtoPost);

    Author authorPostDtoToAuthor(AuthorPostDto authorPostDto);
}
