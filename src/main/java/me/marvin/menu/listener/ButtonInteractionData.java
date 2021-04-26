package me.marvin.menu.listener;

import me.marvin.menu.Menu;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * A simple wrapper for Bukkit's {@link InventoryClickEvent}, what contains
 * the reference of the clicked {@link Menu}.
 */
public class ButtonInteractionData implements Cancellable {
    @NotNull private final InventoryClickEvent event;
    @NotNull private final Menu menu;

    protected ButtonInteractionData(@NotNull InventoryClickEvent event, @NotNull Menu menu) {
        this.event = event;
        this.menu = menu;
    }

    /**
     * Returns the currently opened menu.
     *
     * @return The menu
     */
    @NotNull
    public Menu getMenu() {
        return menu;
    }

    /**
     * Gets the inventory that was clicked, or null if outside of window
     *
     * @return The clicked inventory
     */
    public Inventory getClickedInventory() {
        return event.getClickedInventory();
    }

    /**
     * Gets the type of slot that was clicked.
     *
     * @return the slot type
     */
    public InventoryType.SlotType getSlotType() {
        return event.getSlotType();
    }

    /**
     * Gets the current ItemStack on the cursor.
     *
     * @return the cursor ItemStack
     */
    public ItemStack getCursor() {
        return event.getCursor();
    }

    /**
     * Gets the ItemStack currently in the clicked slot.
     *
     * @return the item in the clicked
     */
    public ItemStack getCurrentItem() {
        return event.getCurrentItem();
    }

    /**
     * Gets whether or not the ClickType for the event represents a right
     * click.
     *
     * @return true if the ClickType uses the right mouse button.
     * @see ClickType#isRightClick()
     */
    public boolean isRightClick() {
        return event.isRightClick();
    }

    /**
     * Gets whether or not the ClickType for the event represents a left
     * click.
     *
     * @return true if the ClickType uses the left mouse button.
     * @see ClickType#isLeftClick()
     */
    public boolean isLeftClick() {
        return event.isLeftClick();
    }

    /**
     * Gets whether the ClickType for the event indicates that the key was
     * pressed down when the click was made.
     *
     * @return true if the ClickType uses Shift or Ctrl.
     * @see ClickType#isShiftClick()
     */
    public boolean isShiftClick() {
        return event.isRightClick();
    }

    /**
     * Sets the item on the cursor.
     *
     * @param stack the new cursor item
     * @deprecated This changes the ItemStack in their hand before any
     * calculations are applied to the Inventory, which has a tendency to
     * create inconsistencies between the Player and the server, and to
     * make unexpected changes in the behavior of the clicked Inventory.
     */
    @Deprecated
    public void setCursor(ItemStack stack) {
        event.setCursor(stack);
    }

    /**
     * Sets the ItemStack currently in the clicked slot.
     *
     * @param stack the item to be placed in the current slot
     */
    public void setCurrentItem(ItemStack stack) {
        event.setCurrentItem(stack);
    }

    /**
     * The slot number that was clicked, ready for passing to
     * {@link Inventory#getItem(int)}. Note that there may be two slots with
     * the same slot number, since a view links two different inventories.
     *
     * @return The slot number.
     */
    public int getSlot() {
        return event.getSlot();
    }

    /**
     * The raw slot number clicked, ready for passing to {@link InventoryView
     * #getItem(int)} This slot number is unique for the view.
     *
     * @return the slot number
     */
    public int getRawSlot() {
        return event.getRawSlot();
    }

    /**
     * If the ClickType is NUMBER_KEY, this method will return the index of
     * the pressed key (0-8).
     *
     * @return the number on the key minus 1 (range 0-8); or -1 if not
     * a NUMBER_KEY action
     */
    public int getHotbarButton() {
        return event.getHotbarButton();
    }

    /**
     * Gets the InventoryAction that triggered the event.
     * <p>
     * This action cannot be changed, and represents what the normal outcome
     * of the event will be. To change the behavior of this
     * InventoryClickEvent, changes must be manually applied.
     *
     * @return the InventoryAction that triggered this event.
     */
    public InventoryAction getAction() {
        return event.getAction();
    }

    /**
     * Gets the ClickType for the event.
     * <p>
     * This is insulated against changes to the inventory by other plugins.
     *
     * @return the type of inventory click
     */
    public ClickType getClick() {
        return event.getClick();
    }

    /**
     * Sets the result of the event. This will change whether or not this
     * event is considered cancelled.
     *
     * @see #isCancelled()
     * @param newResult the new {@link Event.Result} for this event
     */
    public void setResult(Event.Result newResult) {
        event.setResult(newResult);
    }

    /**
     * Gets the {@link Event.Result} of the event. The Result describes the
     * behavior that will be applied to the inventory in relation to this
     * event.
     *
     * @return the Result of this event.
     */
    public Event.Result getResult() {
        return event.getResult();
    }

    /**
     * Gets the cancellation state of the event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    public boolean isCancelled() {
        return event.isCancelled();
    }

    /**
     * Sets the cancellation state of the event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    public void setCancelled(boolean cancel) {
        event.setCancelled(cancel);
    }
}
