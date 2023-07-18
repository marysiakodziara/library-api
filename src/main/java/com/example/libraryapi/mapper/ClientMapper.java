package com.example.libraryapi.mapper;

import com.example.libraryapi.dto.ClientDto;
import com.example.libraryapi.model.Client;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    @Mappings(
            {
                    @Mapping(target="firstName", source="firstName"),
                    @Mapping(target="lastName", source="lastName"),
                    @Mapping(target="emailAddress", source="emailAddress"),
                    @Mapping(target="phoneNumber", source="phoneNumber"),
                    @Mapping(target="address", source="address")
            }
    )
    public Client map(ClientDto clientDto);

    @InheritInverseConfiguration
    public ClientDto map(Client client);

    @Mappings(
            {
                    @Mapping(target="firstName", source="firstName"),
                    @Mapping(target="lastName", source="lastName"),
                    @Mapping(target="emailAddress", source="emailAddress"),
                    @Mapping(target="phoneNumber", source="phoneNumber"),
                    @Mapping(target="address", source="address")
            }
    )
    void updateClient(ClientDto clientDto, @MappingTarget Client client);
}
