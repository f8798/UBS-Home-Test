/**
 * 
 */
package com.jyh.othello.enums;

/**
 * @author KimJin
 * 棋子类型
 */
public enum PieceTypeEnum {
	X("X"),
	O("O"),;
	
	private PieceTypeEnum(String type) {
		this.type = type;
	}
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
