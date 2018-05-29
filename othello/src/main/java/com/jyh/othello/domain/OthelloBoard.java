package com.jyh.othello.domain;

import java.util.List;
import java.util.Stack;

import com.jyh.othello.enums.PieceTypeEnum;
import com.jyh.othello.exception.InvalidPositionException;
import com.jyh.othello.tools.PositionLoader;

/**
 * @author KimJin
 * 棋盘
 */
public class OthelloBoard {
	
	private PieceTypeEnum[][] board;
	
	//落子历史栈
	private Stack<PiecePosition> pieceStack;
	
	private int boardSize;
	
	public OthelloBoard(int size){
		this.boardSize = size;
		board = new PieceTypeEnum[size][size];
		pieceStack = new Stack<>();
	}
	
	public void putPiece(PiecePosition p) throws InvalidPositionException {
		if(p.getX() > (boardSize - 1) || p.getY() >  (boardSize - 1)) {
			throw new InvalidPositionException("position not valid!");
		}else {
			if(board[p.getX()][p.getY()] != null) {
				throw new InvalidPositionException("position not valid!");
			}
			board[p.getX()][p.getY()] = p.getPieceType();
			pieceStack.push(p);
		}
	}
	
	public void load(PositionLoader loader) throws InvalidPositionException {
		List<PiecePosition> positions = loader.loadPositions();
		for(PiecePosition p:positions) {
			board[p.getX()][p.getY()] = p.getPieceType(); 
		}
	}
	
	
	
	public void printBoard() {
		for(int x = 0 ; x < board.length; x++) {
			System.out.print(x + 1);
			for(int y = 0 ; y < board[x].length; y++) {
				if(board[x][y] == null) {
					System.out.print("-");
				}else {
					System.out.print(board[x][y].getType());
				}
			}
			System.out.println();
			if(x == board.length - 1) {
				for(int z = 0;z <= board[x].length;z++) {
					if(z == 0) {
						System.out.print(" ");
					}else {
						System.out.print((char)('a' + (z-1)));
					}
				}
			}
		}
		System.out.println();
	}

	public Stack<PiecePosition> getPieceStack() {
		return pieceStack;
	}

	public void setPieceStack(Stack<PiecePosition> pieceStack) {
		this.pieceStack = pieceStack;
	}

	public PieceTypeEnum[][] getBoard() {
		return board;
	}

	public void setBoard(PieceTypeEnum[][] board) {
		this.board = board;
	}

	public int getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	

}
