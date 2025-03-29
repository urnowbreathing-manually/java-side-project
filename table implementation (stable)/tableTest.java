import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;

public class tableTest{

	// Current player coords
	int playerPosRow = 4;
	int playerPosCol = 5;

	// handles player movement after the movement buttons were pressed
	public static void playerMovement(int moveKeys, JTable lvlGrid, JTextField lastMove, tableTest lvlGridIndex){
		switch (moveKeys){
			case 1: //Up button
				if(lvlGridIndex.playerPosRow > 0){
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosRow -= 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("UP");
				}
				break;

			case 2: //Down button
				if(lvlGridIndex.playerPosRow < 8){	
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosRow += 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("DOWN");
				}
				break;

			case 3: //Left button
				if(lvlGridIndex.playerPosCol > 0){
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosCol -= 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("LEFT");			
				}
					break;

			case 4: //Right button
				if(lvlGridIndex.playerPosCol < 11){
					lvlGrid.setValueAt("0", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					lvlGridIndex.playerPosCol += 1;
					lvlGrid.setValueAt("PLAYER", lvlGridIndex.playerPosRow, lvlGridIndex.playerPosCol);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("RIGHT");
				}
				break;
			}
	}

	// Runs after playerMovement() is done running
	public static void enemyMovement(){

	}

	//Main Method
	public static void main(String[] args) {
		String map_row[][] = 
{{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "1", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"}};

	String map_col[] = {"0", "0", "0", "0", "0", "0", "11", "0", "0", "0", "0", "0"};

		// Main JFrame containing everything
		JFrame mainFrame = new JFrame("Table Test");

		// Object for navigating the lvlGrid coords
		tableTest lvlGridIndex = new tableTest();

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

		// JTextField that saves the last movement button pressed as text
		JTextField lastMove = new JTextField();
			lastMove.setBounds(5, 5, 65, 20);

		// Up button
		JButton up_button = new JButton("W");
			up_button.setBounds(50, 0, 50, 50);
			up_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(1, lvlGrid, lastMove, lvlGridIndex);
					// enemyMovement(lvlGrid, );
					
				}
			});

		// Down button
		JButton down_button = new JButton("S");
			down_button.setBounds(50, 50, 50, 50);
			down_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(2, lvlGrid, lastMove, lvlGridIndex);
					// enemyMovement(lvlGrid, );
				}
			});

		// Left button
		JButton left_button = new JButton("A");
			left_button.setBounds(0, 50, 50, 50);
			left_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(3, lvlGrid, lastMove, lvlGridIndex);
					// enemyMovement(lvlGrid, );
				}
			});

		// Right button
		JButton right_button = new JButton("D");
			right_button.setBounds(100, 50, 50, 50);
			right_button.addActionListener( new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					playerMovement(4, lvlGrid, lastMove, lvlGridIndex);
					// enemyMovement(lvlGrid, );
				}
			});


		mainFrame.add(lvlGrid);
		mainFrame.add(buttonContainer);
			buttonContainer.add(up_button);
			buttonContainer.add(down_button);
			buttonContainer.add(left_button);
			buttonContainer.add(right_button);
		mainFrame.add(debugInfo);
			debugInfo.add(lastMove);
			// debugInfo.add(pRow);
			// debugInfo.add(pCol);
			// debugInfo.add(eRow);
			// debugInfo.add(eCol);

		// Main Frame Settings
		mainFrame.setSize(600, 590);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.decode("#404040"));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Populates the JTable lvlGrid with data
		mapGeneration(lvlGrid, lvlGridIndex, map_row, map_col);

	}
/*
	Value stored in each cell determines what's on it
	0 = air, 1 = wall, 10 = player
	100 = enemy, 1000 = exit
	110 = enemy hits player
	1010 = player gets to exit 
*/
	// Populates the JTable lvlGrid with data
	public static void mapGeneration(JTable lvlGrid, tableTest lvlGridIndex, String row[][], String col[]){
		for(int i = 0; i < row.length; i++){
			for(int j = 0; j < col.length; j++){
				lvlGrid.setValueAt("0", i, j);

				if (i == lvlGridIndex.playerPosRow && j == lvlGridIndex.playerPosCol) {
					lvlGrid.setValueAt("PLAYER", i, j);
				}
			}
		}

		Random randomizer = new Random();

		for(int k = 0; k < 3; k++){
		int e_row = randomizer.nextInt(8);
		int e_col = randomizer.nextInt(10);
		lvlGrid.setValueAt("ENEMY", e_row, e_col);
		}



	}

}