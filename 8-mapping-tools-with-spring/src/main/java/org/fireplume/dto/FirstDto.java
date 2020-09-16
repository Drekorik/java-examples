package org.fireplume.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FirstDto {
    private String firstField;
    private Integer secondField;

    private NestedDto nestedDto;
}
