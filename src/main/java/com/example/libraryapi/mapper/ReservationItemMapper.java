package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.dto.ReservationItemDto;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.ReservationItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationItemMapper {

    ReservationItemMapper INSTANCE = Mappers.getMapper(ReservationItemMapper.class);
    default BookDto map(Book book) {
        return Mappers.getMapper(BookMapper.class).map(book);
    }

    default Book map(BookDto bookDto) {
        return Mappers.getMapper(BookMapper.class).map(bookDto);
    }

    @Mappings(
            {
                    @Mapping(target = "book", source = "book"),
                    @Mapping(target = "quantity", source = "quantity"),
            }
    )
    ReservationItemDto map(ReservationItem reservationItem);

    @InheritInverseConfiguration
    ReservationItem map(ReservationItemDto reservationItemDto);
}
