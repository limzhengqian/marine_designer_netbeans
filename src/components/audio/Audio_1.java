package components.audio;

import frontend.MarineController;

public class Audio_1 implements Audio {

    private String audioFilePath;
    private String audioFileName;
    private String audioFileDescription;
    private MarineController controller;

    public Audio_1(MarineController controller) {
        audioFilePath = "resources\\audio\\Miraculous_Whisper.mp3";
        audioFileName = "Miraculous Whisper";
        audioFileDescription = "Youtube link https://youtu.be/ETozwSefU0c?si=0cn8b4-Z3jTltljw";
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