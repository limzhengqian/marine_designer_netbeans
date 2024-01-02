package remoteControl;

import components.Light;
import components.logger.Logger;
import remoteControl.Command;

public class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light){
        this.light = light;
    }
    @Override
    public void execute() {
        Logger logger = Logger.getInstance();
        try {
            light.on();
        } catch (Exception e) {
            logger.logError("Error in LightOnCommand: ",e);
        }
    }

    @Override
    public void undo() {
        Logger logger = Logger.getInstance();
        try {
            light.off(true);
        } catch (Exception e) {
            logger.logError("Error undo in LightOnCommand: ",e);
        }
    }
}
