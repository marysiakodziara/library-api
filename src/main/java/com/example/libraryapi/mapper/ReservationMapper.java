package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.ExtendedReservationDto;
import com.example.libraryapi.dto.ReservationDto;
import com.example.libraryapi.dto.ReservationItemDto;
import com.example.libraryapi.model.Client;
import com.example.libraryapi.model.Reservation;
import com.example.libraryapi.model.ReservationItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReservationMapper {

    default ReservationItem map(ReservationItemDto reservationItemDto) {
        return Mappers.getMapper(ReservationItemMapper.class).map(reservationItemDto);
    }

    default ReservationItemDto map(ReservationItem reservationItem) {
        return Mappers.getMapper(ReservationItemMapper.class).map(reservationItem);
    }

    @Mappings(
            {
                    @Mapping(target = "id",  source = "reservationDto.id"),
                    @Mapping(target = "reservationItems", source = "reservationDto.reservationItems"),
                    @Mapping(target = "borrowed", source = "reservationDto.borrowed"),
                    @Mapping(target = "canceled", source = "reservationDto.canceled"),
                    @Mapping(target = "endOfReservation", source = "reservationDto.endOfReservation"),
                    @Mapping(target = "reservationDate", source = "reservationDto.reservationDate"),
                    @Mapping(target = "client", source = "client")
            }
    )
    Reservation map(ReservationDto reservationDto, Client client);

    @Mappings(
            {
                    @Mapping(target = "id", source = "id"),
                    @Mapping(target = "reservationItems", source = "reservationItems"),
                    @Mapping(target = "borrowed", source = "borrowed"),
                    @Mapping(target = "canceled", source = "canceled"),
                    @Mapping(target = "endOfReservation", source = "endOfReservation"),
                    @Mapping(target = "reservationDate", source = "reservationDate"),
            }
    )
    ReservationDto map(Reservation reservation);

    @Mappings(
            {
                    @Mapping(target = "id", source = "reservation.id"),
                    @Mapping(target = "reservationItems", source = "reservation.reservationItems"),
                    @Mapping(target = "borrowed", source = "reservation.borrowed"),
                    @Mapping(target = "canceled", source = "reservation.canceled"),
                    @Mapping(target = "endOfReservation", source = "reservation.endOfReservation"),
                    @Mapping(target = "reservationDate", source = "reservation.reservationDate"),
                    @Mapping(target = "clientEmail", source = "clientEmail")
            }
    )
    ExtendedReservationDto map(Reservation reservation, String clientEmail);
}
