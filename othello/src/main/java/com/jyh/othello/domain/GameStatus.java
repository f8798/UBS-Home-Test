package com.jyh.othello.domain;

import java.util.List;

import com.jyh.othello.enums.PieceTypeEnum;
import com.jyh.othello.exception.InvalidPositionException;
import com.jyh.othello.tools.PositionLoaderFactory;

public class GameStatus {
	private Player playerX;
	private Player playerO;
	
	private Player currectPlayer;
	private Player winner;
	private boolean isOver;
	private OthelloBoard board;
	private String msg;
	
	
	public GameStatus() throws InvalidPositionException{
		playerX = new Player();
		playerX.setName("X");
		playerX.setPieceType(PieceTypeEnum.X);
		playerO = new Player();
		playerO.setPieceType(PieceTypeEnum.O);
		playerO.setName("O");
		
		currectPlayer = playerO;
		
		//define the size of the board;
		board = new OthelloBoard(8);
		//load the board
		board.load(PositionLoaderFactory.getCsvPositionLoader());
	}
	
	public Player getCurrectPlayer() {
		return currectPlayer;
	}
	public void setCurrectPlayer(Player currectPlayer) {
		this.currectPlayer = currectPlayer;
	}
	public Player getWinner() {
		return winner;
	}
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	public boolean isOver() {
		return isOver;
	}
	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}
	public OthelloBoard getBoard() {
		return board;
	}
	public void setBoard(OthelloBoard board) {
		this.board = board;
	}

	public Player getPlayerX() {
		return playerX;
	}

	public void setPlayerX(Player playerX) {
		this.playerX = playerX;
	}

	public Player getPlayerO() {
		return playerO;
	}

	public void setPlayerO(Player playerO) {
		this.playerO = playerO;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
