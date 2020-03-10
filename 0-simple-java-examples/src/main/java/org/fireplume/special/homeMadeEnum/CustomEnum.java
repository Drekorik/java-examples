package org.fireplume.special.homeMadeEnum;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomEnum {
    private static final Map<String, CustomEnum> valuesMap = new LinkedHashMap<>();
    private static final List<CustomEnum> valuesList = new ArrayList<>();

    public static final CustomEnum ENUM1 = new CustomEnum("ENUM1");
    public static final CustomEnum ENUM2 = new CustomEnum("ENUM2");
    private final String name;

    private CustomEnum(String name) {
        this.name = name;
        valuesMap.put(name, this);
        valuesList.add(this);
    }

    public static CustomEnum valueOf(final String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        final CustomEnum customEnum = valuesMap.get(name);
        if (customEnum == null) {
            throw new IllegalArgumentException();
        }
        return customEnum;
    }

    public static CustomEnum[] values() {
        return valuesList.toArray(new CustomEnum[0]);
    }

    public int ordinal() {
        return valuesList.indexOf(this);
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomEnum that = (CustomEnum) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}