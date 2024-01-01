package frontend;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class MarineDesigner extends Application {
    class OceanSetter{
        @FXML
        private ImageView imageView;
        @FXML
        private Pane oceanView;
        @FXML
        private BorderPane sideMenu;
        @FXML
        private BorderPane commandMenu;
        @FXML
        private BorderPane fishMenu;
        @FXML
        public void initialize(){
            //set image onto imageview
//            Image oceanImage = new Image(getClass().getResource("/image/ocean.jpg").toExternalForm());
//            ImageView imageView = new ImageView(oceanImage);
//            imageView.setFitWidth(1180);
//            imageView.setFitHeight(780);
//
//            //create rectangle to curve the image but clipping the image to curved rectangle
//            Rectangle clip = new Rectangle(1180, 780);
//            clip.setArcWidth(30.0);
//            clip.setArcHeight(30.0);
//            imageView.setClip(clip);
//
//            //snap the image of the imageView
//            SnapshotParameters parameters = new SnapshotParameters();
//            parameters.setFill(Color.TRANSPARENT);
//            WritableImage image = imageView.snapshot(parameters, null);
//
//            // remove the rounding clip so that our effect can show through.
//            imageView.setClip(null);
//
//            // apply a shadow effect.
//            imageView.setEffect(new DropShadow(80, Color.BLACK));
//
//            // store the rounded image in the imageView.
//            imageView.setImage(image);
//            oceanView.getChildren().add(imageView);
//
//            sideMenu.setStyle("-fx-border-color: black; -fx-border-width: 2;");
//            commandMenu.setStyle("-fx-border-color: black; -fx-border-width: 2;");
//            fishMenu.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarineDesigner.class.getResource("marine_designer.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 720);
        stage.setTitle("Marine Designer");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}