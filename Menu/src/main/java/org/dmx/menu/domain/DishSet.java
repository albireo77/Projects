package org.dmx.menu.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonTypeName("set")
public class DishSet implements MenuPosition {

    private String name;
    private String description;
    private BigDecimal price;

    private final Map<String, List<Item>> groups = new HashMap<>();

    public Map<String, List<Item>> getGroups() {
        return groups;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}
