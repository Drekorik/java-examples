package org.fireplume.mapper.mapstruct;

import org.fireplume.configuration.MapStructConfiguration;
import org.fireplume.dto.AnotherNestedDto;
import org.fireplume.dto.NestedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(config = MapStructConfiguration.class)
public interface MapStructNestedDtoToAnotherNestedDtoMapper {

    @Mappings({
            @Mapping(source = "nestedFirstField", target = "anotherNestedFirstField"),
            @Mapping(source = "nestedSecondField", target = "anotherNestedSecondField")
    })
    AnotherNestedDto map(NestedDto firstDto);
}
