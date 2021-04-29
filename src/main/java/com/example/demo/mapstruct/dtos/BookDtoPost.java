package com.example.demo.mapstruct.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class BookDtoPost {
    @JsonProperty("title")
    private String title;

    @JsonProperty("releaseDate")
    private Date releaseDate;

    @JsonProperty("authors")
    private Set<AuthorPostDto> authors;
}
