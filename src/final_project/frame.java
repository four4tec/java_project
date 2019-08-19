package final_project;

import java.awt.event.*;
import java.util.TimerTask;
import javax.swing.*;

import java.awt.*;

public class frame extends TimerTask {
	// variable
	int cnt;
	private final int DELAY_TIME = 10;
	long recent_time = 0;

	boolean start;
	boolean gameover;
	boolean win;
	boolean win_flag;
	JFrame main_window;
	graphic gra;
	People player;
	Slime[] enemy;
	JLabel[] live;
	JLabel start_end;
	JLabel show_time;
	int enemy_cnt;
	long spend;

	// --------------------------------------------------------------------------//
	// run
	public void run() {
		for (int i = player.hp; i < 3; ++i) {
			live[i].setIcon(new ImageIcon("src/pic_src/heart_empty.png"));
		}
		if (player.hp <= 0) {
			gameover = true;
		}
		if (enemy_cnt <= 0) {
			win = true;
		}
		//
		if (!start) {
			start_end.setVisible(false);
		}
		if (gameover) {
			start_end.setIcon(new ImageIcon("src/pic_src/gameover.png"));
			start_end.setVisible(true);
		}
		if (win) {
			start_end.setIcon(new ImageIcon("src/pic_src/win.png"));
			start_end.setVisible(true);
			if (win_flag) {
				win_flag = false;
				double time = (double) (System.currentTimeMillis() - spend) / 1000.0;
				show_time.setFont(new Font(Font.DIALOG, Font.BOLD, 35));
				show_time.setText("花費時間：" + time + "秒");
				show_time.setVisible(true);
			}
		}
		if (start || gameover || win)
			return;
		//
		Point now_point = MouseInfo.getPointerInfo().getLocation();
		player.set_direction(new int[] {
				(now_point.x - (8 - 6) - main_window.getLocation().x - (player.get_position()[0] + player.size)),
				(now_point.y - (31 - 6) - main_window.getLocation().y - (player.get_position()[1] + player.size)) });
		player.shooting = (player.shooting == 0) ? 0 : player.shooting - 1;
		player.imm = (player.imm == 0) ? 0 : player.imm - 1;
		refresh();
		ishited();
	}

