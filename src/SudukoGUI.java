// GUI for Suduko Solution

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SudukoGUI extends JFrame {
	
	public LimitedTextField[][] textField = new LimitedTextField[9][9];
			
	public SudukoGUI () {
		init();
	}
	
	private void buttonActionGo () {
		int[][] data = getData();
		Suduko suduko = new Suduko(data);
		suduko.recursionSearch();
		setData(data);
	}
	
	private void buttonActionReset() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j].setText(null);
			}
		}
	}
	
	private int[][] getData () {
		int[][] data = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					data[i][j] = Integer.parseInt(textField[i][j].getText());
				}
				catch (NumberFormatException e) {
					data[i][j] = 0;
				}
			}
		}
		return data;
	}
	
	private void setData (int[][] data) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				textField[i][j].setText(Integer.toString(data[i][j]));
			}
		}
	}
	
	private void init () {
		setTitle("Suduko Solver");
		setSize(320, 400);
		setLayout(null);
		setBackground(Color.GRAY);
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    JButton buttonGo = new JButton("Go");
	    buttonGo.setBounds(new Rectangle(30, 325, 100, 40));
	    buttonGo.addActionListener(new ActionListener() {    	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	buttonActionGo();
            }
        });
	    
	    JButton buttonReset = new JButton("Reset");
	    buttonReset.setBounds(new Rectangle(190, 325, 100, 40));
	    buttonReset.addActionListener(new ActionListener() {    	 
            public void actionPerformed(ActionEvent e)
            {
                //Execute when button is pressed
            	buttonActionReset();
            }
        });
	    
	    JPanel sudukoPanel = new JPanel();
	    sudukoPanel.setSize(315, 315);
	    sudukoPanel.setBounds(new Rectangle(3, 3, 315, 315));
	    //sudukoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    sudukoPanel.setBorder(BorderFactory.createEtchedBorder(Color.GRAY, Color.GRAY));
	    sudukoPanel.setLayout(new GridLayout(9, 9, 0, 0));
 
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++){
        		textField[i][j] = new LimitedTextField();
        		sudukoPanel.add(textField[i][j]);
        	}
        }

        add(sudukoPanel);
        add(buttonGo);
        add(buttonReset);
	}
}

class LimitedTextField extends JTextField{
	
    public LimitedTextField() {
    	super(JTextField.CENTER);
        this.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                int code = e.getKeyChar();
                if (code == KeyEvent.VK_BACK_SPACE) {
                    e.isActionKey();
                } else if (getDocument().getLength() >= 1) {
                    e.consume();
                }
            }
        });
    }
}
