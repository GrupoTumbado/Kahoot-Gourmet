package pw.espana.kahootgourmet.server;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pw.espana.kahootgourmet.server.controllers.*;

import java.io.IOException;

public class ScreenSwitcher {
    private static Stage stage;
    private static Scene answerScene;
    private static Scene mainScene;
    private static Scene questionAnswersScene;
    private static Scene questionScene;
    private static Scene quizEditorScene;
    private static Scene scoreboardScene;
    private static Scene startGameScene;
    private static AnswerScreenController answerScreenController;
    private static MainScreenController mainScreenController;
    private static QuestionAnswersScreenController questionAnswersScreenController;
    private static QuestionScreenController questionScreenController;
    private static QuizEditorScreenController quizEditorScreenController;
    private static ScoreboardScreenController scoreboardScreenController;
    private static StartGameScreenController startGameScreenController;

    public static void initScreenSwitcher(Stage stage) throws IOException {
        ScreenSwitcher.stage = stage;

        // Load the FXML files for the two scenes
        FXMLLoader answerScreenLoader = new FXMLLoader(ScreenSwitcher.class.getResource("answer-view.fxml"));
        FXMLLoader mainScreenLoader = new FXMLLoader(ScreenSwitcher.class.getResource("main-view.fxml"));
        FXMLLoader questionAnswersScreenLoader = new FXMLLoader(
                ScreenSwitcher.class.getResource("question-answers-view.fxml"));
        FXMLLoader questionScreenLoader = new FXMLLoader(ScreenSwitcher.class.getResource("question-view.fxml"));
        FXMLLoader quizEditorScreenLoader = new FXMLLoader(ScreenSwitcher.class.getResource("quiz-editor-view.fxml"));
        FXMLLoader scoreboardScreenLoader = new FXMLLoader(ScreenSwitcher.class.getResource("scoreboard-view.fxml"));
        FXMLLoader startGameScreenLoader = new FXMLLoader(ScreenSwitcher.class.getResource("start-game-view.fxml"));

        // Create the scenes and set their controllers
        Parent answerScreenRoot = answerScreenLoader.load();
        Parent mainScreenRoot = mainScreenLoader.load();
        Parent questionAnswersScreenRoot = questionAnswersScreenLoader.load();
        Parent questionScreenRoot = questionScreenLoader.load();
        Parent quizEditorScreenRoot = quizEditorScreenLoader.load();
        Parent scoreboardScreenRoot = scoreboardScreenLoader.load();
        Parent startGameScreenRoot = startGameScreenLoader.load();

        answerScreenController = answerScreenLoader.getController();
        mainScreenController = mainScreenLoader.getController();
        questionAnswersScreenController = questionAnswersScreenLoader.getController();
        questionScreenController = questionScreenLoader.getController();
        quizEditorScreenController = quizEditorScreenLoader.getController();
        scoreboardScreenController = scoreboardScreenLoader.getController();
        startGameScreenController = startGameScreenLoader.getController();

        // Initialize the scenes
        answerScene = new Scene(answerScreenRoot);
        mainScene = new Scene(mainScreenRoot);
        questionAnswersScene = new Scene(questionAnswersScreenRoot);
        questionScene = new Scene(questionScreenRoot);
        quizEditorScene = new Scene(quizEditorScreenRoot);
        scoreboardScene = new Scene(scoreboardScreenRoot);
        startGameScene = new Scene(startGameScreenRoot);

        // Pass a reference to the stage to the controllers
        answerScreenController.setStage(stage);
        mainScreenController.setStage(stage);
        questionScreenController.setStage(stage);
        questionAnswersScreenController.setStage(stage);
        quizEditorScreenController.setStage(stage);
        scoreboardScreenController.setStage(stage);
        startGameScreenController.setStage(stage);
    }

    public static void terminate() {
        stage.close();
    }

    public static void showAnswerScene() {
        Platform.runLater(() -> {
            stage.setScene(answerScene);
            stage.show();
            answerScreenController.onLoad();
        });
    }

    public static void showMainScene() {
        Platform.runLater(() -> {
            stage.setScene(mainScene);
            stage.show();
            mainScreenController.onLoad();
        });
    }

    public static void showQuestionAnswersScene() {
        Platform.runLater(() -> {
            stage.setScene(questionAnswersScene);
            stage.show();
            questionAnswersScreenController.onLoad();
        });
    }

    public static void showQuestionScene() {
        Platform.runLater(() -> {
            stage.setScene(questionScene);
            stage.show();
            questionScreenController.onLoad();
        });
    }

    public static void showQuizEditorScene() {
        Platform.runLater(() -> {
            stage.setScene(quizEditorScene);
            stage.show();
            quizEditorScreenController.onLoad();
        });
    }

    public static void showScoreboardScene() {
        Platform.runLater(() -> {
            stage.setScene(scoreboardScene);
            stage.show();
        });
    }

    public static void showStartGameScene() {
        Platform.runLater(() -> {
            stage.setScene(startGameScene);
            stage.show();
            startGameScreenController.onLoad();
        });
    }
}
