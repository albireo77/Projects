package org.dmx.menu.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;

import java.math.BigDecimal;

@JsonTypeName("dish")
public class Dish implements MenuPosition {

    private final Item item;
    private BigDecimal price;

    public Dish() {
        this.item = new Item();
    }

    public Dish(Item item) {
        this.item = item;
    }

    @Override
    public String getName() {
        return item.getName();
    }

    public void setName(String name) {
        item.setName(name);
    }

    @Override
    public String getDescription() {
        return item.getDescription();
    }

    public void setDescription(String description) {
        item.setDescription(description);
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
