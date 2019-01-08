package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import com.sun.org.glassfish.gmbal.GmbalException;
import frontend.networking.MessageInterpreter;
import frontend.util.BoardField;
import frontend.util.ControllerNetworkFacade;
import frontend.util.TurnState;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.exceptions.MoveNotAllowedException;
import model.exceptions.NoSuchPieceException;
import model.exceptions.NoSuchPlayerException;
import model.player.PiecePosition;
import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Game extends AbstractController {
    private List<BoardField> fields = new ArrayList<>();
    private List<BoardField> availableMoves = new ArrayList<>();
    private BoardField chosen;
    private TurnState state;

    @FXML
    VBox boardBox;

    @FXML
    Label player;

    @Override
    public void onSwitch() {
        MessageInterpreter.spawnFacade(this);
        Platform.runLater(() -> {
            renderFields();
        });
    }

    @FXML
    public void initialize() {
    }

    public void renderFields() {
        Platform.runLater(() -> {
            System.out.println("RENDERING FIELDS...");
            boardBox.getChildren().clear();
            int[][] boardFields = game.getBoardMovementInterface().getBoard().getPositions();
            for (int i = 0; i < boardFields.length; i++) {
                HBox hbox = new HBox();
                hbox.setAlignment(Pos.CENTER);
                for (int j = 0; j < boardFields[i].length; j++) {
                    final BoardField button = new BoardField("#343434");
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        public void handle(ActionEvent event) {
                            System.out.println("handled");
                            switch (state) {
                                case YOUR_TURN:
                                    System.out.println("your-turn");
                                    choose(button);
                                    chosen = button;
                                    break;
                                case PIECE_CHOSEN:
                                    System.out.println("piece-chosen");
                                    move(chosen, button);
                                    break;
                            }
                        }
                    });
                    button.setDisable(true);
                    if (boardFields[i][j] == 0) {
                        button.setPosition(new PiecePosition(i, j));
                        hbox.getChildren().add(button);
                        fields.add(button);
                    } else {
                        PiecePosition pos = new PiecePosition(i, j);
                        button.setPosition(pos);
                        boolean n = false;
                        if (boardFields[i][j] > 0 && boardFields[i][j] < 7) {
                            try {
                                Player tempPlayer = game.getPlayerById(boardFields[i][j]);

                                try {
                                    button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                } catch (NoSuchPieceException e) {
                                    showAlert(e.getMessage());
                                }
                                switch (boardFields[i][j]) {
                                    case 1:
                                        button.setStyle("-fx-color: #182257");
                                        break;
                                    case 2:
                                        button.setStyle("-fx-color: #D4CB6A");
                                        break;
                                    case 3:
                                        button.setStyle("-fx-color: #801515");
                                        break;
                                    case 4:
                                        button.setStyle("-fx-color: #3E7213");
                                        break;
                                    case 5:
                                        button.setStyle("-fx-color: #341456");
                                        break;
                                    case 6:
                                        button.setStyle("-fx-color: #805215");
                                        break;
                                }
                            } catch (NoSuchPlayerException e) {
                                //let's just ignore this tbh
                                //showAlert(e.getMessage());
                            }
                        } else {
                            n = true;
                        }
                        if (!n) {
                            fields.add(button);
                            hbox.getChildren().add(button);
                        }
                    }
                }
                boardBox.getChildren().add(hbox);
            }
            nextTurn();
        });
    }

    public void nextTurn() {
        try {
            player.setText(game.getPlayerById(game.getTurn()).getName());
        } catch (NoSuchPlayerException e) {
            showAlert(e.getMessage());
        }
        if (game.getTurn() == thisPlayer.getId()) {
            state = TurnState.YOUR_TURN;
            for (BoardField field : fields) {
                if (field.getPiece() != null && field.getPiece().getId() == thisPlayer.getId()) {
                    field.setDisable(false);
                } else {
                    field.setDisable(true);
                }
            }
        }
    }

    private void move(BoardField origin, BoardField target) {
        Platform.runLater(() -> {
            try {
                game.getBoardMovementInterface().makeMove(origin.getPiece(), target.getPosition());
                ControllerNetworkFacade.moved(origin.getPosition(), target.getPosition());
            } catch (MoveNotAllowedException e) {
                showAlert(e.getMessage());
                return;
            }
            //we just re-render fields
            renderFields();
        });
    }

    private void choose(BoardField field) {
        PiecePosition[] moves = game.getBoardMovementInterface().getMoves(field.getPiece());
        //System.out.println("Printing possible moves:");
        for (PiecePosition move : moves) {
            //System.out.println("Move: " + move);
            for (BoardField boardField : fields) {
                if (boardField.getPosition().equals(move)) {
                    availableMoves.add(boardField);
                } else {
                    boardField.setDisable(true);
                }
            }
        }
        for (BoardField boardField : availableMoves) {
            boardField.setDisable(false);
        }
        state = TurnState.PIECE_CHOSEN;
    }
}
