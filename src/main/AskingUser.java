package main;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AskingUser extends JFrame implements ActionListener {

	private JLabel Fruits;
	private JLabel poisoned;
	private JLabel ghosts;
	private JTextField FruitsNum;
	private JTextField poisonedNum;
	private JTextField ghostsNum;
	private JButton next = new JButton(" next ");
	private JPanel panel;
	private static boolean isAdded;
	ActionListener changeFocus;

	public AskingUser() {
		setSize(200, 200);
		setLayout(new FlowLayout());

		addComponent();

		changeFocus = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == FruitsNum) {
					if (!FruitsNum.getText().isEmpty())
						poisonedNum.requestFocus();
				} else if (e.getSource() == poisonedNum) {
					if (!FruitsNum.getText().isEmpty())
						ghostsNum.requestFocus();
				} else if (e.getSource() == ghostsNum) {
					if (!FruitsNum.getText().isEmpty())
						next.doClick();
				}
			}
		};

		FruitsNum.addActionListener(changeFocus);
		poisonedNum.addActionListener(changeFocus);
		ghostsNum.addActionListener(changeFocus);

	}

	private void addComponent() {
		panel = new JPanel();
		isAdded = false;
		Fruits = new JLabel("please enter the number of Fruits");
		poisoned = new JLabel("please enter the number of poisoned Food");
		ghosts = new JLabel("please enter the number of ghosts");
		FruitsNum = new JTextField(11);
		poisonedNum = new JTextField(11);
		ghostsNum = new JTextField(11);
		next = new JButton(" next ");

		GridLayout layout = new GridLayout(6, 1);
		layout.setHgap(20);
		layout.setVgap(8);

		panel.setLayout(layout);
		panel.add(Fruits);
		panel.add(FruitsNum);
		panel.add(poisoned);
		panel.add(poisonedNum);
		panel.add(ghosts);
		panel.add(ghostsNum);

		isAdded = false;
	}

	public int getFruitsNum() {
		return Integer.parseInt(FruitsNum.getText());
	}

	public int getPoisonedNum() {
		return Integer.parseInt(poisonedNum.getText());
	}

	public int getGhostsNum() {
		return Integer.parseInt(ghostsNum.getText());
	}

	public static boolean isAdded() {
		return isAdded;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if ((getFruitsNum() <= 0 || getPoisonedNum() <= 0 || getGhostsNum() <= 0)) {
				JOptionPane.showMessageDialog(null, "the numbers can not be empty or zeros", "Error",
						JOptionPane.ERROR_MESSAGE);

			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Please enter numbers", "Error", JOptionPane.ERROR_MESSAGE);
		}

		if ((getFruitsNum() > 0 && getPoisonedNum() > 0 && getGhostsNum() > 0)) {
			setVisible(false);
			isAdded = true;
		}
	}

	public void addScreen() {
		setLayout(new FlowLayout());
		add(panel);
		add(next);
		next.addActionListener(this);

		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
}
