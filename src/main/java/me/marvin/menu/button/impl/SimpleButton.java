package me.marvin.menu.button.impl;

import me.marvin.menu.button.Button;
import me.marvin.menu.button.ButtonEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

/**
 * A simple {@link Button} implementation powered by functions.
 *
 * @author marvin
 *
 * @see Button
 */
public class SimpleButton implements Button {
    private final Player player;
    private final Function<Player, ItemStack> itemFunction;
    private final Function<Player, ButtonEvent> eventFunction;
    private final Function<Player, Boolean> strictFunction;

    public SimpleButton(Player player, Function<Player, ItemStack> itemFunction, Function<Player, ButtonEvent> eventFunction, Function<Player, Boolean> strictFunction) {
        this.player = player;
        this.itemFunction = itemFunction;
        this.eventFunction = eventFunction;
        this.strictFunction = strictFunction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ItemStack getItem() {
        return itemFunction.apply(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ButtonEvent getEvent() {
        return eventFunction.apply(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStrict() {
        return strictFunction.apply(player);
    }
}
