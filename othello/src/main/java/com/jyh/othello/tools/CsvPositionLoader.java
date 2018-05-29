package com.jyh.othello.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jyh.othello.convertor.PiecePositionConvertor;
import com.jyh.othello.domain.PiecePosition;
import com.jyh.othello.enums.PieceTypeEnum;
import com.jyh.othello.exception.InvalidPositionException;

public class CsvPositionLoader implements PositionLoader{

	private File csvFilePosition;
	public CsvPositionLoader(File csvFilePosition) {
		this.csvFilePosition = csvFilePosition;
	}
	public List<PiecePosition> loadPositions() throws InvalidPositionException {
		List<PiecePosition> resultList = new ArrayList<>();
		List<String> lines = null;
		
		lines  = getLineList(csvFilePosition);

		String xLine = lines.get(0);
		String oLine = lines.get(1);

		resultList.addAll(turnIntoPiecePositions(xLine, PieceTypeEnum.X));
		
		resultList.addAll(turnIntoPiecePositions(oLine, PieceTypeEnum.O));

		return resultList;		
	}

	private List<String> getLineList(File csvFilePosition) {
		List<String> lines = new ArrayList<>();

		FileReader fr = null;
		BufferedReader br = null ;
		
		try {
			fr = new FileReader(csvFilePosition);
			br = new BufferedReader(fr);
			
			String line = null;
			while((line = br.readLine())!=null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Excepiton happened when reading file!");
		}finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Excepiton happened when reading file!");
			}
		}
		return lines;
	}
	
	private List<PiecePosition> turnIntoPiecePositions(String positionLine, PieceTypeEnum type) throws InvalidPositionException {
		List<PiecePosition> resultList = new ArrayList<>();
		String[] positions = positionLine.split(",");
		for(String positionStr:positions) {
			PiecePosition p = PiecePositionConvertor.convertStrToPiecePosition(positionStr, type);
			resultList.add(p);
			
		}
		return resultList;
	}

	public static void main(String[] args) {
		System.out.println(String.valueOf((int)('a' - 48)));
	}
}
