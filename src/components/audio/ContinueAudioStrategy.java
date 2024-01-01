package components.audio;

public class ContinueAudioStrategy implements NextAudioStrategy {

    @Override
    public Audio getNextAudio(AudioPlaylist audioPlaylist) {
        Audio audio;
        int playlistSize = audioPlaylist.getPlaylistSize();
        int currentAudioIndex = audioPlaylist.getCurrentAudioIndex();

        int nextAudioIndex = (currentAudioIndex + 1) % playlistSize;

        audioPlaylist.getCurrentAudio().off();
        audio = audioPlaylist.getAudio(nextAudioIndex);
        return audio;
    }
}