import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class game_test {
	static int width = 600;
	static int height = 530;
	static int s_size = 50;
	static Color red = new Color(255, 0, 0);

	public static void Movement(int btn_id, JButton p_sprite, JButton e_sprite) {
		Rectangle p_bounds = p_sprite.getBounds();
		Rectangle e_bounds = e_sprite.getBounds();

		if (p_bounds.x == 0 && p_bounds.y == 0) {
			System.out.println("You win");
		}

		switch (btn_id) {
		case 1: // Left
			if (!(p_bounds.x <= 0))
				p_sprite.setBounds(p_bounds.x-50, p_bounds.y, p_bounds.width, p_bounds.height);
			break;
		case 2: // Right
			if (!(p_bounds.x >= width-s_size))
				p_sprite.setBounds(p_bounds.x+50, p_bounds.y, p_bounds.width, p_bounds.height);
			break;
		case 3: // Up
			if (!(p_bounds.y <= 0))
				p_sprite.setBounds(p_bounds.x, p_bounds.y-50, p_bounds.width, p_bounds.height);
			break;
		case 4: // Down
			if (!(p_bounds.y >= height-(p_bounds.height*2)))
				p_sprite.setBounds(p_bounds.x, p_bounds.y+50, p_bounds.width, p_bounds.height);
			break;
		}

		if (p_bounds.x > e_bounds.x) {
			e_sprite.setBounds(e_bounds.x+50, e_bounds.y, e_bounds.width, e_bounds.height);
		} else if (p_bounds.x < e_bounds.x) {
			e_sprite.setBounds(e_bounds.x-50, e_bounds.y, e_bounds.width, e_bounds.height);
		}

		if (p_bounds.y > e_bounds.y) {
			e_sprite.setBounds(e_bounds.x, e_bounds.y+50, e_bounds.width, e_bounds.height);
		} else if (e_bounds.y > p_bounds.y) 
			e_sprite.setBounds(e_bounds.x, e_bounds.y-50, e_bounds.width, e_bounds.height);
	}

	public static void main(String[] args) {
		JFrame window = new JFrame("test");

		JButton exit = new JButton("W");
		exit.setBounds(0, 0, 50, 50);
		exit.setBackground(red);

		JButton player = new JButton("P");
		player.setBounds(300, 300, 50, 50);

		JButton enemy = new JButton("E");
		enemy.setBounds(50, 400, 50, 50);

		JButton left_btn = new JButton("Left");
		left_btn.setBounds(90, height-100, 70, 30);
		left_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movement(1, player, enemy);
			}
		});

		JButton right_btn = new JButton("Right");
		right_btn.setBounds(180, height-100, 70, 30);
		right_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movement(2, player, enemy);
			}
		});

		JButton up_btn = new JButton("Up");
		up_btn.setBounds(270, height-100, 70, 30);
		up_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movement(3, player, enemy);
			}
		});

		JButton down_btn = new JButton("Down");
		down_btn.setBounds(360, height-100, 70, 30);
		down_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Movement(4, player, enemy);
			}
		});

		window.add(left_btn);
		window.add(right_btn);
		window.add(up_btn);
		window.add(down_btn);
		window.add(player);
		window.add(exit);
		window.add(enemy);

		window.setLayout(null);
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}