package final_project;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class graphic {
	JFrame parent;
	int[][] all_map;
	JLabel background;

	public graphic(JFrame in) {
		parent = in;
		all_map = new int[90][120];
		for (int i = 0; i < 90; ++i) {
			for (int j = 0; j < 120; ++j) {
				all_map[i][j] = 0;
			}
		}
		for (int i = 18; i < 23; ++i) {
			for (int j = 28; j < 120; ++j) {
				all_map[i][j] = 1;
			}

		}
		for (int i = 23; i < 63; ++i) {
			for (int j = 0; j < 120; ++j) {
				all_map[i][j] = 1;
			}

		}
		for (int i = 63; i < 72; ++i) {
			for (int j = 12; j < 120; ++j) {
				all_map[i][j] = 1;
			}

		}
		for (int i = 72; i < 76; ++i) {
			for (int j = 12; j < 115; ++j) {
				all_map[i][j] = 1;
			}

		}
		//
		background = new JLabel();
		parent.add(background);
		background.setVisible(true);
		background.setLocation(0, 0);
		background.setSize(1200, 900);
		background.setIcon(new ImageIcon(
				new ImageIcon("src/pic_src/map.jpg").getImage().getScaledInstance(1200, 900, Image.SCALE_DEFAULT)));
		//
	}
}
