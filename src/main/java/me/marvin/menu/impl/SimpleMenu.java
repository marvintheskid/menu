package me.marvin.menu.impl;

import me.marvin.menu.Menu;
import me.marvin.menu.button.Button;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Function;

/**
 * A simple {@link Menu} implementation powered by functions.
 *
 * @author marvin
 *
 * @see Menu
 */
public class SimpleMenu extends Menu {
    private final Function<Player, Map<Integer, Button>> buttonFunction;
    private final Function<Player, String> titleFunction;
    private final Function<Player, Integer> sizeFunction;

    public SimpleMenu(Function<Player, Map<Integer, Button>> buttonFunction, Function<Player, String> titleFunction, Function<Player, Integer> sizeFunction) {
        this.buttonFunction = buttonFunction;
        this.titleFunction = titleFunction;
        this.sizeFunction = sizeFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public Map<Integer, Button> getButtons(@NotNull Player player) {
        return buttonFunction.apply(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public String getMenuTitle(@NotNull Player player) {
        return titleFunction.apply(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize(@NotNull Player player) {
        return sizeFunction.apply(player);
    }
}
