package me.marvin.menu;

import com.google.common.base.Preconditions;
import me.marvin.menu.button.Button;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This utility helps filling the button set of a {@link Menu}.
 *
 * @author marvin
 *
 * @see Menu
 */
public class MenuMask {
    private final Map<Character, Function<Player, Button>> mappedCharacters = new HashMap<>();
    private String[] rows;

    /**
     * Describes the shape of this mask.
     *
     * @param shape the shape of the mask
     * @return this mask
     * @throws IndexOutOfBoundsException if the shape.length is larger than 6 or one of the row's length is larger than 9
     * @throws NullPointerException if one of the rows are null or the shape is null
     */
    public MenuMask shape(final String... shape) {
        Preconditions.checkNotNull(shape, "shape should not be null");
        Preconditions.checkPositionIndex(shape.length, 6, "shape.length cannot exceed 6, current length: " + shape.length);

        rows = new String[shape.length];

        for (int i = 0; i < shape.length; i++) {
            String row = shape[i];
            Preconditions.checkNotNull(row, "row cannot be null");
            Preconditions.checkPositionIndex(row.length(), 9, "row size cannot exceed 9");

            rows[i] = row;
        }

        return this;
    }

    /**
     * Maps the given character for a button.
     *
     * @param character the character
     * @param buttonFunction the function
     * @return this mask
     */
    public MenuMask character(char character, Function<Player, Button> buttonFunction) {
        mappedCharacters.put(character, buttonFunction);
        return this;
    }

    /**
     * Applies the mask for a set of buttons.
     *
     * @param player the player
     * @param buttons the set
     */
    public void apply(@NotNull Map<Integer, Button> buttons, @NotNull Player player) {
        int index = 0;
        for (String row : rows) {
            for (char c : row.toCharArray()) {
                Button button = Preconditions.checkNotNull(mappedCharacters.get(c), "mapped button was null").apply(player);
                buttons.put(index++, button);
            }
        }
    }

    /**
     * Creates a new set of buttons using the mask.
     *
     * @param player the player
     * @return the set of buttons
     */
    @NotNull public Map<Integer, Button> create(@NotNull Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        apply(buttons, player);
        return buttons;
    }
}
