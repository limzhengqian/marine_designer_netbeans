package remoteControl;

import components.logger.Logger;

public class RemoteControl {
    Command [] onCommands;
    Command [] offCommands;

    Command undoCommand;

    Logger logger;

    public RemoteControl(Logger logger){
        this.logger = logger;

        onCommands = new Command[10];
        offCommands = new Command[10];

        Command noCommand = new NoCommand();
        for(int i=0;i<10;i++){
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }

        undoCommand = noCommand;
    }

    public void setCommands(int slot, Command onCommand, Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot){
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
        logger.execute(onCommands[slot]);
    }

    public void offButtonWasPushed(int slot){
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
        logger.execute(offCommands[slot]);
    }
    public void undoButtonWasPushed(){
        undoCommand.undo();
        undoCommand = new NoCommand();
    }

    public boolean isUndoAvailable() {
        return !(undoCommand instanceof NoCommand);
    }
}

class NoCommand implements Command{

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
