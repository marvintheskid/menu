package me.marvin.menu.button;

import me.marvin.menu.Menu;
import me.marvin.menu.listener.ButtonInteractionListener;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * This object represents an element in a {@link Menu}
 *
 * @author marvin
 * @since 1.0
 *
 * @see ButtonInteractionListener
 * @see ButtonEvent
 */
public interface Button {
    /**
     * This method returns the {@link ItemStack} of this button.
     *
     * @return the item
     */
    @NotNull ItemStack getItem();

    /**
     * This method returns the button's {@link ButtonEvent}.
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
}