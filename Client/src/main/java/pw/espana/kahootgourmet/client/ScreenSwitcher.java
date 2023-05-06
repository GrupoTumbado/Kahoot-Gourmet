package pw.espana.kahootgourmet.client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import pw.espana.kahootgourmet.client.controllers.*;

import java.io.IOException;

public class ScreenSwitcher {
    private static Stage stage;
    private static Scene loginScene;
    private static Scene loadingScreenScene;
    private static Scene buttonsScene;
    private static Scene questionResultsScene;
    private static Scene scoreboardScene;
    private static LoginScreenScreenController loginScreenController;
    private static LoadingScreenController loadingScreenController;
    private static ButtonsScreenController buttonsScreenController;
    private static QuestionResultsScreenController questionResultsScreenController;
    private static ScoreboardScreenController scoreboardScreenController;

    public static void initScreenSwitcher(Stage stage) throws IOException {
        ScreenSwitcher.stage = stage;

        // Load the FXML files for the two scenes
        FXMLLoader loginSceneLoader = new FXMLLoader(ScreenSwitcher.class.getResource("login-view.fxml"));
        FXMLLoader loadingScreenSceneLoader = new FXMLLoader(ScreenSwitcher.class.getResource("loading-screen-view.fxml"));
        FXMLLoader buttonsSceneLoader = new FXMLLoader(ScreenSwitcher.class.getResource("buttons-view.fxml"));
        FXMLLoader questionResultsSceneLoader = new FXMLLoader(ScreenSwitcher.class.getResource("question-results-view.fxml"));
        FXMLLoader scoreboardSceneLoader = new FXMLLoader(ScreenSwitcher.class.getResource("scoreboard-view.fxml"));

        // Create the scenes and set their controllers
        Parent loginSceneRoot = loginSceneLoader.load();
        Parent loadingScreenSceneRoot = loadingScreenSceneLoader.load();
        Parent buttonsSceneRoot = buttonsSceneLoader.load();
        Parent questionResultsRoot = questionResultsSceneLoader.load();
        Parent scoreboardSceneRoot = scoreboardSceneLoader.load();

        loginScreenController = loginSceneLoader.getController();
        loadingScreenController = loadingScreenSceneLoader.getController();
        buttonsScreenController = buttonsSceneLoader.getController();
        questionResultsScreenController = questionResultsSceneLoader.getController();
        scoreboardScreenController = scoreboardSceneLoader.getController();

        // Initialize the scenes
        loginScene = new Scene(loginSceneRoot);
        loadingScreenScene = new Scene(loadingScreenSceneRoot);
        buttonsScene = new Scene(buttonsSceneRoot);
        questionResultsScene = new Scene(questionResultsRoot);
        scoreboardScene = new Scene(scoreboardSceneRoot);

        // Pass a reference to the stage to the controllers
        loginScreenController.setStage(stage);
        loadingScreenController.setStage(stage);
        buttonsScreenController.setStage(stage);
        questionResultsScreenController.setStage(stage);
        scoreboardScreenController.setStage(stage);
    }

    public static void terminate() {
        stage.close();
    }

    public static void showLoginScene() {
        Platform.runLater(() -> {
            stage.setScene(loginScene);
            stage.show();
        });
    }

    public static void showLoadingScene() {
        Platform.runLater(() -> {
            stage.setScene(loadingScreenScene);
            stage.show();
        });
    }

    public static void showButtonsScene() {
        Platform.runLater(() -> {
            stage.setScene(buttonsScene);
            stage.show();
        });
    }

    public static void showQuestionResultsScene() {
        Platform.runLater(() -> {
            stage.setScene(questionResultsScene);
            stage.show();
        });
    }

    public static void showScoreboardScene() {
        Platform.runLater(() -> {
            stage.setScene(scoreboardScene);
            stage.show();
        });
    }
}
