package remoteControl;

import components.audio.AudioPlaylist;
import components.audio.StartAudioStrategy;

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
        audioPlaylist.getCurrentAudio().off();
        audioPlaylist.setCurrentAudioIndex(-1);
    }

    @Override
    public void undo() {
        audioPlaylist.getNextAudio().on();
    }
}