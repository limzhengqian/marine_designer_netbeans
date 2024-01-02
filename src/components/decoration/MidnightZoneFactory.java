package components.decoration;

public class MidnightZoneFactory implements DecorationFactory {

    private static final String DOLPHIN_IMAGE_PATH = "/image/Fish/MidnightZone/dolphin.png";
    private static final String FISH4_IMAGE_PATH = "/image/Fish/MidnightZone/fish4.png";
    private static final String COLOR_CLARITY = "Low";

    @Override
    public Decoration createFish(String name, String type) {
        String imagePath = name.endsWith("1") || name.endsWith("2") || name.endsWith("3") || name.endsWith("4")
                ? DOLPHIN_IMAGE_PATH
                : FISH4_IMAGE_PATH;
        Fish fish = new Fish(name, type, imagePath);
        fish.setColorClarity(COLOR_CLARITY);
        return fish;
    }
}
