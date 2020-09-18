# menu
A simplistic Bukkit GUI / Menu API using the InventoryHolder interface.

## Usage
### Creating your first menu
You need to create an object which extends **Menu** first:
```java
public class DummyMenu extends Menu {
    @Override
    @NotNull 
    public Map<Integer, Button> getButtons(@NotNull Player player) {
        ...
    }

    @Override
    @NotNull
    public String getMenuTitle(@NotNull Player player) {
        ...
    }

    @Override
    public int getSize(@NotNull Player player) {
        ...
    }
}
```
You should create a new instance of the desired menu every time, because most of the time we only want 1 viewer / menu. After creating the menu, calling the Menu#openMenu(Player) method will show the menu for the given player.
```java
    ...
    Player player = ...;
    new DummyMenu().openMenu(player);
    ...
```
