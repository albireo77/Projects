package org.dmx.menu.validation;

import org.dmx.menu.domain.DishSet;
import org.dmx.menu.domain.Item;
import org.dmx.menu.domain.Menu;
import org.dmx.menu.domain.MenuPosition;
import org.dmx.menu.error.ValidationException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class MenuValidator implements ConstraintValidator<ValidMenu, Menu> {

    @Override
    public boolean isValid(Menu menu, ConstraintValidatorContext context) {

        for (MenuPosition position : menu.getPositions()) {
            if (position instanceof DishSet) {
                DishSet set = (DishSet)position;
                if (set.getGroups().isEmpty() || set.getGroups().size() > 3) {
                    throw new ValidationException("Set must have 1-3 groups");
                }
                for (List<Item> items : set.getGroups().values()) {
                    if (items == null || items.isEmpty()) {
                        throw new ValidationException("Set group must have at least 1 item");
                    }
                }
            }
        }
        return true;
    }
}
