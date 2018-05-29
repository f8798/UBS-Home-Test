/**
 * 
 */
package com.jyh.othello.domain;

import com.jyh.othello.enums.PieceTypeEnum;

/**
 * @author KimJin
 * 棋子位置
 */
public class PiecePosition {
	private int x;
	private int y;
	
	private PieceTypeEnum pieceType;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public PieceTypeEnum getPieceType() {
		return pieceType;
	}
	public void setPieceType(PieceTypeEnum pieceType) {
		this.pieceType = pieceType;
	}
	
	
}
