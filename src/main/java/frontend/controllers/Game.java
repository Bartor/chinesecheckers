package frontend.controllers;

import com.jfoenix.controls.JFXButton;
import frontend.util.BoardField;
import frontend.util.TurnState;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.exceptions.MoveNotAllowedException;
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

    @FXML
    JFXButton map;

    @FXML
    public void initialize() {
        //todo delete this, debug purposes only
        state = TurnState.YOUR_TURN;
        map.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Loading fields");
                loadFields();
            }
        });
    }

    private void wipeAvailable() {
        for (BoardField field : availableMoves) {
            field.setDisable(true);
            field.setSelected(false);
        }
        availableMoves.clear();
    }

    private void loadFields() {
        int[][] boardFields = game.getBoardMovementInterface().getBoard().getBoardFields();
        for (int i = 0; i < boardFields.length; i++) {
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            for (int j = 0; j < boardFields[i].length; j++) {
                final BoardField button = new BoardField("white");
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
                    boolean n = false;
                    switch (boardFields[i][j]) {
                        case 1: {
                            Player tempPlayer;
                            try {
                                tempPlayer = game.getPlayerById(1);
                                button.setPlayer(tempPlayer);
                                button.setPosition(pos);
                                button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                button.setStyle("-fx-color: blue");
                                break;
                            } catch (NoSuchPlayerException e) {
                                e.printStackTrace();
                            }

                        }
                        case 2: {
                            Player tempPlayer;
                            try {
                                tempPlayer = game.getPlayerById(2);
                                button.setPlayer(tempPlayer);
                                button.setPosition(pos);
                                button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                button.setStyle("-fx-color: yellow;");
                                break;
                            } catch (NoSuchPlayerException e) {
                                e.printStackTrace();
                            }

                        }
                        case 3: {
                            Player tempPlayer;
                            try {
                                tempPlayer = game.getPlayerById(3);
                                button.setPlayer(tempPlayer);
                                button.setPosition(pos);
                                button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                button.setStyle("-fx-color: green;");
                                break;
                            } catch (NoSuchPlayerException e) {
                                e.printStackTrace();
                            }

                        }
                        case 4: {
                            Player tempPlayer;
                            try {
                                tempPlayer = game.getPlayerById(4);
                                button.setPlayer(tempPlayer);
                                button.setPosition(pos);
                                button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                button.setStyle("-fx-color: red;");
                                break;
                            } catch (NoSuchPlayerException e) {
                                e.printStackTrace();
                            }

                        }
                        case 5: {
                            Player tempPlayer;
                            try {
                                tempPlayer = game.getPlayerById(5);
                                button.setPlayer(tempPlayer);
                                button.setPosition(pos);
                                button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                button.setStyle("-fx-color: purple;");
                                break;
                            } catch (NoSuchPlayerException e) {
                                e.printStackTrace();
                            }

                        }
                        case 6: {
                            Player tempPlayer;
                            try {
                                tempPlayer = game.getPlayerById(6);
                                button.setPlayer(tempPlayer);
                                button.setPosition(pos);
                                button.setPiece(tempPlayer.getArmy().getPieceByPosition(pos));
                                button.setStyle("-fx-color: orange;");
                                break;
                            } catch (NoSuchPlayerException e) {
                                e.printStackTrace();
                            }

                        }
                        default: {
                            n = true;
                            break;
                        }
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
    }

    private void nextTurn() {
        if (game.getTurn() == thisPlayer.getId()) {
            for (BoardField field : fields) {
                if (field.getPlayer() == thisPlayer) {
                    field.setDisable(false);
                }
            }
        }
    }

    private void move(BoardField origin, BoardField target) {
        target.setStyle(origin.getStyle());
        origin.setStyle("-fx-color: white");
        try {
            game.getBoardMovementInterface().makeMove(origin.getPiece(), target.getPosition());
        } catch (MoveNotAllowedException e) {
            showAlert(e.getMessage());
        }
        origin.setSelected(false);
        wipeAvailable();
        target.setPiece(origin.getPiece());
        origin.setPiece(null);
    }

    private void choose(BoardField field) {
        PiecePosition[] moves = game.getBoardMovementInterface().getMoves(field.getPiece());
        System.out.println("Printing possible moves:");
        for (PiecePosition move : moves) {
            System.out.println("Move: " + move);
            for (BoardField boardField : fields) {
                if (boardField.getPosition().getCol() == move.getCol() && boardField.getPosition().getRow() == move.getRow()) {
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
