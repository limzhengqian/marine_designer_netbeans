package components.decoration;

public class SunlightZoneFactory implements DecorationFactory {

    private static final String COLOR_CLARITY = "High";
    private static final double MAX_Y_COORDINATE = 0;

    @Override
    public Decoration createFish(String name, String type, String imagePath) {
        if (MAX_Y_COORDINATE > 0) {
            throw new IllegalArgumentException("Fish in the sunlight zone cannot be placed below the specified y-coordinate.");
        }

        Fish fish = new Fish(name, type, imagePath);
        fish.setColorClarity(COLOR_CLARITY);
        return fish;
    }
}
