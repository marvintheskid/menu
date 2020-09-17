package me.marvin.menu.listener;

import me.marvin.menu.Menu;
import me.marvin.menu.button.Button;
import me.marvin.menu.button.ButtonEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * This {@link Listener} must be registered. The API does not work
 * without this listener, because this handles the interaction with the buttons.
 *
 * @author marvin
 * @since 1.0
 *
 * @see Menu
 * @see ButtonEvent
 */
public class ButtonInteractionListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        // We check if the clicked inventory is null
        if (event.getClickedInventory() == null) return;
        Inventory inventory = event.getClickedInventory();

        // This should not happen, but just in case
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();

        // If the clicked inventory's holder is not a menu, we just return
        if (!(inventory.getHolder() instanceof Menu)) return;
        Menu menu = (Menu) event.getClickedInventory().getHolder();

        Button button = menu.getButtons(player).get(event.getSlot());
        if (button == null) return;

        ButtonEvent buttonEvent = button.getEvent();

        if (button.isStrict()) {
            if (button.getItem().isSimilar(inventory.getItem(event.getSlot()))) {
                buttonEvent.accept(event);
            }
        } else {
            buttonEvent.accept(event);
        }
    }
}
