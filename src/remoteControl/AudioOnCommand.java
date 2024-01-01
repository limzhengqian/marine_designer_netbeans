package remoteControl;

import components.audio.AudioPlaylist;
import components.audio.StartAudioStrategy;

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
        audioPlaylist.getNextAudio().on();
    }

    @Override
    public void undo() {
        audioPlaylist.getCurrentAudio().off();
    }
}