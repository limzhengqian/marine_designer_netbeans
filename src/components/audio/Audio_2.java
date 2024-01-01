package components.audio;

import frontend.MarineController;

public class Audio_2 implements Audio {

    private String audioFilePath;
    private String audioFileName;
    private String audioFileDescription;
    private MarineController controller;

    public Audio_2(MarineController controller) {
        audioFilePath = "resources\\audio\\Ebb_and_Flow.mp3";
        audioFileName = "Ebb and Flow";
        audioFileDescription = "Youtube link https://youtu.be/Qn3n_kW7f4g?si=6cdcV9ZIoUiMHYJf";
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