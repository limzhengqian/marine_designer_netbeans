package remoteControl;

import components.Light;
import components.logger.Logger;

public class LightOffCommand implements Command {
    Light light;

    public LightOffCommand(Light light){
        this.light = light;
    }

    @Override
    public void execute() {
        Logger logger = Logger.getInstance();
        try {
            light.off();
        } catch (Exception e) {
            logger.logError("Error in LightOffCommand: ",e);
        }
    }

    @Override
    public void undo() {
        Logger logger = Logger.getInstance();
        try {
            light.on(true);
        } catch (Exception e) {
            logger.logError("Error undo in LightOffCommand: ",e);
        }
    }
}
