package me.marvin.menu.button;

import me.marvin.menu.listener.ButtonInteractionListener;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Handles all the interactions made by the player on a button.
 * The interface itself extends {@link Consumer} for more extensibility.
 *
 * @author marvin
 *
 * @see ButtonInteractionListener
 * @see Consumer
 * @see Button
 */
public interface ButtonEvent extends Consumer<InventoryClickEvent> {
    /**
     * A simple {@link ButtonEvent} which cancels every interaction given towards the desired button
     */
    ButtonEvent CANCELLING_EVENT = (evt) -> {
        evt.setCancelled(true);
        evt.setResult(Event.Result.DENY);
    };

    /**
     * A no-operation {@link ButtonEvent}, which does nothing.
     */
    ButtonEvent NOOP_EVENT = (evt) -> {};

    @Override
    @NotNull
    default ButtonEvent andThen(@NotNull Consumer<? super InventoryClickEvent> after) {
        Objects.requireNonNull(after);
        return (t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
