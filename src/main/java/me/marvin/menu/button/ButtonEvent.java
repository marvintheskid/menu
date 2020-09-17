package me.marvin.menu.button;

import me.marvin.menu.listener.ButtonInteractionListener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

/**
 * This interface handles all the interactions made by the player on a button.
 *
 * The interface itself extends {@link Consumer} for more extensibility.
 *
 * @author marvin
 * @since 1.0
 *
 * @see ButtonInteractionListener
 * @see Consumer
 * @see Button
 */
public interface ButtonEvent extends Consumer<InventoryClickEvent> {
    /**
     * A simple {@link ButtonEvent} which cancels every interaction given towards the desired button
     */
    ButtonEvent CANCELLING_EVENT = (evt) -> evt.setCancelled(true);

    /**
     * A no-operation {@link ButtonEvent}, which does nothing.
     */
    ButtonEvent NOOP_EVENT = (evt) -> {};
}
