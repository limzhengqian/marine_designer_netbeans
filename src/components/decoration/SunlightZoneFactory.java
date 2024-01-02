package components.decoration;

public class SunlightZoneFactory implements DecorationFactory {

    private static final String BLOBFISH_IMAGE_PATH = "/image/Fish/SunlightZone/blobfish.png";
    private static final String FISH1_IMAGE_PATH = "/image/Fish/SunlightZone/fish1.png";
    private static final String COLOR_CLARITY = "High";

    @Override
    public Decoration createFish(String name, String type) {
        String imagePath = name.endsWith("1") || name.endsWith("2") || name.endsWith("3") || name.endsWith("4")
                ? BLOBFISH_IMAGE_PATH
                : FISH1_IMAGE_PATH;
        Fish fish = new Fish(name, type, imagePath);
        fish.setColorClarity(COLOR_CLARITY);
        return fish;
    }
}