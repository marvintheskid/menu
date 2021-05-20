# menu
A simplistic Bukkit GUI / Menu API using the InventoryHolder interface.
TODO: rewrite / cleanup the readme

## Usage
### Basics
Registering the [ButtonInteractionListener](src/main/java/me/marvin/menu/listener/ButtonInteractionListener.java) is required, otherwise the API will malfunction.
```java
Bukkit.getPluginManager().registerEvents(new ButtonInteractionListener(), instance);
```
### Creating your first menu
You need to create an object which extends [Menu](src/main/java/me/marvin/menu/Menu.java) first:
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
### Creating buttons
Creating a [Button](src/main/java/me/marvin/menu/button/Button.java) is simple:
```java
public class DummyButton implements Button {
    @Override
    @NotNull
    public ItemStack getItem() {
        ...
    }

    @Override
    @NotNull
    public ButtonEvent getEvent() {
        ...
    }

    @Override
    public boolean isStrict() {
        ...
    }
```
The Button#getEvent() and the Button#isStrict() method have a default value, therefore you can use the Button interface as a functional interface:
```java
Button btn = () -> ...;
```
