package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    // String https = "https://";
    String addressLink;
    ArrayList<String> history = new ArrayList<String>();
    int currPage = 1;
    WebEngine engine;

    @FXML private Button backButton;

    @FXML private Button nextButton;

    @FXML private TextField goTextField;

    @FXML private Button goButton;

    @FXML private WebView webBox;


    @FXML
    void backButtonPressed() {
        engine.load(history.get(--currPage));
        nextButton.setDisable(false);
        if(currPage == 0) backButton.setDisable(true);
        goTextField.setText(history.get(currPage));

    }

    @FXML
    void nextButtonPressed() {
        engine.load(history.get(++currPage));
        backButton.setDisable(false);
        if(currPage == history.size() - 1) nextButton.setDisable(true);
        goTextField.setText(history.get(currPage));
    }

    @FXML
    void search() {
        addressLink = goTextField.getText();
        engine.load("https://"+addressLink);
        history.add("https://"+ addressLink);
        currPage = history.size() - 1;
        backButton.setDisable(false);
        nextButton.setDisable(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // WebEngine engine = webBox.getEngine();
        webBox.getEngine().load("www.google.com");
        nextButton.setDisable(true);
        backButton.setDisable(true);
        history.add("https://" +"www.google.com");

    }
}
