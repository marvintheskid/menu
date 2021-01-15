package me.marvin.menu.button;

import me.marvin.menu.Menu;
import me.marvin.menu.listener.ButtonInteractionListener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an element in a {@link Menu}.
 *
 * @author marvin
 *
 * @see ButtonInteractionListener
 * @see ButtonEvent
 *
 * @since 1.0
 */
public interface Button {
    /**
     *  Returns the {@link ItemStack} of this button.
     *
     * @return the item
     */
    @NotNull ItemStack getItem();

    /**
     * Returns the button's {@link ButtonEvent}.
     * By default, this method returns a {@linkplain ButtonEvent#NOOP_EVENT}.
     *
     * @return the event assigned to this button
     */
    @NotNull default ButtonEvent getEvent() {
        return ButtonEvent.NOOP_EVENT;
    }

    /**
     * This boolean marks the button as a strict button.
     * By default, every button is strict. If the button is
     * not strict, only the clicked button's position should
     * match, else the button's item should match too.
     *
     * @return true if the button is strict
     */
    default boolean isStrict() {
        return true;
    }

    /**
     * Creates a button with the given {@link ItemStack}
     * @param item the item
     * @return a new element with the given item
     * @since 1.1
     */
    static Button of(@NotNull ItemStack item) {
        return of(item, ButtonEvent.NOOP_EVENT);
    }

    /**
     * Creates a button with the given {@link ItemStack} and {@link ButtonEvent}
     *
     * @param item the item
     * @param event the event
     * @return a new element with the given item and event
     * @since 1.1
     */
    static Button of(@NotNull ItemStack item, @NotNull ButtonEvent event) {
        return of(item, event, true);
    }

    /**
     * Creates a button with the given {@link ItemStack} and behavior.
     *
     * @param item the item
     * @param strict the behavior
     * @return a new element with the given item and behavior
     * @since 1.1
     */
    static Button of(@NotNull ItemStack item, boolean strict) {
        return of(item, ButtonEvent.NOOP_EVENT, strict);
    }

    /**
     * Creates a button with all the required objects.
     *
     * @param item the item
     * @param event the event
     * @return a new element with all the required objects
     * @since 1.1
     */
    static Button of(@NotNull ItemStack item, @NotNull ButtonEvent event, boolean strict) {
        return new Button() {
            @Override @NotNull public ItemStack getItem() { return item; }
            @Override @NotNull public ButtonEvent getEvent() { return event; }
            @Override public boolean isStrict() { return strict; }
        };
    }
}