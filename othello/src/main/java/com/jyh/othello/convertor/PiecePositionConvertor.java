package com.jyh.othello.convertor;

import com.jyh.othello.domain.PiecePosition;
import com.jyh.othello.enums.PieceTypeEnum;
import com.jyh.othello.exception.InvalidPositionException;

public class PiecePositionConvertor {
	public static PiecePosition convertStrToPiecePosition(String positionStr, PieceTypeEnum type) throws InvalidPositionException{
		if(positionStr == null || positionStr.length() != 2) {
			throw new InvalidPositionException("positionStr invalid! positionStr:" + positionStr);
		}
		char c1 = positionStr.charAt(0);
		char c2 = positionStr.charAt(1);
		
		PiecePosition p = new PiecePosition();
		p.setPieceType(type);
		if(((c1 >= 'a' && c1 <= 'h') && (c2 >= '1' && c2 <= '8') ) ) {
			p.setX(c2 - 49);
			p.setY(c1 - 97);
			
		}else if(((c2 >= 'a' && c2 <= 'h') && (c1 >= '1' && c1 <= '8') ) ){
			p.setX(c1 - 49);
			p.setY(c2 - 97);
		}else {
			throw new InvalidPositionException("positionStr invalid! positionStr:" + positionStr);
		}
		return p;
	}
}
