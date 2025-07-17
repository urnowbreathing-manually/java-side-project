import java.util.Scanner;
import java.util.Random;

// System.out.print("\033[H\033[2J"); //࿕ ☭

public class aStarPathfinding2{

{		/*
			[0][n] == Origin Coords; Where pathfinding starts
			          - map_row value = -3; cellState name = "ORG"

			[1][n] == Target Coords; Where pathfinding ends
			          - map_row value = -4; cellState name = "TGT"

			[2][n] == Current Index; Where pathfinding currently is taking place
		*/} // vipCoords description

	int[][] vipCoords = new int[3][2];
	int mapSize_row = 9;
	int mapSize_col = 12;
	int turnCheck = 1;

	public static void main(String[] args) {

{		/*	
			Array for storing cell scores
			Cell scores determines where the current index will move to
			The the current index will move to the lowest cell score in the whole array with every loop step
			If 2 or more cells have the same score, current index will move to the cell closest to (0,0)
			Current index will not move to any cell with a score lower than -1 (Score assigned for air or empty space)
			Since cells with scores lower than -1 could mean that the cell is either a wall (-2), the origin cell (-3), or the target cell (-4)
		*/} // map_row description

		String map_row[][] = 
			{{"-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1", "-2"},
			{"-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2", "-2"}};

{		/*
			Array for storing cell states
			Cell states determine how the current index will interact with a cell
			Current index only moves to a cell that has the lowest cell score, and is also an "OPEN" cell
			OPEN cells are cells that the current index has not moved to yet, but has been chosen for evaluation
			cells are "opened" whenever they are adjacent to the origin cell
			or when the current index moves to a cell adjacent to it
			CLOSED cells are cell that the current index has already been in
			having been chosen for having the lowest cell score
			Other cell states include: WALL, AIR, ORIGIN, and, TARGET
		*/} //cellStates description

		String cellStates[][] = 
			{{"███", "███", "███", "███", "███", "███", "███", "███", "███", "███", "███", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "░░░", "███"},
			{"███", "███", "███", "███", "███", "███", "███", "███", "███", "███", "███", "███"}};

		aStarPathfinding2 lvlGridIndex = new aStarPathfinding2();

		resetMap(lvlGridIndex, map_row, cellStates);

		System.out.print("\n\nRandomly generated Origin and Target coords.\n\n");
		for(int i = 0; i < 3; i++){
			resetVIPCoords(lvlGridIndex, i, map_row, cellStates);
		}

		System.out.println("\n[]█████████████████████████[ Turn #" + (lvlGridIndex.turnCheck) + " ]█████████████████████████[]");
		printMap(map_row, cellStates);
		

		System.out.println("End of Turn: [#" + lvlGridIndex.turnCheck + "]");
		lvlGridIndex.turnCheck++;

		aStarAlgorithm(lvlGridIndex, map_row, cellStates);

		printMap(map_row, cellStates);
		System.out.println("  No. of Turns: [#" + lvlGridIndex.turnCheck + "]");

	}

	public static void printMap(String map_row[][], String cellStates[][]){
		System.out.print("\n\nmap_row; Contains all cell scores.\n\n");
		for(String[] a : map_row){
			for(String b : a){
				System.out.print(b + "║");
			}
			System.out.print("\n");
		}

		System.out.print("\n\ncellStates; Contains all cell states.\n\n");
		for(String[] a : cellStates){
			for(String b : a){
				System.out.print(b + "▓");
			}
			System.out.print("\n");
			// System.out.println("═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬═══╬");
			System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");

		}
	}

