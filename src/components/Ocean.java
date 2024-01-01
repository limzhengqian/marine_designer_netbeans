package components;

import components.decoration.Decoration;

import java.util.ArrayList;
import java.util.List;

public class Ocean {
    private List<Decoration> decorations;

    public Ocean() {
        this.decorations = new ArrayList<>();
    }

    public void addDecoration(Decoration decoration) {
        decorations.add(decoration);
    }

    public void removeDecoration(Decoration decoration) {
        decorations.remove(decoration);
    }

    public List<Decoration> getDecorations() {
        return decorations;
    }
}