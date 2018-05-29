package com.jyh.othello.domain;

import com.jyh.othello.enums.PieceTypeEnum;

/**
 * @author KimJin
 * 玩家
 */
public class Player {
	private String name;
	
	/**
	 * 棋子数量
	 */
	private int pieceCount;
	
	/**
	 * 所执棋子类型
	 */
	private PieceTypeEnum pieceType;

	public int getPieceCount() {
		return pieceCount;
	}

	public void setPieceCount(int pieceCount) {
		this.pieceCount = pieceCount;
	}

	public PieceTypeEnum getPieceType() {
		return pieceType;
	}

	public void setPieceType(PieceTypeEnum pieceType) {
		this.pieceType = pieceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name + "["+pieceCount+"]";
		
	}
}
