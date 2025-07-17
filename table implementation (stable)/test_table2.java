import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.Vector;

public class tableTest{

	// Current player coords
	int playerPosRow = 4;
	int playerPosCol = 5;

	int currentEnemyMovement = 0;

	// handles player movement after the movement buttons were pressed
	public static void playerMovement(int moveKeys, JTable lvlGrid, JTextField plm_read, tableTest lvlGridIndex, JTextField ppr_read, JTextField ppc_read){
		switch (moveKeys){
			case 1: //Up button
				if(lvlGridIndex.playerPosRow > 0){
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosRow -= 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					plm_read.setText("UP");
					ppr_read.setText("ROW: " + lvlGridIndex.playerPosRow);
				}
				break;

			case 2: //Down button
				if(lvlGridIndex.playerPosRow < 8){	
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosRow += 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					plm_read.setText("DOWN");
					ppr_read.setText("ROW: " + lvlGridIndex.playerPosRow);
				}
				break;

			case 3: //Left button
				if(lvlGridIndex.playerPosCol > 0){
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosCol -= 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					plm_read.setText("LEFT");	
					ppc_read.setText("COL: " + lvlGridIndex.playerPosCol);
				}
					break;

			case 4: //Right button
				if(lvlGridIndex.playerPosCol < 11){
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosCol += 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					plm_read.setText("RIGHT");
					ppc_read.setText("COL: " + lvlGridIndex.playerPosCol);
				}
				break;
			}
	}

	// Runs after playerMovement() is done running
	public static void enemyMovement(JTable lvlGrid, tableTest lvlGridIndex, String row[][], String col[], int enemyAmount, int enemyPos[][], tableTest currentEnemyMovement){
		Random randomizer1 = new Random();
		int cycleThroughEnemies = 0;
		int enemyRow = 0;
		int enemyCol = 0;
		int enemyMovementDelay = 1;

		//System.out.println("CEM: " + currentEnemyMovement.currentEnemyMovement + " EMD: " + enemyMovementDelay);

		for (int i = 0; i < enemyPos.length; i++) {
			for (int d = 0; d < enemyPos.length; d++) {
				lvlGrid.setValueAt("0", enemyPos[i][d], enemyPos[i][d+1]);
			}
		}

		if(currentEnemyMovement.currentEnemyMovement == enemyMovementDelay){
			//System.out.println("CEM: " + currentEnemyMovement.currentEnemyMovement + " EMD: " + enemyMovementDelay);
			
			for(int[] eachEnemy : enemyPos){
				lvlGrid.setValueAt("0", eachEnemy[0], eachEnemy[1]);
				enemyRow = eachEnemy[0];
				enemyCol = eachEnemy[1];

				if((enemyRow != lvlGridIndex.playerPosRow) && (enemyCol != lvlGridIndex.playerPosCol)){
					int chooseDirection = randomizer1.nextInt(2);

					if(chooseDirection == 0){
						if(enemyRow < lvlGridIndex.playerPosRow){
							enemyRow++;
						} else{
							enemyRow--;
						}
					} else{
						if(enemyCol < lvlGridIndex.playerPosCol){
							enemyCol++;
						} else{
							enemyCol--;
						}
					}
				} else if((enemyRow != lvlGridIndex.playerPosRow) && (enemyCol == lvlGridIndex.playerPosCol)){
					if(enemyRow < lvlGridIndex.playerPosRow){
						enemyRow++;
					} else{
						enemyRow--;
					}
				} else if((enemyRow == lvlGridIndex.playerPosRow) && (enemyCol != lvlGridIndex.playerPosCol)){
					if(enemyCol < lvlGridIndex.playerPosCol){
							enemyCol++;
						} else{
							enemyCol--;
						}
				}

				for(int[] stackingCheck : enemyPos){
					if((stackingCheck[0] == enemyRow) && (stackingCheck[1] == enemyCol)){
						int chooseDirection1 = randomizer1.nextInt(2);
						if(chooseDirection1 == 0){
							int chooseDirection2 = randomizer1.nextInt(2);
							if(chooseDirection2 == 0){
								enemyRow--;
							} else{
								enemyRow++;
							}
						} else{
							int chooseDirection3 = randomizer1.nextInt(2);
							if(chooseDirection3 == 0){
								enemyCol--;
							} else{
								enemyCol++;
							}
						}
						
					}

				}

				enemyRow = (enemyRow < 0) ? enemyRow += 2 : enemyRow;
				enemyRow = (enemyRow > 8) ? enemyRow -= 2 : enemyRow;
				enemyCol = (enemyCol < 0) ? enemyCol += 2 : enemyCol;
				enemyCol = (enemyCol > 11) ? enemyCol -= 2 : enemyCol;



				eachEnemy[0] = enemyRow;
				eachEnemy[1] = enemyCol;

				lvlGrid.setValueAt("ENEMY", eachEnemy[0], eachEnemy[1]);
				currentEnemyMovement.currentEnemyMovement = 0;
			}
		} else{
			currentEnemyMovement.currentEnemyMovement++;
		}
		
		//System.out.println("CEM: " + currentEnemyMovement.currentEnemyMovement + " EMD: " + enemyMovementDelay);
	}

	public static void playerDeathCheck(JTable lvlGrid, tableTest lvlGridIndex, int enemyPos[][]){

	}

