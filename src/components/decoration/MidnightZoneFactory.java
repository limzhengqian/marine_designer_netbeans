package components.decoration;

public class MidnightZoneFactory implements DecorationFactory {

    private static final String COLOR_CLARITY = "Low";
    private static final double MIN_Y_COORDINATE = 100;

    @Override
    public Decoration createFish(String name, String type, String imagePath) {
        if (MIN_Y_COORDINATE <= 0) {
            throw new IllegalArgumentException("Fish in the midnight zone must be placed above the specified y-coordinate.");
        }

        Fish fish = new Fish(name, type, imagePath);
        fish.setColorClarity(COLOR_CLARITY);

        return fish;
    }
}
