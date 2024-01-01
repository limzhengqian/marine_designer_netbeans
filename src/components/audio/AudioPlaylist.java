package components.audio;

import java.util.ArrayList;

import frontend.MarineController;

public class AudioPlaylist {

    private MarineController controller;
    private ArrayList<Audio> playlist;
    private int currentAudioIndex;
    private NextAudioStrategy strategy;

    public AudioPlaylist(MarineController controller) {
        this.controller = controller;
        initializePlaylist();
    }

    private void initializePlaylist() {
        playlist = new ArrayList<>();
        playlist.add(new Audio_1(controller));
        playlist.add(new Audio_2(controller));
        playlist.add(new Audio_3(controller));
        playlist.add(new Audio_4(controller));
        currentAudioIndex = -1;
        strategy = new StartAudioStrategy();
    }

    public int getPlaylistSize() {
        return playlist.size();
    }

    public int getCurrentAudioIndex() {
        return currentAudioIndex;
    }

    public Audio getCurrentAudio() {
        if (currentAudioIndex >= 0 && currentAudioIndex < playlist.size()) {
            return playlist.get(currentAudioIndex);
        } else {
            // Handle the case where the index is out of bounds (e.g., set it to 0)
            currentAudioIndex = 0;
            return playlist.get(0);
        }
    }

    public Audio getAudio(int audioIndex) {
        if (audioIndex >= 0 && audioIndex < playlist.size()) {
            currentAudioIndex = audioIndex;
            return playlist.get(audioIndex);
        } else {
            currentAudioIndex = 0;
            return playlist.get(0);
        }
    }

    public NextAudioStrategy getStrategy() {
        return strategy;
    }

    public Audio getNextAudio() {
        return strategy.getNextAudio(this);
    }

    public void setCurrentAudioIndex(int audioIndex) {
        currentAudioIndex = audioIndex;
    }

    public void setStrategy(NextAudioStrategy strategy) {
        this.strategy = strategy;
    }
}