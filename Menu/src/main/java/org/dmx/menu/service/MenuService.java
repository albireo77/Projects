package org.dmx.menu.service;

import org.dmx.menu.domain.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getAll();

    Menu get(int id);

    Menu create(Menu menu);

    Menu update(Menu menu);
}