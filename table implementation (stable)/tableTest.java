import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;

public class tableTest{

	int rowNonStatic = 4;
	int colNonStatic = 5;

	public static void playerMovement(int moveKeys, JTable levelGrid, JTextField lastMove, tableTest pos){
		switch (moveKeys){
			case 1: //Up button
				if(pos.rowNonStatic > 0){
					levelGrid.setValueAt("0", pos.rowNonStatic, pos.colNonStatic);
					pos.rowNonStatic -= 1;
					levelGrid.setValueAt("PLAYER", pos.rowNonStatic, pos.colNonStatic);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("UP");
				}
				break;

			case 2: //Down button
				if(pos.rowNonStatic < 8){	
					levelGrid.setValueAt("0", pos.rowNonStatic, pos.colNonStatic);
					pos.rowNonStatic += 1;
					levelGrid.setValueAt("PLAYER", pos.rowNonStatic, pos.colNonStatic);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("DOWN");
				}
				break;

			case 3: //Left button
				if(pos.colNonStatic > 0){
					levelGrid.setValueAt("0", pos.rowNonStatic, pos.colNonStatic);
					pos.colNonStatic -= 1;
					levelGrid.setValueAt("PLAYER", pos.rowNonStatic, pos.colNonStatic);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("LEFT");			
				}
					break;

			case 4: //Right button
				if(pos.colNonStatic < 11){
					levelGrid.setValueAt("0", pos.rowNonStatic, pos.colNonStatic);
					pos.colNonStatic += 1;
					levelGrid.setValueAt("PLAYER", pos.rowNonStatic, pos.colNonStatic);
					// System.out.println("Row: " + row + " Col: " + col);
					lastMove.setText("RIGHT");
				}
				break;
			}
	}

	public static void enemyMovement(){

	}

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

		JFrame mainFrame = new JFrame("Table Test");

		tableTest rowStatic = new tableTest();
		tableTest colStatic = new tableTest();

		int row = rowStatic.rowNonStatic;
		int col = colStatic.colNonStatic;

		JTable levelGrid = new JTable(map_row.length, map_col.length);
		levelGrid.setBounds(0, 0, 600, 450);
		levelGrid.setRowHeight(50);
		levelGrid.setBackground(Color.decode("#6F6F6F"));
		levelGrid.setCellSelectionEnabled(false);
		levelGrid.setFocusable(false);

		JPanel buttonContainer = new JPanel();
		buttonContainer.setBounds(225, 450, 150, 100);
		buttonContainer.setLayout(null);
		buttonContainer.setBackground(Color.decode("#C7C7C7"));

		JTextField lastMove = new JTextField();
		lastMove.setBounds(5, 5, 65, 20);

		//Up button
		JButton up_button = new JButton("W");
		up_button.setBounds(50, 0, 50, 50);
		up_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(1, levelGrid, lastMove, rowStatic);
				enemyMovement();
				
			}
		});

		//Down button
		JButton down_button = new JButton("S");
		down_button.setBounds(50, 50, 50, 50);
		down_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(2, levelGrid, lastMove, rowStatic);
				enemyMovement();
			}
		});

		//Left button
		JButton left_button = new JButton("A");
		left_button.setBounds(0, 50, 50, 50);
		left_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(3, levelGrid, lastMove, rowStatic);
				enemyMovement();
			}
		});

		//Right button
		JButton right_button = new JButton("D");
		right_button.setBounds(100, 50, 50, 50);
		right_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(4, levelGrid, lastMove, rowStatic);
				enemyMovement();
			}
		});

		JPanel debugInfo = new JPanel();
		debugInfo.setBounds(380, 455, 215, 90);
		debugInfo.setLayout(null);
		debugInfo.setBackground(Color.decode("#C7C7C7"));

		mainFrame.add(levelGrid);
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

		mainFrame.setSize(600, 590);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.decode("#404040"));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mapGeneration(levelGrid, rowStatic, map_row, map_col);

	}

	//Value stored in each cell determines what's on it
	//0 = air, 1 = wall, 10 = player
	//100 = enemy, 1000 = exit
	//110 = enemy hits player
	//1010 = player gets to exit 
	public static void mapGeneration(JTable levelGrid, tableTest pos, String row[][], String col[]){
		for(int i = 0; i < row.length; i++){
			for(int j = 0; j < col.length; j++){
				levelGrid.setValueAt("0", i, j);

				if (i == pos.rowNonStatic && j == pos.colNonStatic) {
					levelGrid.setValueAt("PLAYER", i, j);
				}
			}
		}

		Random randomizer = new Random();

		for(int k = 0; k < 3; k++){
		int e_row = randomizer.nextInt(8);
		int e_col = randomizer.nextInt(10);
		levelGrid.setValueAt("ENEMY", e_row, e_col);
		}



	}



	// public static void enemyMovement(JTable levelGrid){

	// }

	// public static void mapSearch(JTable levelGrid){
		
	// }

}