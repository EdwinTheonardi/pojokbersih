package com.pojokbersih;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Stage window;
    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        // Load fonts
        Font.loadFont(getClass().getResourceAsStream("fonts/OpenSans-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("fonts/OpenSans-SemiBold.ttf"), 12);
        // Font.loadFont(getClass().getResourceAsStream("fonts/OpenSans-Bold.ttf"), 12);

        BorderPane border = new BorderPane();
        border.getStyleClass().add("border-pane");
        border.setCenter(addGrid());

        scene = new Scene(border, 1280, 720);
        scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
        stage.setTitle("Pojok Bersih");
        stage.setScene(scene);
        stage.show();
    }

    public GridPane addGrid(){
        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid-pane");
        grid.setPrefSize(400, 550);
        grid.setMinSize(400, 550);
        grid.setMaxSize(400, 550);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);

        // Logo
        Image logoImage = new Image(getClass().getResourceAsStream("images/logo.png"));
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(120);
        logoView.setFitHeight(120);
        grid.setMargin(logoView, new Insets(0, 0, 0, 0));
        GridPane.setHalignment(logoView, HPos.CENTER);

        // Login Title
        Text loginTitle = new Text("Login");
        loginTitle.getStyleClass().add("login-title");
        grid.setMargin(loginTitle, new Insets(0, 0, 35, 0));
        grid.setHalignment(loginTitle, HPos.CENTER);

        // Username & Password Title
        Label username = new Label("Username");
        username.getStyleClass().add("username-label");
        Label password = new Label("Password");
        password.getStyleClass().add("password-label");

        // Username & Password Field
        TextField usernameField = new TextField();
        usernameField.setPrefSize(300, 35);
        TextField passwordField = new TextField();
        passwordField.setPrefSize(300, 35);

        // Login Button
        Button login = new Button("Login");
        login.getStyleClass().add("login-btn");
        login.setPrefSize(300, 35);
        grid.setMargin(login, new Insets(125, 0, 0, 0));
        
        // grid.add(logoView, 0, 0);
        grid.add(loginTitle, 0, 1);
        grid.add(username, 0, 2);
        grid.add(usernameField, 0, 3);
        grid.add(password, 0, 4);
        grid.add(passwordField, 0, 5);
        grid.add(login, 0, 6);

        login.setOnAction(e -> {
            Home home = new Home();
            window.getScene().setRoot(home.getRootPane());
        });

        return grid;
    }

// ---------------------------------------------------------------------------------------------------------------------------
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}