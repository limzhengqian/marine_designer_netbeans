package components.decoration;

public class TwilightZoneFactory implements DecorationFactory {

    private static final String COLOR_CLARITY = "Moderate";
    private static final double MIN_Y_COORDINATE = 0;
    private static final double MAX_Y_COORDINATE = 100;

    @Override
    public Decoration createFish(String name, String type, String imagePath) {
        if (MIN_Y_COORDINATE > 0) {
            throw new IllegalArgumentException("Fish in the twilight zone must be placed above the specified y-coordinate.");
        }
        if (MAX_Y_COORDINATE <= 0) {
            throw new IllegalArgumentException("Fish in the twilight zone must be placed below the specified y-coordinate.");
        }

        Fish fish = new Fish(name, type, imagePath);
        fish.setColorClarity(COLOR_CLARITY);
        return fish;
    }
}
