package org.dmx.menu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private Integer id;
    private final List<MenuPosition> positions = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MenuPosition> getPositions() {
        return positions;
    }

    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

}
