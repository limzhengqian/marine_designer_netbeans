package components;

import components.decoration.Decoration;
import components.decoration.DecorationFactory;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class OceanFacade {
    private Ocean ocean;
    private DecorationFactory sunlightZoneFactory;
    private DecorationFactory twilightZoneFactory;
    private DecorationFactory midnightZoneFactory;
    private List<Decoration> decorationMenuList;

    public OceanFacade(Ocean ocean, DecorationFactory sunlightZoneFactory, DecorationFactory twilightZoneFactory, DecorationFactory midnightZoneFactory) {
        this.ocean = ocean;
        this.sunlightZoneFactory = sunlightZoneFactory;
        this.twilightZoneFactory = twilightZoneFactory;
        this.midnightZoneFactory = midnightZoneFactory;
        this.decorationMenuList = new ArrayList<>();
    }

    public void addFishInOcean(String menuFishName, String oceanZone) {
        Decoration decoration = findFishInMenu(menuFishName, oceanZone);

        if (decoration != null) {
            ocean.addDecoration(decoration);
            decorationMenuList.remove(decoration);
            System.out.println("Added In Ocean: " + decoration.getName() + " from the menu, added to " + oceanZone + " Zone.");
        } else {
            System.out.println("Fish not found in the menu.");
        }
    }

    public void removeDecorationInOcean(Decoration decoration) {
        ocean.removeDecoration(decoration);
        decorationMenuList.add(decoration);
        System.out.println("Removed In Ocean: " + decoration.getName() + " from the " + decoration.getType() + " Zone, added back to the menu.");
    }

    public List<Decoration> getDecorationMenuList() {
        return decorationMenuList;
    }

    public String getFishImagePath(String fishName, String type) {
        Decoration fish = findFishInMenu(fishName, type);
        return (fish != null) ? fish.getImagePath() : null;
    }

    public  Decoration findFishInMenu(String menuFishName, String type) {
        for (Decoration menuFish : decorationMenuList) {
            if (menuFish.getName().equalsIgnoreCase(menuFishName) && menuFish.getType().equalsIgnoreCase(type)) {
                return menuFish;
            }
        }
        return null;
    }

    public boolean createFishInMenu(String name, String type, String fishImagePath) {
        DecorationFactory factory = getDecorationFactory(type);
        Decoration fish = factory.createFish(name, type, fishImagePath);

        if (fish != null) {
            decorationMenuList.add(fish);
            System.out.println("Added To Menu: " + fish.getName() + " decoration");
            return true;
        } else {
            return false;
        }
    }

    public DecorationFactory getDecorationFactory(String oceanZone) {
        switch (oceanZone.toLowerCase()) {
            case "sunlight":
                return sunlightZoneFactory;
            case "twilight":
                return twilightZoneFactory;
            case "midnight":
                return midnightZoneFactory;
            default:
                throw new IllegalArgumentException("Invalid ocean zone: " + oceanZone);
        }
    }
}
