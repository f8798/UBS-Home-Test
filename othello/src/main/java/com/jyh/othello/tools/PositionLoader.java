package com.jyh.othello.tools;

import java.util.List;

import com.jyh.othello.domain.PiecePosition;
import com.jyh.othello.exception.InvalidPositionException;

public interface PositionLoader{
	public List<PiecePosition> loadPositions() throws InvalidPositionException;
}
