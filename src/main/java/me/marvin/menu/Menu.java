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
 * {@linkplain Bukkit#createInventory(InventoryHolder, int)} method.
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
    /**
     * The inventory held by this object.
     */
    private Inventory inventory;

    /**
     * This method returns the buttons used by this menu.
     *
     * @param player the player
     * @return the buttons used by this menu
     */
    @NotNull public abstract Map<Integer, Button> getButtons(Player player);

    /**
     * This method returns the title of this menu.
     *
     * @param player the player
     * @return the title of this menu
     */
    @NotNull public abstract String getMenuTitle(Player player);

    /**
     * With this method you can define the inventory's size.
     *
     * @param player the player
     * @return the desired inventory size
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    public abstract int getSize(Player player);

    /**
     * This method opens this menu for the given player.
     *
     * @param player the player
     * @throws IndexOutOfBoundsException if one of the button's position is out of range (should be between 0 and 53)
     */
    public final void openMenu(Player player) {
        String title = getMenuTitle(player);
        title = title.substring(0, Math.min(title.length(), 32));
        title = ChatColor.translateAlternateColorCodes(ChatColor.COLOR_CHAR, title);

        int size = getSize(player);
        Preconditions.checkArgument(size % 9 != 0, "menu size");

        inventory = Bukkit.createInventory(this, size, title);

        for (Map.Entry<Integer, Button> entry : getButtons(player).entrySet()) {
            Preconditions.checkPositionIndex(entry.getKey(), 53, "button position");
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        }

        player.openInventory(inventory);
    }

    /**
     * This method returns this object's inventory.
     *
     * @return the inventory held by this menu
     */
    @Override
    @NotNull
    public Inventory getInventory() {
        return inventory;
    }
}
