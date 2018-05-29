package com.jyh.othello;

import java.util.Scanner;

import com.jyh.othello.controller.OthelloController;
import com.jyh.othello.exception.InvalidPositionException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		OthelloController controller = new OthelloController();
		try {
			controller.initGame();
		} catch (InvalidPositionException e) {
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("Welcome to Othello Game!");
		while (true) {
			controller.printBoard();
			controller.checkStatus();
			System.out.println(controller.getCurrentMsg());
			System.out.println("[type 'exit' to exit, type 'restart' to restart]");
			Scanner sc = new Scanner(System.in);
			String input = null;
			try{
				input = sc.nextLine();
			}catch(Exception e) {
				continue;
			}
			
			if(input.equalsIgnoreCase("restart")) {
				try {
					controller.initGame();
				} catch (InvalidPositionException e) {
					e.printStackTrace();
					System.exit(0);
				}
			}else if(input.equalsIgnoreCase("exit")){
				System.exit(0);
			}
			controller.putPiece(input);
		}

	}
}
