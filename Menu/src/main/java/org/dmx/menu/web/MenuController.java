package org.dmx.menu.web;

import org.dmx.menu.domain.Menu;
import org.dmx.menu.service.MenuService;
import org.dmx.menu.validation.ValidMenu;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAll() {
        List<Menu> allMenus = menuService.getAll();
        return ResponseEntity.ok(allMenus);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Menu> get(@PathVariable int id) {
        Menu menu = menuService.get(id);
        if (menu == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(menu);
        }
    }

    @PostMapping
    public ResponseEntity<Menu> create(@RequestBody @ValidMenu Menu menu) {
        Menu newMenu = menuService.create(menu);
        return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Menu> update(@RequestBody @ValidMenu Menu menu) {
        Menu updatedMenu = menuService.update(menu);
        if (updatedMenu == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedMenu);
        }
    }
}
