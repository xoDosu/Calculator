package calculator;

import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Calculator implements ActionListener{
	
	public JButton buttons[] = new JButton[10];
	public JFrame frame = new JFrame();
	public JPanel screen_area = new JPanel();
	public JPanel button_area = new JPanel();
	public JPanel history_area = new JPanel();
	public JLabel logs_label = new JLabel();
	public JTextField screen_label = new JTextField();
	public JTextField history_label = new JTextField();
	public JButton addButton = new JButton("+");
	public JButton subButton = new JButton("-");
	public JButton multButton = new JButton("*");
	public JButton divButton = new JButton("/");
	public JButton backButton = new JButton("⌫");
	public JButton perButton = new JButton(".");
	public JButton clear = new JButton("C");
	public JButton equals = new JButton("=");
	
	//variables
	char operator;
	double number1, number2, result;
	private ArrayList<String> historyText = new ArrayList<String>();
	String[] buttonLabels = {
		    "0", "1", "2", 
		    "3", "4", "5", 
		    "6", "7", "8", 
		    	"9", 
		};

	public Calculator() {
		//screen that displays the numbers entered in by the user including the clear and backspace buttons.
		clear.setBackground(Color.DARK_GRAY);
		clear.setBorderPainted(false);
		clear.setForeground(Color.WHITE);
		equals.setForeground(Color.WHITE);
		equals.setBorderPainted(false);
		equals.setBackground(Color.DARK_GRAY);
		screen_label.setFont(new Font("SANS_SERIF", Font.BOLD, 45));
		screen_label.setForeground(Color.WHITE);
		screen_label.setBackground(Color.DARK_GRAY);
		screen_label.setAlignmentX(JTextField.RIGHT_ALIGNMENT);
		screen_label.setEditable(false);
		screen_area.setPreferredSize(new Dimension(400, 100));
		screen_area.setBackground(Color.DARK_GRAY);
		screen_area.setLayout(new BorderLayout());
		
		//set the layout of the button panel.
		button_area.setLayout(new GridLayout(4, 4));
		
		
		//create buttons and add them to the button panel.
		for(int i = 0; i < 10; i++) {
			buttons[i] = new JButton(buttonLabels[i]);
			buttons[i].setFont(new Font("SANS_SERIF", Font.BOLD, 40));
			buttons[i].setForeground(Color.white);
			buttons[i].setSize(50, 50);
			buttons[i].setBorderPainted(false);
			buttons[i].setBackground(Color.DARK_GRAY);
			button_area.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
		
		//add buttons to the grid layout panel.
		button_area.add(addButton);
		button_area.add(subButton);
		button_area.add(multButton);
		button_area.add(divButton);
		button_area.add(backButton);
		button_area.add(perButton);
		
		
		//customisation of operator buttons
		addButton.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
		subButton.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
		multButton.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
		divButton.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
		backButton.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
		perButton.setFont(new Font("SANS_SERIF", Font.BOLD, 40));
		
		addButton.setForeground(Color.white);
		subButton.setForeground(Color.white);
		multButton.setForeground(Color.white);
		divButton.setForeground(Color.white);
		backButton.setForeground(Color.white);
		perButton.setForeground(Color.white);
		
		addButton.setBackground(Color.DARK_GRAY);
		subButton.setBackground(Color.DARK_GRAY);
		multButton.setBackground(Color.DARK_GRAY);
		divButton.setBackground(Color.DARK_GRAY);
		backButton.setBackground(Color.DARK_GRAY);
		perButton.setBackground(Color.DARK_GRAY);
		
		addButton.setBorderPainted(false);
		subButton.setBorderPainted(false);
		multButton.setBorderPainted(false);
		divButton.setBorderPainted(false);
		backButton.setBorderPainted(false);
		perButton.setBorderPainted(false);
		
		//initialise buttons
		addButton.addActionListener(this);
		subButton.addActionListener(this);
		multButton.addActionListener(this);
		divButton.addActionListener(this);
		backButton.addActionListener(this);
		perButton.addActionListener(this);
		clear.addActionListener(this);
		equals.addActionListener(this);
		
		//history panel and label.
		history_label.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
		history_label.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		history_label.setForeground(Color.WHITE);
		history_label.setBackground(Color.DARK_GRAY);
		history_area.setLayout(new FlowLayout());
		history_area.setBackground(Color.DARK_GRAY);
		history_area.setPreferredSize(new Dimension(150,400));
		
		
		//add components to the frame
		screen_area.add(clear, BorderLayout.WEST);
		screen_area.add(screen_label, BorderLayout.CENTER);
		screen_area.add(equals, BorderLayout.EAST);
		frame.add(screen_area, BorderLayout.NORTH);
		frame.add(button_area, BorderLayout.CENTER);
		frame.add(history_area, BorderLayout.EAST);
		
		
		// creates the frame.
		frame.setSize(400, 500);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Calculator");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
	}
	

	
	
	public void backspace() {
		String string = screen_label.getText();
		screen_label.setText("");
		for(int i = 0; i < string.length() - 1; i++) {
			screen_label.setText(screen_label.getText() + string.charAt(i));
		}
	}
	
	public void solver() {
		number2 = Double.parseDouble(screen_label.getText());
		
		switch(operator) {
			case '+':
				result = number1 + number2;
				break;
			case '-':
				result = number1 - number2;
				break;
			case '*':
				result = number1 * number2;
				break;
			case '/':
				result = number1 / number2;
				break;
		}
		stringyfy(number1, number2, operator, result);
		screen_label.setText(String.valueOf(result));
		number1 = result;
		
	}
	
	//clear button method.
	public void clear() {
		screen_label.setText("");
	}
	
	public void stringyfy(double number12, double number22, char i, double result2) {
		String conv = String.valueOf(number12);
		String dov = String.valueOf(number22);
		String op = String.valueOf(operator);
		String res = String.valueOf(result2);
		
		String monk = conv + dov + op + res;
		
		setHistoryText(monk);
				
	}
	
	//delete button function for history text area.
	public void delete() {
		historyText.clear();
	}
	
	//getter for the history list
	public ArrayList<String> getHistoryText() {
		return historyText;
	}
	//setter for the history text area. 
	public void setHistoryText(String text) {
		historyText.add(text);
	}

	
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 10; i++) {
				if(e.getSource() == buttons[i]) {
					screen_label.setText(screen_label.getText().concat(String.valueOf(i)));
				} 
			}
			if(e.getSource() == addButton) {
				number1 = Double.parseDouble(screen_label.getText());
				operator = '+'; 
				screen_label.setText("");
			}
			if(e.getSource() == subButton) {
				number1 = Double.parseDouble(screen_label.getText());
				operator = '-'; 
				screen_label.setText("");
				
			}
			if(e.getSource() == multButton) {
				number1 = Double.parseDouble(screen_label.getText());
				operator = '*'; 
				screen_label.setText("");
				
			}
			if(e.getSource() == divButton) {
				number1 = Double.parseDouble(screen_label.getText());
				operator = '/'; 
				screen_label.setText("");
			}
			
			if(e.getSource() == perButton) {
				screen_label.setText(screen_label.getText().concat("."));
			}
			
			if(e.getSource() == backButton) {
				backspace();
			}
			
			if(e.getSource() == clear) {
				clear();
			}
			if(e.getSource() == equals) {
				solver();
			}
		}





	public static void main(String[] args) {
		new Calculator();
	}

	
}


