import javax.swing.*;
import java.awt.*;
import java.util.Vector;
import java.util.Random;

class Walls {
	Vector<JPanel> walls = new Vector<>();
	Vector<Vector<Integer>> walls_pos = new Vector<Vector<Integer>>();

	public void createMap(int w_count, int win_width, int win_height) {

		for (int i = 0; i < w_count; i++) {			// create w_coutn walls you mf
			Random rand = new Random();
			int x = rand.nextInt(win_width);
			int y = rand.nextInt(win_height);

			while (x%50 != 0) x--;
			while (y%50 != 0) y--;
			// System.out.println("x: " + x + " y: " + y);

			// add the position of the panel
			Vector<Integer> pos = new Vector<Integer>();
			pos.add(x); pos.add(y);
			walls_pos.add(pos);

			JPanel wall = new JPanel();
			wall.setBounds(x, y, 50, 50);
			wall.setBackground(Color.decode("#C81B13"));

			this.walls.addElement(wall);
		}
	}

	public void displayPos() {
		for (int i = 0; i < this.walls_pos.size(); i++) {
			for (int d = 0; d < this.walls_pos.get(i).size(); d++) {
				System.out.print(this.walls_pos.get(i).get(d) + " ");
			}
			
			System.out.println();
		}
	}

	public void addWallsToFrame(JFrame window) {
		for (JPanel w : this.walls) window.add(w);
	}
}

public class map_no_table {
	static int win_width = 600;
	static int win_height = 500;

	public static void main(String[] args) {
		JFrame window = new JFrame("Test map");
		window.setLayout(null);
		window.setSize(win_width, win_height);

		Walls waa = new Walls();
		waa.createMap(5, win_width, win_height);
		waa.addWallsToFrame(window);
		// waa.displayPos();

		// add the walls to the shit window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}