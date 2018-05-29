package com.jyh.othello.domain;

import java.util.ArrayList;
import java.util.List;

import com.jyh.othello.enums.PieceTypeEnum;

public class GameJudger {
	public void judge(GameStatus status){
		
		OthelloBoard board = status.getBoard();
		PieceTypeEnum[][] boardArray = board.getBoard();
		PiecePosition pieceJustPut = board.getPieceStack().peek();
		
		List<PiecePosition> piecePositionToChange = new ArrayList<>();

		piecePositionToChange = searchAllDirectionForToCapturePiece(boardArray, pieceJustPut);
		
		if(piecePositionToChange.size() > 0) {
			for(PiecePosition position :piecePositionToChange) {
				boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();

			}
			swichPlayer(status);
		}else {
			board.getPieceStack().pop();
			boardArray[pieceJustPut.getX()][pieceJustPut.getY()] = null;
		}
		
		
	}
	
	public GameStatus checkGameOver(GameStatus status) {
		boolean isPositionAvailable = this.isPositionAvailable(status);
		if(!isPositionAvailable) {
			status.setMsg("Player " + status.getCurrectPlayer().getName() + " has no place to put piece, switch user!");
			this.swichPlayer(status);
			
			
			isPositionAvailable = this.isPositionAvailable(status);
			if(!isPositionAvailable) {
				status.setOver(true);
				countPiece(status);
				
				status.setMsg("Game over! O has pieces: " +status.getPlayerO().getPieceCount()  +", X has pieces: " + status.getPlayerX().getPieceCount() + " Winner is " + status.getWinner().getName());
			}else {
				status.setMsg("Player " + status.getCurrectPlayer().getName() + "\'s turn, please enter the position!");
			}
		}else {
			status.setMsg("Player " + status.getCurrectPlayer().getName() + "\'s turn, please enter the position!");
		}
		return status;
	}
	
	public void countPiece(GameStatus status) {
		OthelloBoard board = status.getBoard();
		PieceTypeEnum[][] boardArray = board.getBoard();
		
		int countX = 0;
		int countO = 0;
		
		for(int x = 0 ; x < boardArray.length; x++) {
			for(int y = 0 ; y < boardArray[x].length; y++) {
				if(boardArray[x][y] == PieceTypeEnum.X) {
					countX++;
					
				}else if(boardArray[x][y] == PieceTypeEnum.O) {
					countO++;
				}
			}
		}
		
		status.getPlayerO().setPieceCount(countO);
		status.getPlayerX().setPieceCount(countX);
		if(countX > countO) {
			status.setWinner(status.getPlayerX());
		}else if(countO > countX) {
			status.setWinner(status.getPlayerO());
		}
	}
	
	public boolean isPositionAvailable(GameStatus status){
		OthelloBoard board = status.getBoard();
		PieceTypeEnum[][] boardArray = board.getBoard();
		boolean pieceToCaptureFound = false;
		for(int x = 0 ; x < boardArray.length; x++) {
			for(int y = 0 ; y < boardArray[x].length; y++) {
				if(boardArray[x][y] == null) {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(status.getCurrectPlayer().getPieceType());
					List<PiecePosition> positionList = searchAllDirectionForToCapturePiece(boardArray, p);
					if(positionList.size() > 0 ) {
						pieceToCaptureFound = true;
					}
				}
			}
		}
		return pieceToCaptureFound;
	}

	/**
	 * @param boardArray
	 * @param pieceJustPut
	 * @param piecePositionToChange
	 */
	private List<PiecePosition> searchAllDirectionForToCapturePiece(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		List<PiecePosition> piecePositionToChange = new ArrayList<PiecePosition>();
		piecePositionToChange.addAll(searchUp(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchDown(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchLeft(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchRight(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchLeftUp(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchRightUp(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchLeftDown(boardArray, pieceJustPut));
		piecePositionToChange.addAll(searchRightDown(boardArray, pieceJustPut));
		
		return piecePositionToChange;
	}

	/**
	 * 往上搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchUp(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(--x >= 0) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往下搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchDown(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(++x < boardArray.length) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往左搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchLeft(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(--y >= 0) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往右搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchRight(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(++y < boardArray[x].length) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往左上️搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchLeftUp(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(--x >= 0 && --y>=0) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往右上上️搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchRightUp(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(--x >= 0 && ++ y < boardArray[x].length) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往左下️搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchLeftDown(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			//
			if(++x < boardArray.length && --y >= 0) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	/**
	 * 往右下️搜索，可以吃的子
	 * @param boardArray
	 * @param pieceJustPut
	 */
	private List<PiecePosition> searchRightDown(PieceTypeEnum[][] boardArray, PiecePosition pieceJustPut) {
		int x = pieceJustPut.getX();
		int y = pieceJustPut.getY();
		List<PiecePosition> piecePositionOnPathForChange = new ArrayList<>();
		List<PiecePosition> piecePositionOnPath = new ArrayList<>();
		boolean foundSameOnPath = false;
		while(true) {
			if(++x < boardArray.length && ++y < boardArray[x].length ) {
				if(boardArray[x][y] == null) {
					break;
				}else if(boardArray[x][y] == pieceJustPut.getPieceType()) {
//					for(PiecePosition position:piecePositionOnPathForChange) {
//						boardArray[position.getX()][position.getY()] = pieceJustPut.getPieceType();
//					}
					foundSameOnPath = true;
					break;
				}else {
					PiecePosition p = new PiecePosition();
					p.setX(x);
					p.setY(y);
					p.setPieceType(boardArray[x][y]);
					piecePositionOnPath.add(p);
					continue;
				}
			}else {
				break;
			}
		}
		if(foundSameOnPath) {
			piecePositionOnPathForChange.addAll(piecePositionOnPath);
		}
		return piecePositionOnPathForChange;
	}
	
	private void swichPlayer(GameStatus status) {
		if(status.getCurrectPlayer() == status.getPlayerO()) {
			status.setCurrectPlayer(status.getPlayerX());
		}else {
			status.setCurrectPlayer(status.getPlayerO());
		}
	}
}
