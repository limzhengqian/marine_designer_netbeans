package components.audio;

public class StartAudioStrategy implements NextAudioStrategy {

    @Override
    public Audio getNextAudio(AudioPlaylist audioPlaylist) {
        Audio audio = audioPlaylist.getAudio(0);
        audioPlaylist.setStrategy(new ContinueAudioStrategy());
        return audio;
    }
}