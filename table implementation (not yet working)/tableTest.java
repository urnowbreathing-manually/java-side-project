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

	public static void main(String[] args) {

		JFrame mainFrame = new JFrame("Table Test");

		JTable levelGrid = new JTable(9, 12);
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
				playerMovement(1, levelGrid, row, col, lastMove);
				
			}
		});

		//Down button
		JButton down_button = new JButton("S");
		down_button.setBounds(50, 50, 50, 50);
		down_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(2, levelGrid, row, col, lastMove);
				
			}
		});

		//Left button
		JButton left_button = new JButton("A");
		left_button.setBounds(0, 50, 50, 50);
		left_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(3, levelGrid, row, col, lastMove);
				
			}
		});

		//Right button
		JButton right_button = new JButton("D");
		right_button.setBounds(100, 50, 50, 50);
		right_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				playerMovement(4, levelGrid, row, col, lastMove);
				
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

		mapGeneration(levelGrid);

		if(lastMove.toString() == "UP"){
			row--;
		}

	}

	//Value stored in each cell determines what's on it
	//0 = air, 1 = wall, 10 = player
	//100 = enemy, 1000 = exit
	//110 = enemy hits player
	//1010 = player gets to exit 
	public static void mapGeneration(JTable levelGrid){
		for(int i = 0; i <= 8; i++){
			for(int j = 0; j <= 11; j++){
				levelGrid.setValueAt(0, i, j);
			}
		}

		levelGrid.setValueAt(10, 4, 5);

	}


	public static void playerMovement(int moveKeys, JTable levelGrid, int row, int col, JTextField lastMove){
		switch (moveKeys){
			case 1: //Up button
				levelGrid.setValueAt(0, row, col);
				levelGrid.setValueAt(10, row - 1, col);
				System.out.println("Row: " + row + " Col: " + col);
				lastMove.setText("UP");
				break;

			case 2: //Down button
				levelGrid.setValueAt(0, row, col);
				levelGrid.setValueAt(10, row + 1, col);
				System.out.println("Row: " + row + " Col: " + col);
				lastMove.setText("DOWN");
				break;

			case 3: //Left button
				levelGrid.setValueAt(0, row, col);
				levelGrid.setValueAt(10, row, col - 1);
				System.out.println("Row: " + row + " Col: " + col);
				lastMove.setText("LEFT");			
				break;

			case 4: //Right button
				levelGrid.setValueAt(0, row, col);
				levelGrid.setValueAt(10, row, col + 1);
				System.out.println("Row: " + row + " Col: " + col);
				lastMove.setText("RIGHT");
				break;
			}
	}

	// public static void enemyMovement(JTable levelGrid){

	// }

	// public static void mapSearch(JTable levelGrid){
		
	// }

}