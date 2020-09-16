package org.fireplume.configuration;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.fireplume.dto.AnotherNestedDto;
import org.fireplume.dto.NestedDto;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OrikaMapperConfiguration {
    @Bean
    public MapperFacade mapperFacade(
            ObjectProvider<List<CustomMapper<?, ?>>> mappers,
            ObjectProvider<List<CustomConverter<?, ?>>> converters
    ) {
        final MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .mapNulls(true)
                .build();
        final ConverterFactory converterFactory = mapperFactory.getConverterFactory();

        mappers.ifAvailable(customMappers -> customMappers.forEach(mapperFactory::registerMapper));
        converters.ifAvailable(customConverters -> customConverters.forEach(converter ->
                converterFactory.registerConverter(converter.getClass().getSimpleName(), converter)));

        mapperFactory.classMap(NestedDto.class, AnotherNestedDto.class)
                .fieldAToB("nestedFirstField", "anotherNestedFirstField")
                .fieldAToB("nestedSecondField", "anotherNestedSecondField")
                .register();

// This was made as Custom Mapper
//        mapperFactory.classMap(FirstDto.class, SecondDto.class)
//                .fieldAToB("firstField", "firstField")
//                .fieldAToB("secondField", "secondField")
//                .fieldAToB("nestedDto", "anotherNestedDto");


        return mapperFactory.getMapperFacade();
    }
}
