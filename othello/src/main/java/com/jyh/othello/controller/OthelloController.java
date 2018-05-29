package com.jyh.othello.controller;

import com.jyh.othello.convertor.PiecePositionConvertor;
import com.jyh.othello.domain.GameJudger;
import com.jyh.othello.domain.GameStatus;
import com.jyh.othello.domain.OthelloBoard;
import com.jyh.othello.domain.PiecePosition;
import com.jyh.othello.domain.Player;
import com.jyh.othello.exception.InvalidPositionException;

public class OthelloController {
	private GameStatus status;
	private GameJudger judger;
	
	public void initGame() throws InvalidPositionException {
		status = new GameStatus();
		judger = new GameJudger();
	}
	
	public GameStatus putPiece(String positionStr) {
		OthelloBoard board = status.getBoard();
		Player currentPlayer = status.getCurrectPlayer();
		try{
			PiecePosition p = PiecePositionConvertor.convertStrToPiecePosition(positionStr, currentPlayer.getPieceType());
			board.putPiece(p);
			judger.judge(status);
		}catch(InvalidPositionException e) {
			status.setMsg("position is not valid, pls enter again!");
		}
		return status;
		
	}
	
	public GameStatus checkStatus() {
		return judger.checkGameOver(status);
	}
	
	public GameStatus getCurrentStatus() {
		return status;
	}
	
	public Player getCurrentPlayer() {
		return status.getCurrectPlayer();
	}
	
	public String getCurrentMsg() {
		return status.getMsg();
	}
	
	public void printBoard() {
		status.getBoard().printBoard();
	}
	

}
