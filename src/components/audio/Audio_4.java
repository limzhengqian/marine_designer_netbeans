package components.audio;

import frontend.MarineController;

public class Audio_4 implements Audio {

    private String audioFilePath;
    private String audioFileName;
    private String audioFileDescription;
    private MarineController controller;

    public Audio_4(MarineController controller) {
        audioFilePath = "resources\\audio\\deep_ocean_bgm.mp3";
        audioFileName = "Deep Ocean bgm";
        audioFileDescription = "Unknown source";
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