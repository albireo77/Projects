package org.dmx.menu.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dish.class, name = "dish"),
        @JsonSubTypes.Type(value = DishSet.class, name = "set")
})
public interface MenuPosition {

    String getName();

    String getDescription();

    BigDecimal getPrice();
}
