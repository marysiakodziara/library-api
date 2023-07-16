package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mappings(
            {
                    @Mapping(target = "isbn", source = "isbn"),
                    @Mapping(target = "title", source = "title"),
                    @Mapping(target = "author", source = "author"),
                    @Mapping(target = "numberOfBooks",  source = "numberOfBooks"),
                    @Mapping(target = "categories", source = "categories")
            }
    )
    Book map(BookDto bookDto);
    BookDto map(Book book);
}
