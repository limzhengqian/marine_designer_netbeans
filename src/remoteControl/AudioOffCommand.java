package remoteControl;

import components.audio.AudioPlaylist;
import components.audio.StartAudioStrategy;
import components.logger.Logger;

public class AudioOffCommand implements Command {

    private static AudioOffCommand instance;
    private AudioPlaylist audioPlaylist;

    private AudioOffCommand(AudioPlaylist audioPlaylist) {
        this.audioPlaylist = audioPlaylist;
    }

    public static AudioOffCommand getInstance(AudioPlaylist audioPlaylist) {
        if (instance == null) {
            instance = new AudioOffCommand(audioPlaylist);
        }
        return instance;
    }

    @Override
    public void execute() {
        Logger logger = Logger.getInstance();
        try {
            audioPlaylist.getCurrentAudio().off();
            audioPlaylist.setCurrentAudioIndex(-1);
        } catch (Exception e) {
            logger.logError("Error in AudioOffCommand: ",e);
        }

    }

    @Override
    public void undo() {
        Logger logger = Logger.getInstance();
        try {
            audioPlaylist.getNextAudio().on();
        } catch (Exception e) {
            logger.logError("Error Undo in AudioOffCommand: ",e);
        }

    }
}