	//Main Method
	public static void main(String[] args) {
		String map_row[][] = 
		{{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
		{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"}};

		String map_col[] = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};

		int enemyAmount = 10;
		int[][] enemyPos = new int[enemyAmount][2];
		tableTest currentEnemyMovement = new tableTest();
		

		//Vector<Vector<Integer>> enemy_pos = new Vector<Vector<Integer>>();

		// Object for navigating the lvlGrid coords
		tableTest lvlGridIndex = new tableTest();
		
		// Main JFrame containing everything
		JFrame mainFrame = new JFrame("Table Test");

		// The JTable containing the level grid
		JTable lvlGrid = new JTable(map_row.length, map_col.length);
			lvlGrid.setBounds(0, 0, 600, 450);
			lvlGrid.setRowHeight(50);
			lvlGrid.setBackground(Color.decode("#6F6F6F"));
			lvlGrid.setCellSelectionEnabled(false);
			lvlGrid.setFocusable(false);

		// Jpanel holding all the movement buttons
		JPanel buttonContainer = new JPanel();
			buttonContainer.setBounds(225, 450, 150, 100);
			buttonContainer.setLayout(null);
			buttonContainer.setBackground(Color.decode("#C7C7C7"));

		// JPanel holding debug info, such as the row/col of the player, and the last move done
		JPanel debugInfo = new JPanel();
			debugInfo.setBounds(380, 455, 215, 90);
			debugInfo.setLayout(null);
			debugInfo.setBackground(Color.decode("#C7C7C7"));

		// JTextField that saves the player's position row (PPR) as text
		JTextField ppr_read = new JTextField("ROW: 4");
			ppr_read.setBounds(5, 5, 65, 20);

		// JTextField that saves the player's position col (PPC) as text
		JTextField ppc_read = new JTextField("COL: 5");
			ppc_read.setBounds(75, 5, 65, 20);

		// JTextField that saves the player's last move (PLM) as text
		JTextField plm_read = new JTextField();
			plm_read.setBounds(5, 30, 65, 20);

		// Up button
		JButton up_button = new JButton("W");
			up_button.setBounds(50, 0, 50, 50);
			up_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(1, lvlGrid, plm_read, lvlGridIndex, ppr_read, ppc_read);
					enemyMovement(lvlGrid, lvlGridIndex, map_row, map_col, enemyAmount, enemyPos, currentEnemyMovement);
					playerDeathCheck(lvlGrid, lvlGridIndex, enemyPos);
					
				}
			});

		// Down button
		JButton down_button = new JButton("S");
			down_button.setBounds(50, 50, 50, 50);
			down_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(2, lvlGrid, plm_read, lvlGridIndex, ppr_read, ppc_read);
					enemyMovement(lvlGrid, lvlGridIndex, map_row, map_col, enemyAmount, enemyPos, currentEnemyMovement);
					playerDeathCheck(lvlGrid, lvlGridIndex, enemyPos);
				}
			});

		// Left button
		JButton left_button = new JButton("A");
			left_button.setBounds(0, 50, 50, 50);
			left_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(3, lvlGrid, plm_read, lvlGridIndex, ppr_read, ppc_read);
					enemyMovement(lvlGrid, lvlGridIndex, map_row, map_col, enemyAmount, enemyPos, currentEnemyMovement);
					playerDeathCheck(lvlGrid, lvlGridIndex, enemyPos);
				}
			});

		// Right button
		JButton right_button = new JButton("D");
			right_button.setBounds(100, 50, 50, 50);
			right_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(4, lvlGrid, plm_read, lvlGridIndex, ppr_read, ppc_read);
					enemyMovement(lvlGrid, lvlGridIndex, map_row, map_col, enemyAmount, enemyPos, currentEnemyMovement);
					playerDeathCheck(lvlGrid, lvlGridIndex, enemyPos);
				}
			});

		mainFrame.add(lvlGrid);
		mainFrame.add(buttonContainer);
			buttonContainer.add(up_button);
			buttonContainer.add(down_button);
			buttonContainer.add(left_button);
			buttonContainer.add(right_button);
		mainFrame.add(debugInfo);
			debugInfo.add(ppr_read);
			debugInfo.add(ppc_read);
			debugInfo.add(plm_read);

		// Main Frame Settings
		mainFrame.setSize(600, 590);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.decode("#404040"));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Populates the JTable lvlGrid with data
		mapGeneration(lvlGrid, lvlGridIndex, map_row, map_col, enemyAmount, enemyPos);

	}

	// Populates the JTable lvlGrid with data
	public static void mapGeneration(JTable lvlGrid, tableTest lvlGridIndex, String row[][], String col[], int enemyAmount, int enemyPos[][]){
		for(int i = 0; i < row.length; i++){
			for(int j = 0; j < col.length; j++){
				lvlGrid.setValueAt(row[i][j], i, j);

				if (i == lvlGridIndex.playerPosRow && j == lvlGridIndex.playerPosCol) {
					lvlGrid.setValueAt("PLAYER", i, j);
				}
			}
		}



		Random randomizer = new Random();
		int e_row = 0;
		int e_col = 0;

		for(int k = 0; k < enemyAmount; k++){

			do{
				e_row = randomizer.nextInt(9);
				e_col = randomizer.nextInt(12);
			} while(lvlGrid.getValueAt(e_row, e_col).toString() == "WALL");

			Vector<Integer> pos = new Vector<Integer>();
			pos.add(e_row);
			pos.add(e_col);
		}

	}
}