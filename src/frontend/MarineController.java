// MarineController.java
package frontend;

import components.audio.*;
import components.Light;
import components.Ocean;
import components.OceanFacade;
import components.decoration.Decoration;
import components.decoration.DecorationFactory;
import components.decoration.MidnightZoneFactory;
import components.decoration.SunlightZoneFactory;
import components.decoration.TwilightZoneFactory;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import remoteControl.AudioOffCommand;
import remoteControl.AudioOnCommand;
import remoteControl.LightOffCommand;
import remoteControl.LightOnCommand;
import remoteControl.RemoteControl;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class MarineController {

    @FXML
    private Label welcomeText;

    @FXML
    private StackPane oceanView;

    @FXML
    private BorderPane sideMenu;

    @FXML
    private StackPane fishMenu;

    @FXML
    private Pane sunlightMenu, twilightMenu, midnightMenu, sunlightZone, twilightZone, midnightZone;

    @FXML
    private Pane lightToggle;

    @FXML
    private Hyperlink undoBtn;

    @FXML
    private Button audioOnButton;

    @FXML
    private Button audioOffButton;

    private MediaPlayer mediaPlayer;

    private Ocean ocean;
    private OceanFacade oceanFacade;
    private Map<Decoration, ImageView> fishImageViews = new HashMap<>();

    private RemoteControl remoteControl;


    @FXML
    private void initialize() {
        ocean = new Ocean();
        DecorationFactory sunlightZoneFactory = new SunlightZoneFactory();
        DecorationFactory twilightZoneFactory = new TwilightZoneFactory();
        DecorationFactory midnightZoneFactory = new MidnightZoneFactory();
        oceanFacade = new OceanFacade(ocean, sunlightZoneFactory, twilightZoneFactory, midnightZoneFactory);

        createFish("Sunlight", 8);
        createFish("Twilight", 8);
        createFish("Midnight", 8);

        displayFishInMenu("Sunlight", sunlightMenu);
        displayFishInMenu("Twilight", twilightMenu);
        displayFishInMenu("Midnight", midnightMenu);

        //slot 0 for light
        remoteControl = new RemoteControl();
        setLightCommand();
        setAudioCommand();

        ToggleSwitch toggleSwitch = new ToggleSwitch(remoteControl,this);
        lightToggle.getChildren().add(toggleSwitch);

        ColorAdjust colorAdjust=  new ColorAdjust();
        colorAdjust.setBrightness(-0.5);
        oceanView.setEffect(colorAdjust);

        Image undoImg = new Image("image/undo.png");
        ImageView view = new ImageView(undoImg);
        view.setFitHeight(20);
        view.setPreserveRatio(true);
        undoBtn.setGraphic(view);
        undoBtn.setBorder(Border.EMPTY);
        undoBtn.setPadding(new Insets(4, 0, 4, 0));
        undoBtn.setOnAction(e -> undoClicked(toggleSwitch));
        undoBtn.setDisable(true);
    }

    private void createFish(String zone, int count) {
        String fishImagePath;
        switch (zone.toLowerCase()) {
            case "midnight":
                fishImagePath = getClass().getResource("/image/Fish/MidnightZone/dolphin.png").toExternalForm();
                break;
            case "twilight":
                fishImagePath = getClass().getResource("/image/Fish/TwilightZone/anglerfish.png").toExternalForm();
                break;
            case "sunlight":
                fishImagePath = getClass().getResource("/image/Fish/SunlightZone/blobfish.png").toExternalForm();
                break;
            default:
                throw new IllegalArgumentException("Invalid zone for fish creation: " + zone);
        }

        for (int i = 1; i <= count; i++) {
            String fishName = zone + "Fish" + i;
            oceanFacade.createFishInMenu(fishName, zone, fishImagePath);
        }
    }

    private void displayFishInMenu(String zone, Pane menuBox) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(6);
        gridPane.setVgap(23);
        gridPane.setPadding(new Insets(10));

        int fishCounter = 0;

        for (Decoration fish : oceanFacade.getDecorationMenuList()) {
            if (fish.getType().equalsIgnoreCase(zone)) {
                Image fishImage = new Image(fish.getImagePath());
                ImageView fishImageView = new ImageView(fishImage);
                fishImageView.setFitHeight(50);
                fishImageView.setFitWidth(50);

                Button fishButton = new Button();
                fishButton.setGraphic(fishImageView);
                fishButton.setCursor(Cursor.HAND);

                Button xBadge = new Button("X");
                xBadge.setVisible(false);
                xBadge.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-border-color: red;");

                fishButton.setOnAction(e -> placeFish(fish, xBadge));
                xBadge.setOnAction(e -> removeFish(fish, fishButton, xBadge));

                Tooltip.install(fishButton, new Tooltip(fish.getName()));

                StackPane stackPane = new StackPane(fishButton, xBadge);
                gridPane.add(stackPane, fishCounter % 4, fishCounter / 4);
                fishCounter++;
            }
        }

        menuBox.getChildren().add(gridPane);
    }


    private Pane getZonePane(String zoneType) {
        switch (zoneType.toLowerCase()) {
            case "sunlight":
                return sunlightZone;
            case "twilight":
                return twilightZone;
            case "midnight":
                return midnightZone;
            default:
                throw new IllegalArgumentException("Invalid zone type: " + zoneType);
        }
    }

    private double getFishXPosition(Pane zonePane) {
        double padding = 60;
        return padding + Math.random() * (zonePane.getWidth() - 2 * padding);
    }

    private double getFishYPosition(Decoration fish, Pane zonePane) {
        double padding = 60;
        return padding + Math.random() * (zonePane.getHeight() - 2 * padding);
    }

    public void placeFish(Decoration fish, Button xBadge) {
        if (!fishImageViews.containsKey(fish)) {
            ImageView fishImageView = new ImageView(new Image(fish.getImagePath()));
            fishImageView.setFitHeight(70);
            fishImageView.setFitWidth(70);

            Pane zonePane = getZonePane(fish.getType());
            fishImageView.setLayoutX(getFishXPosition(zonePane));
            fishImageView.setLayoutY(getFishYPosition(fish, zonePane));

            zonePane.getChildren().add(fishImageView);
            fishImageViews.put(fish, fishImageView);

            oceanFacade.addFishInOcean(fish.getName(), fish.getType());
            xBadge.setVisible(true);
        }
    }

    public void removeFish(Decoration fish, Button fishButton, Button xBadge) {
        ImageView fishImageView = fishImageViews.get(fish);
        if (fishImageView != null) {
            Pane zonePane = getZonePane(fish.getType());
            zonePane.getChildren().remove(fishImageView);
            fishImageViews.remove(fish);

            oceanFacade.removeDecorationInOcean(fish);
            fishButton.setDisable(false);
            xBadge.setVisible(false);
        }
    }

    public void setDefaultBrightness(double defaultBrightness) {
        ColorAdjust colorAdjust=  new ColorAdjust();
        colorAdjust.setBrightness(defaultBrightness);
        oceanView.setEffect(colorAdjust);
    }

    public void undoClicked(ToggleSwitch lightToggle){
        if(remoteControl.isUndoAvailable()){
            remoteControl.undoButtonWasPushed();
            lightToggle.toggle();
        }
    }

    public void onMediaPlayer(String audioFilePath) {
        // Create a Media object from the audio file path
        Media media = new Media(new File(audioFilePath).toURI().toString());

        // Create a MediaPlayer with the given media
        mediaPlayer = new MediaPlayer(media);

        // Play the audio
        mediaPlayer.play();
    }

    public void offMediaPlayer() {
        // Stop the audio
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    private static class ToggleSwitch extends Parent{
        private BooleanProperty switchedOn = new SimpleBooleanProperty(false);
        private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
        private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
        private ParallelTransition animation = new ParallelTransition(translateAnimation,fillAnimation);
        public BooleanProperty switchedOnProperty() {
            return switchedOn;
        }

        private RemoteControl controller;

        public ToggleSwitch(RemoteControl controller, MarineController ocean){
            this.controller = controller;
            Rectangle background = new Rectangle(50,30);
            background.setArcWidth(30);
            background.setArcHeight(30);
            background.setFill(Color.WHITE);
            background.setStroke(Color.LIGHTGRAY);

            Circle trigger = new Circle(15);
            trigger.setCenterX(15);
            trigger.setCenterY(15);
            trigger.setFill(Color.WHITE);
            trigger.setStroke(Color.LIGHTGRAY);

            translateAnimation.setNode(trigger);
            fillAnimation.setShape(background);

            getChildren().addAll(background,trigger);

            switchedOn.addListener((obs,oldState,newState)->{
                boolean isOn = newState.booleanValue();
                translateAnimation.setToX(isOn ? 50 - 30:  0);
                fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
                fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);
                animation.play();
            });

            setOnMouseClicked(event -> {
                if(switchedOn.get()) controller.offButtonWasPushed(0);
                else controller.onButtonWasPushed(0);
                switchedOn.set(!switchedOn.get());
                ocean.updateUndoAvailablity(false);
            });
        }

        public void toggle(){
            switchedOn.set(!switchedOn.get());
        }
    }

    private void setLightCommand(){
        Light environementLight = new Light(this);
        LightOnCommand lightOnCommand = new LightOnCommand(environementLight);
        LightOffCommand lightOffCommand = new LightOffCommand(environementLight);
        remoteControl.setCommands(0,lightOnCommand,lightOffCommand);
    }

    public void updateUndoAvailablity(boolean available){
        undoBtn.setDisable(available);
    }

    private void setAudioCommand() {
        AudioPlaylist audioPlaylist = new AudioPlaylist(this);
        AudioOnCommand audioOnCommand = AudioOnCommand.getInstance(audioPlaylist);
        AudioOffCommand audioOffCommand = AudioOffCommand.getInstance(audioPlaylist);
        remoteControl.setCommands(1, audioOnCommand, audioOffCommand);
    }

    @FXML
    protected void handleAudioOnButtonClick() {
        offMediaPlayer();
        remoteControl.onButtonWasPushed(1);
    }

    @FXML
    protected void handleAudioOffButtonClick() {
//        offMediaPlayer();
        remoteControl.offButtonWasPushed(1);
    }
}