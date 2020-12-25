package PainterApp;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Painter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Painter.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Painter");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static class PainterController {

        private enum PenSize {
            SMALL(2),
            MEDIUM(4),
            LARGE(6);

            private final int radius;

            PenSize(int radius) {this.radius = radius;}

            public int getRadius() {return radius;}
        };
        @FXML
        private Slider redSlider;
        @FXML private Slider greenSlider;
        @FXML private Slider blueSlider;
        @FXML private Slider alphaSlider;
        @FXML private TextField redTextField;
        @FXML private TextField greenTextField;
        @FXML private TextField blueTextField;
        @FXML private TextField alphaTextField;
        @FXML private Rectangle colorRectangle;

        private int red = 0;
        private int green = 0;
        private int blue = 0;
        private double alpha = 1.0;

        @FXML private RadioButton smallRadioButton;
        @FXML private RadioButton mediumRadioButton;
        @FXML private RadioButton largeRadioButton;
        @FXML private Pane drawingAreaPane;
        @FXML private ToggleGroup colorToggleGroup;
        @FXML private ToggleGroup sizeToggleGroup;


        private PenSize radius = PenSize.MEDIUM;
        private Paint brushColor = Color.BLACK;


        public void initialize() {
            redTextField.textProperty().bind(
                    redSlider.valueProperty().asString("%.0f"));
            greenTextField.textProperty().bind(
                    greenSlider.valueProperty().asString("%.0f"));
            blueTextField.textProperty().bind(
                    blueSlider.valueProperty().asString("%.0f"));
            alphaTextField.textProperty().bind(
                    alphaSlider.valueProperty().asString("%.2f"));

            redSlider.valueProperty().addListener(
                    new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> ov,
                                            Number oldValue, Number newValue) {
                            red = newValue.intValue();
                            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                        }
                    }
            );
            greenSlider.valueProperty().addListener(
                    new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> ov,
                                            Number oldValue, Number newValue) {
                            green = newValue.intValue();
                            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                        }
                    }
            );
            blueSlider.valueProperty().addListener(
                    new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> ov,
                                            Number oldValue, Number newValue) {
                            blue = newValue.intValue();
                            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                        }
                    }
            );
            alphaSlider.valueProperty().addListener(
                    new ChangeListener<Number>() {
                        @Override
                        public void changed(ObservableValue<? extends Number> ov,
                                            Number oldValue, Number newValue) {
                            alpha = newValue.doubleValue();
                            colorRectangle.setFill(Color.rgb(red, green, blue, alpha));
                        }
                    }
            );

        }


        @FXML
        private void drawingAreaMouseDragged(MouseEvent e) {
            Circle newCircle = new Circle(e.getX(), e.getY(),
                    radius.getRadius(), Color.rgb(red, green, blue, alpha));
            drawingAreaPane.getChildren().add(newCircle);
        }

        @FXML
        private void colorRadioButtonSelected(ActionEvent e) {
       }

        // handles size RadioButton's ActionEvents
        @FXML
        private void sizeRadioButtonSelected(ActionEvent e) {
            // user data for each size RadioButton is the corresponding PenSize
            radius =(PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
        }


        @FXML
        private void undoButtonPressed(ActionEvent event) {
            int count = drawingAreaPane.getChildren().size();


            if (count > 0) {
                drawingAreaPane.getChildren().remove(count - 1);
            }
        }

        @FXML
        private void clearButtonPressed(ActionEvent event) {
            drawingAreaPane.getChildren().clear();
        }
    }
}
