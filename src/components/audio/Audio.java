package components.audio;

public interface Audio {

    String getAudioFilePath();
    String getAudioFileName();
    String getAudioFileDescription();
    
    void on();
    void off();

}
