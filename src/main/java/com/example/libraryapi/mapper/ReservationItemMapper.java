package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.BookDto;
import com.example.libraryapi.dto.ExtendedReservationItemDto;
import com.example.libraryapi.dto.ReservationItemDto;
import com.example.libraryapi.model.Book;
import com.example.libraryapi.model.ReservationItem;
import java.time.LocalDate;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationItemMapper {

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
                    @Mapping(target = "returned", source = "returned")
            }
    )
    ReservationItemDto map(ReservationItem reservationItem);

    @InheritInverseConfiguration
    ReservationItem map(ReservationItemDto reservationItemDto);

    @Mappings(
            {
                    @Mapping(target = "book", source = "reservationItem.book"),
                    @Mapping(target = "quantity", source = "reservationItem.quantity"),
                    @Mapping(target = "returned", source = "reservationItem.returned"),
                    @Mapping(target = "reservationDate", source = "reservationDate"),
                    @Mapping(target = "endOfReservation", source = "endOfReservation"),
                    @Mapping(target = "clientEmail", source = "clientEmail")
            }
    )
    ExtendedReservationItemDto map(ReservationItem reservationItem, String clientEmail, LocalDate reservationDate, LocalDate endOfReservation);
}
