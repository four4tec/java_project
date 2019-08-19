package final_project;

import javax.swing.JLabel;

public class People {
	public int hp;
	public int[] position;
	public int[] direction;
	public int size;
	public int upFlag;
	public int downFlag;
	public int rightFlag;
	public int leftFlag;
	public int speed;
	public int shooting;
	public int shooting_max;
	public JLabel pic;
	/* 4 */public int imm;
	/* 4 */public int imm_max = 20;

	public People(int[] position, int speed) {
		hp = 3;
		this.position = position;
		size = 3;
		this.direction = new int[] { 0, 0 };
		this.speed = speed;
		this.shooting = 15;
		this.shooting_max = 15;
		/* 4 */imm = 20;
	}

	public void forword() {
		position[1] = position[1] - 1 * speed;
	}

	public void backword() {
		position[1] = position[1] + 1 * speed;
	}

	public void right() {
		position[0] = position[0] + 1 * speed;
	}

	public void left() {
		position[0] = position[0] - 1 * speed;
	}

	public int loss_hp(int loss) {
		if (this.hp - loss <= 0) {
			hp = 0;
			return -1;
		} else {
			this.hp -= loss;
			return 0;
		}
	}

	public void move() {
		if (this.upFlag == 1)
			this.forword();
		if (this.downFlag == 1)
			this.backword();
		if (this.rightFlag == 1)
			this.right();
		if (this.leftFlag == 1)
			this.left();
	}

	public void set_direction(int[] direction) {
		this.direction = direction;
	}

	public void set_position(int[] position) {
		this.position = position;
	}

	public int[] get_position() {
		return this.position;
	}

	public int[] get_direction() {
		return this.direction;
	}
}
