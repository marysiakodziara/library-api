package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.dto.PostBookDto;
import com.example.libraryapi.model.Book;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {


    Book map(BookDto bookDto);
    BookDto map(Book book);

    @Mappings(
            {
                    @Mapping(source = "isbn", target = "isbn"),
                    @Mapping(source = "title", target = "title"),
                    @Mapping(source = "author", target = "author"),
                    @Mapping(source = "numberOfBooks", target = "numberOfBooks"),
                    @Mapping(source = "categories", target = "categories")
            }
    )
    Book map(PostBookDto postBookDto);
}
