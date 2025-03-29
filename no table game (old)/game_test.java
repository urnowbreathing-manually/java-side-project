//Do these imports need to have a specific arrangement?
//And which ones in here are needed and which ones aren't?

import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.util.Random;

public class game_test {
	//Method for displaying player and enemy coords
	// pbx/pby = Player Bounds x/y
	// ebx/eby = Enemy Bounds x/y
	public static void coordsDisp(JTextField pbx, JTextField pby, JTextField ebx, JTextField eby, JPanel player, JPanel enemy){
		pbx.setText("pbx: " + player.getX());
		pby.setText("pby: " + player.getY());
		ebx.setText("ebx: " + enemy.getX());
		eby.setText("eby: " + enemy.getY());

	}

	//Method defining emeny behaviour each turn 
	public static void enemyAI(JPanel player, JPanel enemy){
		if(enemy.getX() < player.getX()){
			enemy.setBounds(enemy.getX() + 50, enemy.getY(), 50, 50);
		//Else the enemy will go to the 
		} else if(enemy.getX() > player.getX()){
			enemy.setBounds(enemy.getX() - 50, enemy.getY(), 50, 50);
		}
		
		if(enemy.getY() < player.getY()){
			enemy.setBounds(enemy.getX(), enemy.getY() + 50, 50, 50);
		} else if(enemy.getY() > player.getY()){
			enemy.setBounds(enemy.getX(), enemy.getY() - 50, 50, 50);
		}
	}

	public static void HandleButton(int btn, JTextField pbx, JTextField pby, JTextField ebx, JTextField eby, JPanel player, JPanel enemy) {
		switch (btn) {
		case 1: //Left button
			if (player.getX() > 0){
				player.setBounds(player.getX() - 50, player.getY(), 50, 50);
			}
			coordsDisp(pbx, pby, ebx, eby, player, enemy);
			break;
		case 2: //Right button
			if (player.getX() < 550){
				player.setBounds(player.getX() + 50, player.getY(), 50, 50);
			}
			coordsDisp(pbx, pby, ebx, eby, player, enemy);
			break;
		case 3: //Down button
			if (player.getY() < 400){
				player.setBounds(player.getX(), player.getY() + 50, 50, 50);
			}
			coordsDisp(pbx, pby, ebx, eby, player, enemy);
			break;
		case 4: //Up button
			if (player.getY() > 0){
				player.setBounds(player.getX(), player.getY() - 50, 50, 50);
			}
			coordsDisp(pbx, pby, ebx, eby, player, enemy);
			break;
		}
	}

	//Main Method, mostly for declaring JFrame components
	//Still very messy, could do with some tidying up
	//To make it more readable and intuitive
	public static void main(String[] args) {
		//JFrame declaration
		JFrame window = new JFrame("Test game");

		//Enemy declaration as a JPanel
		JPanel enemy = new JPanel();

		//Generates random number to set as enemy coords
		Random enemyPos = new Random();
		int enemyPosX = enemyPos.nextInt(550);
		int enemyPosY = enemyPos.nextInt(400);

		//Ensure the random number is a multiple of 50
		while(enemyPosX % 50 != 0){
			enemyPosX -= 1;
			//System.out.println("enemyPosX: " + enemyPosX);
		}
		while(enemyPosY % 50 != 0){
			enemyPosY -= 1;
			//System.out.println("enemyPosX: " + enemyPosX);
		}

		enemy.setBounds(enemyPosX, enemyPosY, 50, 50);
		enemy.setLayout(null);
		enemy.setBackground(Color.decode("#C81B13"));

		//Player declaration as a JPanel
		JPanel player = new JPanel();
		player.setBounds(300, 250, 50, 50);
		player.setLayout(null);
		player.setBackground(Color.decode("#17C813"));

		//All the textfields for displaying coords
		//Of player and enemy, using JTextFields
		JTextField pbx = new JTextField("pbx: " + player.getX());
		pbx.setBounds(5, 5, 65, 20);
		JTextField pby = new JTextField("pby: " + player.getY());
		pby.setBounds(75, 5, 65, 20);
		JTextField ebx = new JTextField("ebx: " + enemy.getX());
		ebx.setBounds(5, 30, 65, 20);
		JTextField eby = new JTextField("eby: " + enemy.getY());
		eby.setBounds(75, 30, 65, 20);

		//Down button
		JButton down_button = new JButton("S");
		down_button.setBounds(275, 400, 50, 50);
		down_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					HandleButton(3, pbx, pby, ebx, eby, player, enemy);
			}
		});

		//Up button
		JButton up_button = new JButton("W");
		up_button.setBounds(275, 350, 50, 50);
		up_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandleButton(4, pbx, pby, ebx, eby, player, enemy);
			}
		});

		//Left button
		JButton left_button = new JButton("A");
		left_button.setBounds(225, 400, 50, 50);
		left_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandleButton(1, pbx, pby, ebx, eby, player, enemy);
			}
		});

		//Right button
		JButton right_button = new JButton("D");
		right_button.setBounds(325, 400, 50, 50);
		right_button.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandleButton(2, pbx, pby, ebx, eby, player, enemy);
			}
		});

		//Panel where all the textfield components are
		//That displays player and enemy coords
		JPanel debugInfo = new JPanel();
		debugInfo.setBounds(445, 10, 145, 55);
		debugInfo.setLayout(null);
		debugInfo.setBackground(Color.lightGray);

		//Makes sure all components are visible
		//On the debugInfo panel
		debugInfo.add(pbx);
		debugInfo.add(pby);
		debugInfo.add(ebx);
		debugInfo.add(eby);

		//Makes sure all components are visible on the JFrame
		window.add(down_button);
		window.add(up_button);
		window.add(left_button);
		window.add(right_button);
		window.add(player);
		window.add(enemy);
		window.add(debugInfo);

		//Bunch of settings for the JFrame itself
		window.setSize(600, 490);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.getContentPane().setBackground(Color.gray);


	}
}