package org.dmx.menu.repository.impl;

import org.dmx.menu.domain.Menu;
import org.dmx.menu.repository.MenuRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMenuRepository implements MenuRepository {

    private final Map<Integer, Menu> repository = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger();

    @Override
    public List<Menu> getAll() {
        return new ArrayList<>(repository.values());
    }

    @Override
    public Menu get(int id) {
        return repository.get(id);
    }

    @Override
    public Menu create(Menu menu) {
        if (menu.isNew()) {
            menu.setId(idGenerator.getAndIncrement());
        }
        repository.put(menu.getId(), menu);
        return menu;
    }

    @Override
    public Menu update(Menu menu) {
        if (!repository.containsKey(menu.getId())) {
            return null;
        }
        repository.put(menu.getId(), menu);
        return menu;
    }
}
