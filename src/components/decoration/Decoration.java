package components.decoration;

public class Decoration {
    protected String name, type, imagePath, colorClarity;

    public Decoration(String name, String type, String imagePath) {
        this.name = name;
        this.type = type;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColorClarity() {
        return colorClarity;
    }

    public void setColorClarity(String colorClarity) {
        this.colorClarity = colorClarity;
    }
}
