package org.fireplume.mapper.mapstruct;

import org.fireplume.configuration.MapStructConfiguration;
import org.fireplume.dto.FirstDto;
import org.fireplume.dto.SecondDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = MapStructNestedDtoToAnotherNestedDtoMapper.class, config = MapStructConfiguration.class)
public interface MapStructFirstDtoToSecondDtoMapper {

    @Mappings({
            @Mapping(source = "firstField", target = "firstField"),
            @Mapping(source = "secondField", target = "secondField"),
            @Mapping(source = "nestedDto", target = "anotherNestedDto")
    })
    SecondDto map(FirstDto firstDto);
}
