package components;

import frontend.MarineController;

public class Light {
    MarineController controller;
    public Light(MarineController controller){
        this.controller = controller;
    }

    public void on(){
        controller.setDefaultBrightness(0);
    }
    public void off(){
        controller.setDefaultBrightness(-0.5);
    }

    public void on(boolean undo){
        controller.setDefaultBrightness(0);
//        controller.updateUndoAvailablity(true);
    }

    public void off(boolean undo){
        controller.setDefaultBrightness(-0.5);
//        controller.updateUndoAvailablity(true);
    }
}
