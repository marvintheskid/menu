package me.marvin.menu.button;

import me.marvin.menu.listener.ButtonInteractionListener;
import me.marvin.menu.listener.ButtonInteractionData;
import org.bukkit.event.Event;
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
 *
 * @since 1.0
 */
@FunctionalInterface
public interface ButtonEvent {
    /**
     * A simple {@link ButtonEvent} which cancels every interaction made on the button.
     */
    ButtonEvent CANCELLING_EVENT = (evt) -> {
        evt.setCancelled(true);
        evt.setResult(Event.Result.DENY);
    };

    /**
     * A no-operation {@link ButtonEvent}, which does nothing.
     */
    ButtonEvent NOOP_EVENT = (evt) -> {};

    /**
     * Performs this operation on the given argument.
     *
     * @param t the input argument
     */
    void accept(ButtonInteractionData t);

    /**
     * Returns a composed {@code ButtonEvent} that performs, in sequence, this
     * operation followed by the {@code after} operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception,
     * the {@code after} operation will not be performed.
     *
     * @param after the operation to perform after this operation
     * @return a composed {@code ButtonEvent} that performs in sequence this
     * operation followed by the {@code after} operation
     * @throws NullPointerException if {@code after} is null
     */
    @NotNull
    default ButtonEvent andThen(@NotNull ButtonEvent after) {
        Objects.requireNonNull(after);
        return (t) -> {
            accept(t);
            after.accept(t);
        };
    }
}
