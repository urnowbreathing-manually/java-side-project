import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;

public class aStarPathfinding{

	int[] originCoords = {4, 5};
	int[] targetCoords = {5, 4};
	int[] currentIndex = {originCoords[0], originCoords[1]};

	public static void randomCoords(aStarPathfinding lvlGridIndex){
		Random randomizer = new Random();

		int origin_rand_row = randomizer.nextInt(7) + 1;
		int origin_rand_col = randomizer.nextInt(10) + 1;
		int target_rand_row = randomizer.nextInt(7) + 1;
		int target_rand_col = randomizer.nextInt(10) + 1;

		lvlGridIndex.originCoords[0] = origin_rand_row;
		lvlGridIndex.originCoords[1] = origin_rand_col;
		lvlGridIndex.targetCoords[0] = target_rand_row;
		lvlGridIndex.targetCoords[1] = target_rand_col;

		System.out.println("originCoords = [" + lvlGridIndex.originCoords[0] + ", " + lvlGridIndex.originCoords[1] + "]");
		System.out.println("targetCoords = [" + lvlGridIndex.targetCoords[0] + ", " + lvlGridIndex.targetCoords[1] + "]");
	}

	public static void main(String[] args) {
		String map_row[][] = 
			{{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "},
			{"   ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "   "},
			{"   ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "   "},
			{"   ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "   "},
			{"   ", " ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", " ", "   "},
			{"   ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "   "},
			{"   ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "   "},
			{"   ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "   "},
			{"   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   "}};

		String map_col[] =
			{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};

		aStarPathfinding lvlGridIndex = new aStarPathfinding();

		JFrame mainFrame = new JFrame("A* Pathfinding Test");

		JTable lvlGrid = new JTable(map_row.length, map_col.length);
			lvlGrid.setBounds(0, 0, 600, 450);
			lvlGrid.setRowHeight(50);
			lvlGrid.setBackground(Color.decode("#6F6F6F"));
			lvlGrid.setCellSelectionEnabled(false);
			lvlGrid.setFocusable(false);

		randomCoords(lvlGridIndex);

		JButton iterate = new JButton("ITERATE");
			iterate.setBounds(250, 475, 100, 50);
			iterate.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					aStarAlgorithm(lvlGrid, lvlGridIndex,map_row, map_col);
				}
			});

		mainFrame.add(lvlGrid);
		mainFrame.add(iterate);

		mainFrame.setSize(600, 590);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.decode("#e2e2e2"));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mapGeneration(lvlGrid, lvlGridIndex,map_row, map_col);
	}

	public static void mapGeneration(JTable lvlGrid, aStarPathfinding lvlGridIndex, String row[][], String col[]){
		for(int rowIndex = 0; rowIndex < row.length; rowIndex++){
			for(int colIndex = 0; colIndex < col.length; colIndex++){
				lvlGrid.setValueAt(row[rowIndex][colIndex], rowIndex, colIndex);

				if (rowIndex == lvlGridIndex.originCoords[0] && colIndex == lvlGridIndex.originCoords[1]){
					lvlGrid.setValueAt("ORIGIN", rowIndex, colIndex);
				}

				if(rowIndex == lvlGridIndex.targetCoords[0] && colIndex == lvlGridIndex.targetCoords[1]){
					lvlGrid.setValueAt("TARGET", rowIndex, colIndex);
				}
			}
		}
	}

	public static void aStarAlgorithm(JTable lvlGrid, aStarPathfinding lvlGridIndex, String map_row[][], String map_col[]){
		String[][] neighboringCells = new String[3][3];
		int[] neighborCellCoords = new int[2];

		int gCost = 0; // Distance of the given cell from the origin cell
		int hCost = 0; // Distance of the given cell from the target cell
 
		calculateAllFCost(lvlGrid, lvlGridIndex, neighborCellCoords, gCost, hCost, neighboringCells, map_row, map_col);
		lvlGrid.setValueAt("ORIGIN", lvlGridIndex.originCoords[0], lvlGridIndex.originCoords[1]);
		map_row[lvlGridIndex.originCoords[0]][lvlGridIndex.originCoords[1]] = "CLOSED";
		neighboringCells[1][1] = "0";
		moveCurrentIndex(lvlGrid, lvlGridIndex, neighboringCells, map_row, map_col);

	}

	public static void calculateAllFCost(JTable lvlGrid, aStarPathfinding lvlGridIndex, int neighborCellCoords[], int gCost, int hCost, String neighboringCells[][], String map_row[][], String map_col[]){
		for(int i = 0; i < 3; i++){
			for(int j = -1; j < 2; j++){
				switch(i){
					case 0:

						// Setting NeighborCellCoords to one of the 3 neighboringCells in the first row depending on the value of j
						neighborCellCoords[0] = lvlGridIndex.currentIndex[0] - 1;
						neighborCellCoords[1] = lvlGridIndex.currentIndex[1] + j;

						// Calculate the fCost; gCost + hCost of the current cell
						calculateIndividualFCost(lvlGridIndex, gCost, hCost, neighboringCells, neighborCellCoords, i, j);

						// Declaring the cell to be evaluated
						map_row[lvlGridIndex.currentIndex[0] - 1][lvlGridIndex.currentIndex[1] + j] = "OPEN";
						// Applying the fCost to the JTable lvlGrid to be displayed
						lvlGrid.setValueAt(neighboringCells[i][j + 1], (lvlGridIndex.currentIndex[0] - 1), (lvlGridIndex.currentIndex[1] + j));
						break;

					case 1:
						neighborCellCoords[0] = lvlGridIndex.currentIndex[0] + 0;
						neighborCellCoords[1] = lvlGridIndex.currentIndex[1] + j;

						calculateIndividualFCost(lvlGridIndex, gCost, hCost, neighboringCells, neighborCellCoords, i, j);

						map_row[lvlGridIndex.currentIndex[0] + 0][lvlGridIndex.currentIndex[1] + j] = "OPEN";
						lvlGrid.setValueAt(neighboringCells[i][j + 1], (lvlGridIndex.currentIndex[0] + 0), (lvlGridIndex.currentIndex[1] + j));
						break;

					case 2:
						neighborCellCoords[0] = lvlGridIndex.currentIndex[0] + 1;
						neighborCellCoords[1] = lvlGridIndex.currentIndex[1] + j;

						calculateIndividualFCost(lvlGridIndex, gCost, hCost, neighboringCells, neighborCellCoords, i, j);

						map_row[lvlGridIndex.currentIndex[0] + 1][lvlGridIndex.currentIndex[1] + j] = "OPEN";
						lvlGrid.setValueAt(neighboringCells[i][j + 1], (lvlGridIndex.currentIndex[0] + 1), (lvlGridIndex.currentIndex[1] + j));
						break;

				}
			}
		}
	}

	// Calculate the fCost; gCost + hCost of the current cell
	public static void calculateIndividualFCost(aStarPathfinding lvlGridIndex, int gCost, int hCost, String neighboringCells[][], int neighborCellCoords[], int i, int j){
		// Calculating the gCost; distance between the current cell and the origin cell
		gCost = calculateGCost(lvlGridIndex, neighborCellCoords, gCost);

		// Calculating the hCost; distance between the current cell and the target cell
		hCost = calculateHCost(lvlGridIndex, neighborCellCoords, hCost);

		// Applying the fCost (gCost + hCost) to the current cell
		neighboringCells[i][j + 1] = String.valueOf(gCost + hCost);
	}

	// Calculating the gCost; distance between the current cell and the origin cell
	public static int calculateGCost(aStarPathfinding lvlGridIndex, int neighborCellCoords[], int gCost){
		if((neighborCellCoords[0] != lvlGridIndex.originCoords[0]) && (neighborCellCoords[1] != lvlGridIndex.originCoords[1])){
			if(Math.abs(lvlGridIndex.originCoords[1] - neighborCellCoords[1]) > Math.abs(lvlGridIndex.originCoords[0] - neighborCellCoords[0])){
				gCost = (Math.abs(lvlGridIndex.originCoords[1] - neighborCellCoords[1]) * 10) + ((Math.abs(lvlGridIndex.originCoords[0] - neighborCellCoords[0])) * 4);
			} else{
				gCost = (Math.abs(lvlGridIndex.originCoords[0] - neighborCellCoords[0]) * 10) + ((Math.abs(lvlGridIndex.originCoords[1] - neighborCellCoords[1])) * 4);
			}
		} else if((neighborCellCoords[0] != lvlGridIndex.originCoords[0]) && (neighborCellCoords[1] == lvlGridIndex.originCoords[1])){
			gCost = ((Math.abs(lvlGridIndex.originCoords[0] - neighborCellCoords[0])) * 10);
		} else if((neighborCellCoords[0] == lvlGridIndex.originCoords[0]) && (neighborCellCoords[1] != lvlGridIndex.originCoords[1])){
			gCost = ((Math.abs(lvlGridIndex.originCoords[1] - neighborCellCoords[1])) * 10);
		}

		return gCost;
	}

	// Calculating the gCost; distance between the current cell and the target cell
	public static int calculateHCost(aStarPathfinding lvlGridIndex, int neighborCellCoords[], int hCost){
		if((neighborCellCoords[0] != lvlGridIndex.targetCoords[0]) && (neighborCellCoords[1] != lvlGridIndex.targetCoords[1])){ 
			if(Math.abs(lvlGridIndex.targetCoords[1] - neighborCellCoords[1]) > Math.abs(lvlGridIndex.targetCoords[0] - neighborCellCoords[0])){
				hCost = (Math.abs(lvlGridIndex.targetCoords[1] - neighborCellCoords[1]) * 10) + ((Math.abs(lvlGridIndex.targetCoords[0] - neighborCellCoords[0])) * 4);
			} else{
				hCost = (Math.abs(lvlGridIndex.targetCoords[0] - neighborCellCoords[0]) * 10) + ((Math.abs(lvlGridIndex.targetCoords[1] - neighborCellCoords[1])) * 4);
			}
		} else if((neighborCellCoords[0] != lvlGridIndex.targetCoords[0]) && (neighborCellCoords[1] == lvlGridIndex.targetCoords[1])){
			hCost = ((Math.abs(lvlGridIndex.targetCoords[0] - neighborCellCoords[0])) * 10);
		} else if((neighborCellCoords[0] == lvlGridIndex.targetCoords[0]) && (neighborCellCoords[1] != lvlGridIndex.targetCoords[1])){
			hCost = ((Math.abs(lvlGridIndex.targetCoords[1] - neighborCellCoords[1])) * 10);
		}

		return hCost;
	}

	public static void moveCurrentIndex(JTable lvlGrid, aStarPathfinding lvlGridIndex, String neighboringCells[][], String map_row[][], String map_col[]){
		// Finds the lowest fCost cell in the entire map_row array
		int lowestFCost = findLowestFCost(lvlGrid, lvlGridIndex, neighboringCells, map_row, map_col);
		System.out.println("\n" + lowestFCost);

		closeLowestCostCell(lvlGrid, lvlGridIndex, map_row, map_col, lowestFCost);

	}

	// Finds the lowest fCost cell in the entire map_row array
	public static int findLowestFCost(JTable lvlGrid, aStarPathfinding lvlGridIndex, String neighboringCells[][], String map_row[][], String map_col[]){
		int lowestFCost = 0;
		for(int i = 0; i < map_row.length; i++){
			for(int j = 0; j < map_col.length; j++){
				//System.out.print(lvlGrid.getValueAt(i, j).toString() + " ");
				if((lvlGrid.getValueAt(i, j).toString() != "   ") && (lvlGrid.getValueAt(i, j).toString() != " ") && (lvlGrid.getValueAt(i, j).toString() != "ORIGIN") && (lvlGrid.getValueAt(i, j).toString() != "TARGET") && (lvlGrid.getValueAt(i, j).toString() != "OPEN") && (lvlGrid.getValueAt(i, j).toString() != "CLOSED")){
					if(lowestFCost == 0){
						lowestFCost = Integer.parseInt(lvlGrid.getValueAt(i, j).toString());
					} else if((Integer.parseInt(lvlGrid.getValueAt(i, j).toString()) < lowestFCost) && (lowestFCost > 0)){
						lowestFCost = Integer.parseInt(lvlGrid.getValueAt(i, j).toString());
					}
				}
			}
		}
	
		return lowestFCost;
	}

	public static void closeLowestCostCell(JTable lvlGrid, aStarPathfinding lvlGridIndex, String map_row[][], String map_col[], int lowestFCost){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 12; j++){
				//System.out.print("[i:" + i + "j:" + j + "]" + lvlGrid.getValueAt(i, j).toString() + "\t");
				if((lvlGrid.getValueAt(i, j).toString() != "   ") && (lvlGrid.getValueAt(i, j).toString() != " ") && (lvlGrid.getValueAt(i, j).toString() != "ORIGIN") && (lvlGrid.getValueAt(i, j).toString() != "TARGET") && (lvlGrid.getValueAt(i, j).toString() != "OPEN") && (lvlGrid.getValueAt(i, j).toString() != "CLOSED")){
					if(Integer.parseInt(lvlGrid.getValueAt(i, j).toString()) == lowestFCost){
						lvlGridIndex.currentIndex[0] = i;
						lvlGridIndex.currentIndex[1] = j;
						map_row[i][j] = "CLOSED";
						break;
					}
				}
			}
			if((lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString() != "   ") && (lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString() != " ") && (lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString() != "ORIGIN") && (lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString() != "TARGET") && (lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString() != "OPEN") && (lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString() != "CLOSED")){
				if((map_row[lvlGridIndex.currentIndex[0]][lvlGridIndex.currentIndex[1]] == "CLOSED") && (Integer.parseInt(lvlGrid.getValueAt(lvlGridIndex.currentIndex[0], lvlGridIndex.currentIndex[1]).toString()) == lowestFCost)){
					break;
				}	
			}
		}
	}
}