package components;

import components.decoration.*;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        // Create an ocean
        Ocean ocean = new Ocean();

        // Create decoration factories for different zones
        DecorationFactory sunlightZoneFactory = new SunlightZoneFactory();
        DecorationFactory twilightZoneFactory = new TwilightZoneFactory();
        DecorationFactory midnightZoneFactory = new MidnightZoneFactory();

        // Create an OceanFacade with the ocean and decoration factories
        OceanFacade oceanFacade = new OceanFacade(ocean, sunlightZoneFactory, twilightZoneFactory, midnightZoneFactory);

        // Create and add fishes to the menu
        oceanFacade.createFishInMenu("Nemo", "Sunlight", "nemo_image.png");
        oceanFacade.createFishInMenu("Dory", "Twilight", "dory_image.png");

        // Display the fish menu
        List<Decoration> fishMenuList = oceanFacade.getDecorationMenuList();
        System.out.println("\nFish Menu:");
        for (Decoration fish : fishMenuList) {
            System.out.println(fish.getName() + " - " + fish.getType());
        }

        // Add fishes from the menu to the ocean
        oceanFacade.addFishInOcean("Nemo", "Sunlight");
        oceanFacade.addFishInOcean("Dory", "Twilight");

        // Display decorations in the ocean
        List<Decoration> oceanDecorations = ocean.getDecorations();
        System.out.println("\nDecorations in the Ocean:");
        for (Decoration decoration : oceanDecorations) {
            System.out.println(decoration.getName() + " - " + decoration.getType());
        }

        // Remove a decoration from the ocean
        Decoration removedDecoration = oceanDecorations.get(0);
        oceanFacade.removeDecorationInOcean(removedDecoration);

        // Display decorations in the ocean after removal
        oceanDecorations = ocean.getDecorations();
        System.out.println("\nDecorations in the Ocean:");
        for (Decoration decoration : oceanDecorations) {
            System.out.println(decoration.getName() + " - " + decoration.getType());
        }

        // Display all fishes in the menu
        List<Decoration> menuList = oceanFacade.getDecorationMenuList();
        System.out.println("\nDecorations in Fish Menu:");
        for (Decoration fish : menuList) {
            System.out.println(fish.getName() + " - " + fish.getType());
        }
    }
}