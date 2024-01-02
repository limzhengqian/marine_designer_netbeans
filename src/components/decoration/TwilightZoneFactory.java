package components.decoration;

public class TwilightZoneFactory implements DecorationFactory {

    private static final String ANGLERFISH_IMAGE_PATH = "/image/Fish/TwilightZone/anglerfish.png";
    private static final String FISH5_IMAGE_PATH = "/image/Fish/TwilightZone/fish5.png";
    private static final String COLOR_CLARITY = "High";

    @Override
    public Decoration createFish(String name, String type) {
        String imagePath = name.endsWith("1") || name.endsWith("2") || name.endsWith("3") || name.endsWith("4")
                ? ANGLERFISH_IMAGE_PATH
                : FISH5_IMAGE_PATH;
        Fish fish = new Fish(name, type, imagePath);
        fish.setColorClarity(COLOR_CLARITY);
        return fish;
    }
}
