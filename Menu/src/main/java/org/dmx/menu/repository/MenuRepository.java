package org.dmx.menu.repository;

import org.dmx.menu.domain.Menu;

import java.util.List;

public interface MenuRepository {

    List<Menu> getAll();

    Menu get(int id);

    Menu create(Menu menu);

    Menu update(Menu menu);
}
