package com.jyh.othello.tools;

import java.io.File;


public class PositionLoaderFactory {
	
	public static PositionLoader getCsvPositionLoader() {
		
		return new CsvPositionLoader(new File(Thread.currentThread().getContextClassLoader().getResource("./init.csv").getPath()));
	}
}