	public static void resetMap(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][]){
		for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
			for(int col = 0; col < lvlGridIndex.mapSize_col; col++){
				if(Integer.parseInt(map_row[row][col]) > -1){
					map_row[row][col] = "-1";
				}
			}
		}

		for (int row = 0; row < lvlGridIndex.mapSize_row; row++){
			for (int col = 0; col < lvlGridIndex.mapSize_col; col++){
				if((cellStates[row][col] == "OPN") || (cellStates[row][col] == "CLS") || (cellStates[row][col] == "ORG") || (cellStates[row][col] == "TGT")){
					cellStates[row][col] = "░░░";
				}
			}
		}
	}

	public static void resetVIPCoords(aStarPathfinding2 lvlGridIndex, int i, String map_row[][], String cellStates[][]){
		Random vipcRandomizer = new Random();

		switch(i){
			case 0: // Reset origin coords
				do{
					// lvlGridIndex.vipCoords[0][0] = vipcRandomizer.nextInt(7) + 1;
					// lvlGridIndex.vipCoords[0][1] = vipcRandomizer.nextInt(10) + 1;

					lvlGridIndex.vipCoords[0][0] = 5;
					lvlGridIndex.vipCoords[0][1] = 5;

				} while(Integer.parseInt(map_row[lvlGridIndex.vipCoords[0][0]][lvlGridIndex.vipCoords[0][1]]) == -2);

				for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
					for(int col = 0; col < lvlGridIndex.mapSize_col; col++){
						if((lvlGridIndex.vipCoords[0][0] == row) && (lvlGridIndex.vipCoords[0][1] == col)){
							for(int j = -1; j < 2; j++){
								for(int k = -1; k < 2; k++){
									if((map_row[row + j][col + k] == "-1")){
										switch(j){
											case -1:
												if(k == 0){
													map_row[row + j][col + k] = "10";
												} else{
													map_row[row + j][col + k] = "14";
												}
												cellStates[row + j][col + k] = "OPN";
												break;
											case 0:
												if(k == 0){
													map_row[row][col] = "-3";
													cellStates[row][col] = "ORG";
												} else{
													map_row[row + j][col + k] = "10";
													cellStates[row + j][col + k] = "OPN";
												}
												break;
											case 1:
												if(k == 0){
													map_row[row + j][col + k] = "10";
												} else{
													map_row[row + j][col + k] = "14";
												}
												cellStates[row + j][col + k] = "OPN";
												break;
										}
									}
								}
							}
						}
					}
				}



				System.out.println(" Origin Coords: [" + lvlGridIndex.vipCoords[0][0] + "][" + lvlGridIndex.vipCoords[0][1] + "]");
				break;

			case 1: // Reset target coords
				do{
					// lvlGridIndex.vipCoords[1][0] = vipcRandomizer.nextInt(7) + 1;
					// lvlGridIndex.vipCoords[1][1] = vipcRandomizer.nextInt(10) + 1;

					lvlGridIndex.vipCoords[1][0] = 2;
					lvlGridIndex.vipCoords[1][1] = 2;

				} while((Integer.parseInt(map_row[lvlGridIndex.vipCoords[1][0]][lvlGridIndex.vipCoords[1][1]]) == -2) || (cellStates[lvlGridIndex.vipCoords[1][0]][lvlGridIndex.vipCoords[1][1]] == "ORG") || (cellStates[lvlGridIndex.vipCoords[1][0]][lvlGridIndex.vipCoords[1][1]] == "OPN"));

				for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
					for(int col = 0; col < lvlGridIndex.mapSize_col; col++){
						if((lvlGridIndex.vipCoords[1][0] == row) && (lvlGridIndex.vipCoords[1][1] == col)){
							map_row[row][col] = "-4";
							cellStates[row][col] = "TGT";
						}
					}
				}

				System.out.println(" Target Coords: [" + lvlGridIndex.vipCoords[1][0] + "][" + lvlGridIndex.vipCoords[1][1] + "]");
				break;

			case 2: // Current index = Origin coords
				lvlGridIndex.vipCoords[2][0] = lvlGridIndex.vipCoords[0][0];
				lvlGridIndex.vipCoords[2][1] = lvlGridIndex.vipCoords[0][1];
				System.out.println("Current Coords: [" + lvlGridIndex.vipCoords[2][0] + "][" + lvlGridIndex.vipCoords[2][1] + "]");
				break;
		}
	}

	public static void aStarAlgorithm(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][]){
		while(!((lvlGridIndex.vipCoords[2][0] == lvlGridIndex.vipCoords[1][0]) && (lvlGridIndex.vipCoords[2][1] == lvlGridIndex.vipCoords[1][1]))){

			System.out.println("\n[]█████████████████████████[ Turn #" + (lvlGridIndex.turnCheck) + " ]█████████████████████████[]");
			calculateGCost(lvlGridIndex, map_row, cellStates);
			calculateHAndFCost(lvlGridIndex, map_row, cellStates);
			int lowestFCost = findLowestFCost(lvlGridIndex, map_row, cellStates);
			moveCurrentIndex(lvlGridIndex, map_row, cellStates, lowestFCost);
			lowestFCost = 0;

			if(isAdjacentToTarget(lvlGridIndex, map_row, cellStates, lowestFCost)){
				lvlGridIndex.vipCoords[2][0] = lvlGridIndex.vipCoords[1][0];
				lvlGridIndex.vipCoords[2][1] = lvlGridIndex.vipCoords[1][1];
			}


			printMap(map_row, cellStates);
			System.out.println(" Origin Coords: [" + lvlGridIndex.vipCoords[0][0] + "][" + lvlGridIndex.vipCoords[0][1] + "]");
			System.out.println(" Target Coords: [" + lvlGridIndex.vipCoords[1][0] + "][" + lvlGridIndex.vipCoords[1][1] + "]");
			System.out.println("Current Coords: [" + lvlGridIndex.vipCoords[2][0] + "][" + lvlGridIndex.vipCoords[2][1] + "]");
			System.out.println("End of Turn: [#" + lvlGridIndex.turnCheck + "]");
			lvlGridIndex.turnCheck++;

			//break;
		}
	}

	public static void calculateGCost(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][]){
		int gCost = 0;

		for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
			for(int col = 0; col < lvlGridIndex.mapSize_col; col++){

				// check if cell is open, else return
				if(cellStates[row][col] == "OPN"){

					int origin_col = lvlGridIndex.vipCoords[0][1];
					int origin_row = lvlGridIndex.vipCoords[0][0];

					// check if cell is not in the col/row of target
					if((row != origin_row) && (col != origin_col)){
						
						if((Math.abs(origin_col - col)) == (Math.abs(origin_row) - row)){
							gCost = (Math.abs(origin_col - col) * 14);
						} else if((origin_col - col) > (origin_row - row)) {
							gCost = (Math.abs(origin_col - col) * 10) + (Math.abs(origin_row - row) * 4);
						} else if((origin_col - col) < (origin_row - row)) {
							gCost = ((Math.abs(origin_row - col) * 4) + 10) + (Math.abs(origin_col - row) * 10);
						}

					// check if cell is not in the row of target but in col
					} else if((row != origin_row) && (col == origin_col)){
						gCost = ((Math.abs(origin_row - row)) * 10);

					// check if cell is not in the col of target but in row
					} else if((row == origin_row) && (col != origin_col)){
						gCost = ((Math.abs(origin_col - col)) * 10);
					}

					map_row[row][col] = String.valueOf(gCost);
				}
			}
		}
	}

	public static void calculateHAndFCost(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][]){
		int hCost = 0;

		for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
			for(int col = 0; col < lvlGridIndex.mapSize_col; col++){
				if (cellStates[row][col] == "OPN"){
					int target_col = lvlGridIndex.vipCoords[1][1];
					int target_row = lvlGridIndex.vipCoords[1][0];

					if((row != target_row) && (col != target_col)){
						
						// diagonal
						if((Math.abs(target_col - col)) == (Math.abs(target_row) - row)){
							hCost = (Math.abs(target_col - col) * 14);

						} else if((target_col - col) > (target_row - row)){
							System.out.println("Cell [ " + row + ", " + col + "]: ");
							// System.out.print("tCol - col = " + (target_col - col));
							// System.out.println(" || tRow - row = " + (target_row - row));
							hCost = (Math.abs(target_col - col) * 10) + (Math.abs(target_row - row) * 4);
						} else if((target_col - col) < (target_row - row)){
							hCost = (Math.abs(target_row - col) * 4) + (Math.abs(target_col - row) * 10);
						}

					// check if cell is not in the row of target but in col
					} else if((row != target_row) && (col == target_col)){
						hCost = ((Math.abs(target_row - row)) * 10);

					// check if cell is not in the col of target but in row
					} else if((row == target_row) && (col != target_col)){
						hCost = ((Math.abs(target_col - col)) * 10);
					}

					map_row[row][col] = String.valueOf(Integer.parseInt(map_row[row][col]) + hCost);
				}
			}
		}
	}

	public static int findLowestFCost(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][]){
		int lowestFCost = 0;

		for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
			for(int col = 0; col < lvlGridIndex.mapSize_col; col++){
				if((lowestFCost == 0) && (cellStates[row][col] == "OPN")){
					lowestFCost = Integer.parseInt(map_row[row][col]);
				} else if((Integer.parseInt(map_row[row][col]) > 0) && (Integer.parseInt(map_row[row][col]) < lowestFCost)){
					lowestFCost = Integer.parseInt(map_row[row][col]);
				}
			}
		}

		return lowestFCost;
	}

	public static void moveCurrentIndex(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][], int lowestFCost){
		boolean hasFoundlowestFScoreCell = false;


		for(int row = 0; row < lvlGridIndex.mapSize_row; row++){
			for(int col = 0; col < lvlGridIndex.mapSize_col; col++){
				if((cellStates[row][col] == "OPN") && (Integer.parseInt(map_row[row][col]) == lowestFCost) && (hasFoundlowestFScoreCell == false)){
					hasFoundlowestFScoreCell = true;
					cellStates[row][col] = "CLS";
					lvlGridIndex.vipCoords[2][0] = row;
					lvlGridIndex.vipCoords[2][1] = col;

					for(int adj_row = -1; adj_row < 2; adj_row++){
						for(int adj_col = -1; adj_col < 2; adj_col++){
							if((map_row[row + adj_row][col + adj_col] == "-1")){
								switch(adj_row){
									case -1:
										if(cellStates[row + adj_row][col + adj_col] != "CLS"){
											cellStates[row + adj_row][col + adj_col] = "OPN";
										}
										break;
									case 0:
										if(adj_col == 0){
											cellStates[row][col] = "CLS";
										} else if(cellStates[row + adj_row][col + adj_col] != "CLS"){
											cellStates[row + adj_row][col + adj_col] = "OPN";
										}
										break;
									case 1:
										if(cellStates[row + adj_row][col + adj_col] != "CLS"){
											cellStates[row + adj_row][col + adj_col] = "OPN";
										}
										break;
								}
							}
						}
					}

					//break;
				}
			}
		}
	}

	public static boolean isAdjacentToTarget(aStarPathfinding2 lvlGridIndex, String map_row[][], String cellStates[][], int lowestFCost){
		boolean aa = false;

		for(int adj_row = -1; adj_row < 2; adj_row++){
			for(int adj_col = -1; adj_col < 2; adj_col++){
				switch(adj_row){
					case -1:
						if(((lvlGridIndex.vipCoords[1][0] + adj_row) == lvlGridIndex.vipCoords[2][0]) && ((lvlGridIndex.vipCoords[1][1] + adj_col) == lvlGridIndex.vipCoords[2][1])){
							aa = true;
						}
						break;
					case 0:
						if(((lvlGridIndex.vipCoords[1][0] + adj_row) == lvlGridIndex.vipCoords[2][0]) && ((lvlGridIndex.vipCoords[1][1] + adj_col) == lvlGridIndex.vipCoords[2][1])){
							aa = true;
						}
						break;
					case 1:	
						if(((lvlGridIndex.vipCoords[1][0] + adj_row) == lvlGridIndex.vipCoords[2][0]) && ((lvlGridIndex.vipCoords[1][1] + adj_col) == lvlGridIndex.vipCoords[2][1])){
							aa = true;
						}
						break;
				}
			}
		}

		return aa;
	}
}