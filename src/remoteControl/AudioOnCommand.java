package remoteControl;

import components.audio.AudioPlaylist;
import components.audio.StartAudioStrategy;
import components.logger.Logger;

public class AudioOnCommand implements Command {

    private static AudioOnCommand instance;
    private AudioPlaylist audioPlaylist;

    private AudioOnCommand(AudioPlaylist audioPlaylist) {
        this.audioPlaylist = audioPlaylist;
    }

    public static AudioOnCommand getInstance(AudioPlaylist audioPlaylist) {
        //// singleton to prevent threading for media player thus avoiding mixed audio noise
        if (instance == null) {
            instance = new AudioOnCommand(audioPlaylist);
        }
        return instance;
    }

    @Override
    public void execute() {
        Logger logger = Logger.getInstance();
        try {
            audioPlaylist.getNextAudio().on();
        } catch (Exception e) {
            logger.logError("Error in AudioOnCommand: " ,e);
        }
    }

    @Override
    public void undo() {
        Logger logger = Logger.getInstance();
        try {
            audioPlaylist.getCurrentAudio().off();
        } catch (Exception e) {
            logger.logError("Error undo in AudioOnCommand: ",e);
        }
    }
}
