/* *
 * 	Author: Guang Peng Li
 *	Date: 24/04/2013
 *	Project: ATM
 * 	University of Liverpool
 *	Comment:  A virtual ATM cash machine.
 *	*/
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class ATM extends JFrame implements ActionListener{
	/**############################################**/
	/**----------------------------------- Field ----------------------------------**/
	/**############################################**/
	// System Variables Declaration
	private int loginAttempt = 1;
	private int balance = 10;
	private int input;
	private int stage;
	private String pound = "\u00a3";
	private String info = "\n\nWhich service do you requiere?";
	private char[] pinNumber = {'1', '2', '3', '4'};
	// Screen Loggin Declaration
	private JPanel logginPanel = new JPanel(new BorderLayout());
	private JLabel notiLabel = new JLabel("Please Enter Your PIN");
	private JPasswordField pinField = new JPasswordField();
	// Screen Logged Declaration
	private JPanel loggedPanel = new JPanel(new BorderLayout());
	private JLabel currencyLabel = new JLabel(pound);
	private JTextField inputLabel = new JTextField();
	private JTextArea introArea = new JTextArea();
	// Keypad Declaration
	JPanel keypadPanel = new JPanel(new BorderLayout());
	JPanel keypad = new JPanel(new GridLayout(4,3,5,5));
	private JButton button0 = new JButton("0");
	private JButton[] button = new JButton[9]; 
	private JButton enter = new JButton("ENTER");
	private JButton clear = new JButton("CLEAR");
	// Main Screen Declaration
	JPanel mainPanel = new JPanel(new BorderLayout());
	// Side Buttons Declaration
	private JPanel sidePanel = new JPanel(new BorderLayout());
	private JPanel sideButtons = new JPanel(new GridLayout(0,1,0,5));
	private JButton depButton = new JButton("DEPOSIT");
	private JButton exitButton = new JButton("EXIT");
	private JButton withDB5 = new JButton("WITHDRAW "+pound+"5");
	private JButton withDB10 = new JButton("WITHDRAW "+pound+"10");
	private JButton withDB20 = new JButton("WITHDRAW "+pound+"20");
	private JButton withDBA = new JButton("WITHDRAW Other Amount");
	private JButton changePinB = new JButton("CHANGE PIN");
	/**############################################**/
	/**--------------------------- Constructor ---------------------------------**/
	/**############################################**/
	public ATM(){
		super("ATM");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
		getGUI();
		pack();
		getContentPane().setBackground(Color.WHITE);
		setVisible(true);
		setSize(305,400);
		setResizable(false);
		setLocationRelativeTo(null);
        toFront();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**############################################**/
	/**----------------------------------- Main ----------------------------------**/
	/**############################################**/
	public static void main(String[] args){
		ATM frame1 = new ATM();
	}
	/**############################################**/
	/**-------------------------Create User Interface-----------------------------**/
	/**############################################**/
	// Create User Interface
	private void getGUI(){
		// Create loggin Screan
		logginScreen();
		// Created Logged Screen
		loggedScreen();
		// Create Keypad
		keyPad();
		// Create Main Panel
		mainPanel.add(logginPanel,BorderLayout.CENTER);
		mainPanel.add(keypadPanel,BorderLayout.SOUTH);
		// Create Side Control
		sideControl();
		// Add to Frame
		add(mainPanel,BorderLayout.CENTER);
	}
	// Create Loggin Screen
	private void logginScreen(){
		// Set Top NOT loggin
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.add(notiLabel,BorderLayout.CENTER);
		topPanel.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.SOUTH);
		notiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		notiLabel.setFont(new Font("Arial", Font.BOLD, 16));
		// Set Pin Frield NOT loggin
		JPanel pinPanel = new JPanel(new BorderLayout());
		pinPanel.add(pinField,BorderLayout.CENTER);
		pinPanel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
		pinField.setFont(new Font("Arial", Font.BOLD, 150));
		pinField.setHorizontalAlignment(SwingConstants.CENTER);
		pinField.setEditable(false);
		// Set loggin Panel
		logginPanel.add(topPanel,BorderLayout.NORTH);
		logginPanel.add(pinPanel,BorderLayout.CENTER);
		logginPanel.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
	}
	// Create Logged Screen
	private void loggedScreen(){
		//Set Intro Logged
		JPanel introPanel = new JPanel(new BorderLayout());
		introPanel.add(introArea,BorderLayout.CENTER);
		introPanel.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
		introArea.setFont(new Font("Arial", Font.BOLD, 18));
		introArea.setEditable(false);
		introArea.setLineWrap(true);
		// Set Input Logged
		JPanel inputPanel = new JPanel(new BorderLayout());
		inputPanel.add(currencyLabel,BorderLayout.WEST);
		inputPanel.add(inputLabel,BorderLayout.CENTER);
		inputPanel.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.NORTH);
		inputLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inputLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		inputLabel.setFont(new Font("Arial", Font.BOLD, 30));
		inputLabel.setEditable(false);
		currencyLabel.setFont(new Font("Arial", Font.BOLD, 30));
		currencyLabel.setVisible(false);
		// Set Logged Panel
		loggedPanel.add(introPanel,BorderLayout.CENTER);
		loggedPanel.add(inputPanel,BorderLayout.SOUTH);
		loggedPanel.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
	}
	// Create Keypad
	private void keyPad(){
		// Set keypad
		button0.setFont(new Font("Arial", Font.BOLD, 30));
		button0.addActionListener(this);
		enter.setFont(new Font("Arial", Font.BOLD, 17));
		enter.addActionListener(this);
		enter.setBackground(new Color(0,100,0));
		enter.setForeground(Color.WHITE);
		clear.setFont(new Font("Arial", Font.BOLD, 17));
		clear.addActionListener(this);
		clear.setBackground(Color.RED);
		clear.setForeground(Color.WHITE);
		// Keypad
		keypad.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		for(int i = 0; i < button.length; i++){
 			keypad.add(button[i] = new JButton(String.valueOf(i+1)));
 			button[i].setFont(new Font("Arial", Font.BOLD, 30));
 			button[i].addActionListener(this);
 		}
 		keypad.add(enter);
		keypad.add(button0);
		keypad.add(clear);
		// Set Keypad Panel
		keypadPanel.add(keypad,BorderLayout.CENTER);
		keypadPanel.add(new JSeparator(SwingConstants.HORIZONTAL),BorderLayout.NORTH);
	}
	// Create Side Dontrol
	private void sideControl(){
		// Side Buttons
		withDB5.addActionListener(this);
		withDB10.addActionListener(this);
		withDB20.addActionListener(this);
		withDBA.addActionListener(this);
		depButton.addActionListener(this);
		exitButton.addActionListener(this);
		exitButton.setBackground(new Color(0,139,139));
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(new Font("Arial", Font.BOLD, 30));
		changePinB.addActionListener(this);
		// Set Side Buttons
		sideButtons.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		sideButtons.add(withDB5);
		sideButtons.add(withDB10);
		sideButtons.add(withDB20);
		sideButtons.add(withDBA);
		sideButtons.add(depButton);
		sideButtons.add(changePinB);
		sideButtons.add(exitButton);
		// Set Side Panel
		sidePanel.add(sideButtons);
		sidePanel.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.WEST);
	}
	/**############################################**/
	/**------------------------- Events -----------------------------**/
	/**############################################**/
	public void actionPerformed(ActionEvent e){
		Object source = e.getSource();
		// When the button 1 to 9 is hit
	    for(int i = 0; i < button.length; i++){
	    	if(source == button[i]){
	    		if((stage == 0 || stage == 4) && pinField.getPassword().length < 4){	
	    			pinField.setText(pinField.getText() + button[i].getText());
	    		}
	    		else if(stage == 2 || stage == 3){
	    			inputLabel.setText(inputLabel.getText() + (i+1));
	    		}
	    	}
	    }
	    // When the button 0 is hit
	    if(source == button0){
	    	if((stage == 0 || stage== 4) && pinField.getPassword().length < 4){
	    		pinField.setText(pinField.getText() + button0.getText());
	    	}
	    	else if(stage == 2 || stage == 3){
	    		inputLabel.setText(inputLabel.getText() + 0);
	    	}
	    }
	    // When the button clear is hit
	    else if(source == clear){
	    	pinField.setText("");
	    	inputLabel.setText("");
	    }
	    // When the button enter is hit
       	else if (source == enter) {
       		char[] pin = pinField.getPassword();
       		// When the user is not logged / at login screen stage
       		switch(stage){
	            case 0: {
	            	// When the pin is correct
		            if (checkPin(pin)) {
		            	pinField.setText("");
		                stage = 1;
		                inputLabel.setText("PIN Accepted");
		                introArea.setText("Your Balance is "+pound+balance+info);
		                System.out.println("Loggin -->\nStart Balance: "+balance);
		            }
		            // When the pin is lesser than 4 characters 
		          	else if(pin.length < 4){
		          		notiLabel.setText("Please Insert 4 Digit PIN!");
		          	}
		          	// When the pin is incorrect
		            else {
		            	if(loginAttempt == 3){
		            		System.exit(0);
		            	}
		                loginAttempt++;
		                pinField.setText("");
		                notiLabel.setText("Wrong PIN! You have " +(4-loginAttempt)+ " More Attempts");
		            }
		        }
		        break;
		        // When the withdraw stage is activated
		        case 2: {
		        	convert();
			        if(balance >= input){
			        	balance-=input;
			        	introArea.setText("You have withdrawed "+pound+input+"\n\nYour new balance is "+pound+balance+info);
			        	inputLabel.setText("");
			        	stage = 1;
			        	eButtons();
			        	currencyLabel.setVisible(false);
			        	System.out.println("Windrawed: "+input);
			        	System.out.println("New Balance: "+balance);
			        }
			        else{
			        	introArea.setText("Your Balance is "+pound+balance+"\nNot enough balance!\n\nPlease reenter the amount from  the keypad.");
			        	inputLabel.setText("");
			        }
		        }
		        break;
		        // When the deposit stage is activated
		        case 3: {
		        	convert();
			        if(1000 >= input){	
			        	balance+=input;
			        	introArea.setText("You have deposited "+pound+input+"\n\nYour new balance is "+pound+balance+info);
			        	inputLabel.setText("");
			        	stage = 1;
			        	currencyLabel.setVisible(false);
			        	System.out.println("Deposited: "+input);
			        	System.out.println("New Balance: "+balance);
			        }
			        else{
			        	introArea.setText("You cannot deposit more than\n"+pound+"1000\n\nPlease reenter the amount from  the keypad.");
			        	inputLabel.setText("");
			        }
		        }
		        break;
		        // When the change pin stage is activated
		        case 4: {
		        	if(pin.length < 4){
		        		notiLabel.setText("Please Insert 4 Digit  PIN!");
		        	}
		        	else {
		        		pinNumber = (char[])pin.clone();
		        		inputLabel.setText("PIN Has Changed");
		        		stage = 1;
		        		pinField.setText("");
		        		System.out.println("Changed PIN to "+Arrays.toString(pin));
		        	}
		        }
		    }
        }

        //############################################//
        //############### Side Buttons ###############//
        //############################################//

        // When the button withdraw 5 is hit
        else if(source == withDB5){
        	if(balance >= 5){
        		balance-=5;
	        	introArea.setText("You have withdrawed "+pound+"5\n\nYour new balance is \u00a3"+balance+info);
	        	inputLabel.setText("");
	        	System.out.println("Windrawed: 5");
		        System.out.println("New Balance: "+balance);
	        }
	        else{
	        	introArea.setText("Your Balance is "+pound+balance+"\nNot enough balance!");
	        	inputLabel.setText("");
	        }
        }
        // When the button withdraw 10 is hit
        else if(source == withDB10){
        	if(balance >= 10){
        		balance-=10;
	        	introArea.setText("You have withdrawed \u00a310\n\nYour new balance is \u00a3"+balance+info);
	        	inputLabel.setText("");
	        	System.out.println("Windrawed: 10");
		        System.out.println("New Balance: "+balance);
	        }
	        else{
	        	introArea.setText("Your Balance is "+pound+balance+"\nNot enough balance!");
	        	inputLabel.setText("");
	        }
        }
        // When the button withdraw 20 is hit
        else if(source == withDB20){
        	if(balance >= 20){
        		balance-=20;
	        	introArea.setText("You have withdrawed \u00a320\n\nYour new balance is \u00a3"+balance+info);
	        	inputLabel.setText("");
	        	System.out.println("Windrawed: 20");
		        System.out.println("New Balance: "+balance);
	        }
	        else{
	        	introArea.setText("Your Balance is "+pound+balance+"\nNot enough balance!");
	        	inputLabel.setText("");
	        }
        }
        // When the button withdraw other ammount is hit
    	else if(source == withDBA){
    		introArea.setText("Windraw Money\n\nPlease enter the amount from      the keypad.");
    		stage = 2;
    		inputLabel.setText("");
    		
    	}
    	// When the button deposit is hit
    	else if(source == depButton){
    		introArea.setText("Deposit Money\n\nPlease enter the amount from      the keypad.");
    		stage = 3;
    		inputLabel.setText("");
    	}
    	// When the button change pin is hit
    	else if(source == changePinB){
    		stage = 4;
    		notiLabel.setText("Please Enter New PIN");
    	}
    	// When the button exit is hit
        else if(source == exitButton){
        	switch(stage){
        		case 1:
		            stage = 0;
		            inputLabel.setText("");
		            notiLabel.setText("Please Enter PIN");
		            loginAttempt = 1;
		            System.out.println("Log out -->\n");
		        	break;
		        default:
		        	inputLabel.setText("");
		        	pinField.setText("");
		        	introArea.setText("Service Cancelled\n\nYour Balance is "+pound+balance+info);
			        stage = 1;
	        }
        }

        switch(stage){
        	case 0:	eButtons();
        			mainPanel.remove(loggedPanel);
		            mainPanel.add(logginPanel);
		            remove(sidePanel);
		            setSize(305,400);
		            currencyLabel.setVisible(false);
		            validate();
		            repaint();
        			break;
        	case 1: eButtons();
        			dButtons();
        			currencyLabel.setVisible(false);
			        mainPanel.remove(logginPanel);
		        	mainPanel.add(loggedPanel);
		        	add(sidePanel,BorderLayout.EAST);
		            setSize(500,400);
        			exitButton.setText("Exit");
        			repaint();
		        	validate();
        			break;
        	case 2: eButtons();
        			dButtons();
        			currencyLabel.setVisible(true);
        			exitButton.setText("Cancel");
        			break;
        	case 3: eButtons();
        			dButtons();
        			currencyLabel.setVisible(true);
        			exitButton.setText("Cancel");
        			break;
        	case 4: eButtons();
        			dButtons();
        			mainPanel.add(logginPanel);
    				mainPanel.remove(loggedPanel);
    				validate();
            		repaint();
        			exitButton.setText("Cancel");
        			break;
        }
	}
	/**############################################**/
	/**------------------------------ Compute Values ------------------------------**/
	/**############################################**/
	// Match the input with the pin
	private boolean checkPin(char[] input) {
        boolean accepted = true;
        return accepted = Arrays.equals(input, pinNumber);
    }
    // Convert input from string to integer
    private void convert() {
    	input = Integer.parseInt(inputLabel.getText());
    }

    private void dButtons() {
    	Component[] items = keypad.getComponents();
    	Component[] items2 = sideButtons.getComponents();
		if(stage == 1){	
			for (int i = 0; i < items.length; i++) {
				items[i].setEnabled(false);
			}
		}
		if(stage != 1){
			for (int i = 0; i < items2.length-1; i++) {
				items2[i].setEnabled(false);
			}
		}
    }
    private void eButtons() {
    	Component[] items = keypad.getComponents();
    	Component[] items2 = sideButtons.getComponents();
    	for (int i = 0; i < items.length; i++) {
    		items[i].setEnabled(true);
    	}
		if(stage == 1) {
			for (int i = 0; i < items2.length-1; i++) {
				items2[i].setEnabled(true);
			}
		}
    }
}