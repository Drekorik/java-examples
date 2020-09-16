package org.fireplume.mapper.orika;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import org.fireplume.dto.AnotherNestedDto;
import org.fireplume.dto.FirstDto;
import org.fireplume.dto.SecondDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class OrikaFirstDtoToSecondDtoMapper extends CustomMapper<FirstDto, SecondDto> {

    @Lazy
    @Autowired
    private MapperFacade mapperFacade;

    @Override
    public void mapAtoB(FirstDto firstDto, SecondDto secondDto, MappingContext context) {
        secondDto.setFirstField(firstDto.getFirstField());
        secondDto.setSecondField(firstDto.getSecondField());
        secondDto.setAnotherNestedDto(mapperFacade.map(firstDto.getNestedDto(), AnotherNestedDto.class));
    }
}
