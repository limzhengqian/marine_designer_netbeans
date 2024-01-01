package components.audio;

import frontend.MarineController;

public class Audio_3 implements Audio {

    private String audioFilePath;
    private String audioFileName;
    private String audioFileDescription;
    private MarineController controller;

    public Audio_3(MarineController controller) {
        audioFilePath = "resources\\audio\\Following_the_Torrent.mp3";
        audioFileName = "Following the Torrent";
        audioFileDescription = "Youtube link https://youtu.be/6eJuMFs7J00?si=AwWdxEBW62wq44F4";
        this.controller = controller;
    }

    @Override
    public String getAudioFilePath() {
        return audioFilePath;
    }
    @Override
    public String getAudioFileName() {
        return audioFileName;
    }
    @Override
    public String getAudioFileDescription() {
        return audioFileDescription;
    }

    @Override
    public void on() {
        controller.onMediaPlayer(audioFilePath);
    }

    @Override
    public void off() {
        controller.offMediaPlayer();
    }

}