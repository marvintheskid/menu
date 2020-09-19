package me.marvin.menu;

import com.google.common.base.Preconditions;
import me.marvin.menu.button.Button;
import me.marvin.menu.listener.ButtonInteractionListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * An abstract menu class which holds an inventory created using the
 * {@link Bukkit#createInventory(InventoryHolder, int)} method.
 * Using this method we can check if the clicked inventory's holder
 * is a {@link Menu}.
 *
 * @author marvin
 * @since 1.0
 *
 * @see ButtonInteractionListener
 * @see InventoryHolder
 * @see Button
 */
public abstract class Menu implements InventoryHolder {
    private static final char ALTERNATE_COLOR_CHAR = '&';

    /**
     * The inventory held by this object.
     */
    private Inventory inventory;

    /**
     * Returns the buttons used by this {@linkplain Menu}.
     *
     * @param player the player
     * @return the buttons used by this menu
     */
    @NotNull public abstract Map<Integer, Button> getButtons(@NotNull Player player);

    /**
     * Returns the title of this {@linkplain Menu}.
     *
     * @param player the player
     * @return the title of this menu
     */
    @NotNull public abstract String getMenuTitle(@NotNull Player player);

    /**
     * Returns the desired inventory size.
     *
     * @param player the player
     * @return the desired inventory size
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    public abstract int getSize(@NotNull Player player);

    /**
     * This method opens this {@linkplain Menu} for the given player.
     *
     * @param player the player
     * @throws IllegalArgumentException if the menu's size is not acceptable
     * @throws IndexOutOfBoundsException if one of the button's position is out of range (should be between 0 and 53)
     */
    public final void openMenu(@NotNull Player player) {
        String title = getMenuTitle(player);
        title = title.substring(0, Math.min(title.length(), 31));
        title = ChatColor.translateAlternateColorCodes(ALTERNATE_COLOR_CHAR, title);

        int size = getSize(player);
        Preconditions.checkArgument(size % 9 == 0, "menu size must be a multiple of 9");
        Preconditions.checkArgument(size < 54, "menu size must be smaller than 54");
        Preconditions.checkArgument(size > 9, "menu size must be larger than 9");

        inventory = Bukkit.createInventory(this, size, title);
        refreshMenu(player);

        player.openInventory(inventory);
    }

    /**
     * This method (re)initiates the the buttons of this {@linkplain Menu}.
     *
     * @param player the player
     * @throws NullPointerException if the inventory was not opened before
     */
    public final void refreshMenu(@NotNull Player player) {
        Preconditions.checkNotNull(inventory, "refresh");

        inventory.clear();
        for (Map.Entry<Integer, Button> entry : getButtons(player).entrySet()) {
            Preconditions.checkPositionIndex(entry.getKey(), inventory.getSize() - 1, "button position");
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        }
    }

    /**
     * Returns the inventory held by this {@linkplain Menu}.
     *
     * @return the inventory held by this menu
     */
    @Override
    @NotNull
    public Inventory getInventory() {
        return inventory;
    }
}
