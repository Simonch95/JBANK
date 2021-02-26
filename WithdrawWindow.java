package odnowa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public  class WithdrawWindow extends JFrame implements ActionListener {
	
	
	Customer newCustomer;
	private JButton _saveButton,_closeButton;
	private JTextField _balanceField,timeField;
	private JTextArea _answerText;
	private double _balance,_balanceTextField;
	private JLabel _askLabel;
	
	WithdrawWindow(Customer cust){
		this.newCustomer=cust;
		Color cb=new Color(225, 209, 170);
		this.getContentPane().setBackground(cb);
		setMinimumSize(new Dimension(400, 350));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("WITHDRAW");
		setLayout(null);
		AddButton();
		clock();
		
	}
	public void clock()
	{
		Thread clock = new Thread()
		{
			public void run()
			{						
					try {
						while(true)
						{							
						Date date=new Date();
						SimpleDateFormat sdfFormat=new SimpleDateFormat(" H:mm:ss    EEEE d MMMM yyyy");						
						timeField.setText( sdfFormat.format(date));						
						Thread.sleep(1000);
						}
					} catch (Exception e) {					
						e.printStackTrace();
					}
			}
		};
		clock.start();		
	}
	
	public boolean Withdraw(double dAmount)
	{
		if((_balance-dAmount)>=0)
		{
			_balance-=dAmount;
			return true;
		}	
		else
			return false;
	
	}
   public void AddButton() {
	   timeField = new JTextField();
		timeField .setText("CLOCK");
		timeField .setBounds(20, 11, 300, 20);
		timeField .setEditable(false);
		timeField.setFont(new Font("Arial", Font.BOLD, 13));
		timeField.setBorder(BorderFactory.createLineBorder(Color.yellow));
		add(timeField );
	   
	   Color c=new Color(159, 137, 119);
	   _balanceField=new JTextField();
	  _balanceField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				char input=e.getKeyChar();
				if((input<'0'||input>'9')&&input!='\b')
					e.consume();
               
			}
		});
	   _balanceField.setFont(new Font("Arial", Font.BOLD, 15));
	   _balanceField.setBounds(100, 80, 120, 25);
	   _balanceField.setBorder(BorderFactory.createLineBorder(Color.yellow));
	   
	   add(_balanceField);
	   
	   _saveButton=new JButton("SAVE");
	   _saveButton.setBounds(100, 120, 100, 20);
	   _saveButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
	   _saveButton.setBackground(c);
	   add(_saveButton);
	   
	   _askLabel=new JLabel("How much money do you want to withdraw ?");
	   _askLabel.setBounds(30, 40, 400, 30);
	   _askLabel.setFont(new Font("Arial", Font.BOLD, 15));
	   
	   add(_askLabel);
	   
	   _answerText=new JTextArea(); 
	   _answerText.setBounds(50,160,300,80);
	   
	 _answerText.setEditable(false);
	   _answerText.setBorder(BorderFactory.createLineBorder(Color.yellow));
	   _answerText.setFont(new Font("Arial", Font.BOLD, 15));
	   add(_answerText);
	   _closeButton=new JButton("BACK");
	   _closeButton.setBounds(220, 120, 100, 20);
	   _closeButton.setBorder(BorderFactory.createLineBorder(Color.yellow));	  
	   _closeButton.setBackground(c);
	   add(_closeButton);
	   
	   _saveButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e)
		   {			   
			   _balanceTextField=Double.parseDouble(_balanceField.getText());
			      _balance=newCustomer.getBalance();
			      if(Withdraw(_balanceTextField))
			      {
			    	  newCustomer.setBalance(_balance);
			    	
			    	  MainMenu.customer.setBalance(_balance);
			    	  _answerText.setText(newCustomer.getName() + " you withdraw "+_balanceTextField + "$.\nYou have "+   newCustomer.getBalance()
			    	                      +"$ left on the account.");		    				    	 
			      }
			      else JOptionPane.showMessageDialog(null, "You don't have enough funds on the account. \nPlease try again.");  
         
		   }
	   });
	 
    	  
	   _closeButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e)
		   {			   
     			if(e.getSource()==_closeButton)
				  WithdrawWindow.this.setVisible(false);
		   }
	   });
	 
   }

   @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