	// construct
	public frame() {		
		recent_time = System.currentTimeMillis();
		// main_window
		main_window = new JFrame();
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_window.setSize(1200 + 8 - 6, 900 + 31 - 6);
		main_window.setTitle("game");
		main_window.setLayout(null);
		main_window.setVisible(true);
		main_window.addKeyListener(new MyKeyListener());
		main_window.addMouseListener(new MyMouseListener());
		main_window.setResizable(false);
		//
		show_time = new JLabel();
		show_time.setLocation(450, 470);
		show_time.setSize(400, 50);
		show_time.setVisible(false);
		main_window.add(show_time);
		//
		start_end = new JLabel();
		start_end.setLocation(300, 225);
		start_end.setSize(600, 450);
		start_end.setVisible(true);
		start_end.setIcon(new ImageIcon("src/pic_src/start.png"));
		main_window.add(start_end);
		// player
		player = new People(new int[] { 600, 450 }, 8);
		player.downFlag = 0;
		player.leftFlag = 0;
		player.rightFlag = 0;
		player.upFlag = 0;
		player.size = 40;
		player.pic = new JLabel();
		player.pic.setVisible(true);
		player.pic.setLocation(600, 450);
		player.pic.setSize(player.size, player.size);
		//
		player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/down.png").getImage().getScaledInstance(player.size,
				player.size, Image.SCALE_DEFAULT)));
		main_window.add(player.pic);
		// enemy
		enemy = new Slime[20];
		for (int i = 0; i < 20; ++i) {
			enemy[i] = new Slime(new int[] { (int) (Math.random() * 1025 + 120), (int) (Math.random() * 535 + 230) },
					2);
			enemy[i].size = 40;
			enemy[i].pic = new JLabel();
			enemy[i].pic.setVisible(true);
			enemy[i].pic.setLocation(enemy[i].get_position()[0], enemy[i].get_position()[1]);
			enemy[i].pic.setSize(enemy[i].size, enemy[i].size);
			enemy[i].pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/6.gif").getImage()
					.getScaledInstance(enemy[i].size, enemy[i].size, Image.SCALE_DEFAULT)));
			main_window.add(enemy[i].pic);
		}
		// live
		live = new JLabel[3];
		for (int i = 0; i < 3; ++i) {
			live[i] = new JLabel();
			live[i].setLocation(50 + i * 80, 50);
			live[i].setSize(52, 48);
			live[i].setIcon(new ImageIcon("src/pic_src/heart_full.png"));
			live[i].setVisible(true);
			main_window.add(live[i]);
		}
		// map,show
		enemy_cnt = 20;
		gra = new graphic(main_window);
		gameover = false;
		win = false;
		start = true;
		win_flag = true;
	}

	private void refresh() {
		player.move();
		if (player.upFlag == 1 && player.leftFlag == 0 && player.rightFlag == 0)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/up.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.downFlag == 1 && player.leftFlag == 0 && player.rightFlag == 0)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/down.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.rightFlag == 1 && player.upFlag == 0 && player.downFlag == 0)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/right.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.leftFlag == 1 && player.upFlag == 0 && player.downFlag == 0)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/left.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.upFlag == 1 && player.rightFlag == 1)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/right_up.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.upFlag == 1 && player.leftFlag == 1)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/left_up.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.downFlag == 1 && player.rightFlag == 1)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/right_down.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		if (player.downFlag == 1 && player.leftFlag == 1)
			player.pic.setIcon(new ImageIcon(new ImageIcon("src/pic_src/left_down.png").getImage()
					.getScaledInstance(player.size, player.size, Image.SCALE_DEFAULT)));
		player.pic.setLocation(player.get_position()[0], player.get_position()[1]);

		for (int i = 0; i < 20; i++) {
			if (enemy[i].position[0] + enemy[i].direction[0] + enemy[i].size * 4 > 1200
					|| enemy[i].position[0] + enemy[i].direction[0] < 0
					|| gra.all_map[enemy[i].get_position()[1] / 10][enemy[i].get_position()[0] / 10] == 0) {
				enemy[i].direction[0] = -1 * enemy[i].direction[0];
			}
			if (enemy[i].position[1] + enemy[i].direction[1] + enemy[i].size * 4 > 900
					|| enemy[i].position[1] + enemy[i].direction[1] < 0
					|| gra.all_map[enemy[i].get_position()[1] / 10][enemy[i].get_position()[0] / 10] == 0) {
				enemy[i].direction[1] = -1 * enemy[i].direction[1];
			}
			enemy[i].move();
			enemy[i].pic.setLocation(enemy[i].get_position()[0], enemy[i].get_position()[1]);
		}

		if ((System.currentTimeMillis() - recent_time) / 1000 > DELAY_TIME) {
			recent_time = System.currentTimeMillis();
			for (int i = 0; i < 20; i++) {
				enemy[i].change_direction();
			}
		}
	}

	private void ishited() {
		if (player.imm > 0)
			return;
		for (int i = 0; i < 20; ++i) {
			if (!enemy[i].pic.isVisible())
				continue;
			double tmp = Math.sqrt(Math.pow((player.position[0] - enemy[i].position[0]), 2)
					+ Math.pow((player.position[1] - enemy[i].position[1]), 2));
			if (tmp < (player.size + enemy[i].size)) {
				player.loss_hp(1);
				player.imm = player.imm_max;
			}
		}
		return;
	}

	// class for mouse event
	class MyMouseListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (start) {
				start = false;
				spend = System.currentTimeMillis();
				return;
			}
			if (player.shooting == 0) {
				player.shooting = player.shooting_max;
				double min_dis = 100000.0;
				for (int i = 0; i < 90; ++i) {
					for (int j = 0; j < 120; ++j) {
						if (gra.all_map[i][j] == 0) {
							double tmp = Math
									.abs(player.get_direction()[1] * (j * 10 - 5 - player.get_position()[0])
											- player.get_direction()[0] * (i * 10 - 5 - player.get_position()[1]))
									/ Math.sqrt(Math.pow(player.get_direction()[0], 2)
											+ Math.pow(player.get_direction()[1], 2));
							if (tmp < 6) {
								double tmp_d = Math.sqrt(Math.pow(j * 10 - 5 - player.get_position()[0], 2)
										+ Math.pow(i * 10 - 5 - player.get_position()[1], 2));
								min_dis = (tmp_d >= min_dis) ? min_dis : tmp_d;
							}
						}
					}
				}
				int tar = -1;
				for (int i = 0; i < 20; ++i) {
					if (!enemy[i].pic.isVisible() || !((enemy[i].get_position()[0] - player.get_position()[0])
							* player.get_direction()[0] >= 0
							&& (enemy[i].get_position()[1] - player.get_position()[1])
									* player.get_direction()[1] >= 0)) {
						continue;
					}
					double tmp = Math
							.abs(player.get_direction()[1] * (enemy[i].get_position()[0] - player.get_position()[0])
									- player.get_direction()[0]
											* (enemy[i].get_position()[1] - player.get_position()[1]))
							/ Math.sqrt(
									Math.pow(player.get_direction()[0], 2) + Math.pow(player.get_direction()[1], 2));
					double tmp_d = Math.sqrt(Math.pow(enemy[i].get_position()[0] - player.get_position()[0], 2)
							+ Math.pow(enemy[i].get_position()[0] - player.get_position()[1], 2));
					if (tmp < enemy[i].size && tmp_d < min_dis) {
						tar = i;
						min_dis = tmp_d;
					}
				}
				if (tar != -1) {
					// enemy[tar].pic.setIcon(new ImageIcon("src/pic_src/1.png"));
					enemy[tar].loss_hp(4);
					if (enemy[tar].hp <= 0) {
						enemy[tar].pic.setVisible(false);
						--enemy_cnt;
					}
				}
			}
		}
	}

	// class for key event
	class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyChar()) {
			case 'w':
				player.upFlag = 1;
				break;
			case 's':
				player.downFlag = 1;
				break;
			case 'a':
				player.leftFlag = 1;
				break;
			case 'd':
				player.rightFlag = 1;
				break;
			case 'p':
				for (int i = 1; i < 20; ++i) {
					enemy[i].pic.setVisible(false);
					enemy[i].hp = 0;
				}
				enemy[0].pic.setVisible(true);
				enemy[0].hp = 3;
				enemy_cnt = 1;
				break;
			}
		}

		public void keyReleased(KeyEvent e) {
			switch (e.getKeyChar()) {
			case 'w':
				player.upFlag = 0;
				break;
			case 's':
				player.downFlag = 0;
				break;
			case 'a':
				player.leftFlag = 0;
				break;
			case 'd':
				player.rightFlag = 0;
				break;
			}
		}
	}
}
