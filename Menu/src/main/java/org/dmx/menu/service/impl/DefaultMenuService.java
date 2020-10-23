package org.dmx.menu.service.impl;

import org.dmx.menu.domain.Menu;
import org.dmx.menu.repository.MenuRepository;
import org.dmx.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultMenuService implements MenuService {

    private final MenuRepository menuRepository;

    public DefaultMenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.getAll();
    }

    @Override
    public Menu get(int id) {
        return menuRepository.get(id);
    }

    @Override
    public Menu create(Menu menu) {
        return menuRepository.create(menu);
    }

    @Override
    public Menu update(Menu menu) {
        return menuRepository.update(menu);
    }
}
