package odnowa;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;




public class MainMenu extends JFrame implements ActionListener{
	
	private JList<Customer> list;
	private JButton AddCustomerButton,DepositButton,RemoveCustomerButton,EditCustomerButton,
	                 WithdrawButton;
	public static Customer customer;
	private String sName,sSurname,sCity,sPhone,sId,sAge,sBalance;
	private JLabel BalanceLabel,NameLabel,SurnameLabel,AgeLabel,PhoneLabel,IdLabel,CityLabel;
	private boolean n=false,c=false,p=false,i=false,a=false;
	
	private JTextField _name,_surname,_age,_city,_Id,_balance,
	                     _phone,timeField;
	private JScrollPane scrollPane;
   
	DefaultListModel<Customer> model;

	public MainMenu()
	{
		 model = new DefaultListModel<Customer>();
		    list = new JList<Customer>(model);
		    scrollPane = new JScrollPane(list);
		   // scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		    //scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    scrollPane.setViewportView(list);
		   
		   // getContentPane().add(scrollPane, BorderLayout.CENTER);
		    add(scrollPane);
		    list.setVisibleRowCount(10);	    
		    
		GUI();
		AddButton();
		RemoveCustomer();
		CreateCustomer();
		AddTextField();
		EditCustomer();
		Focus();
		Withdraw();
		Deposit();	    
	}

	public void Focus() {
		_name.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_name.getText().equals("Name"))
			   _name.setText("");
	     }

		public void focusLost(FocusEvent e) {
		
			if(_name.getText().length()==0)
				_name.setText("Name");
		}
	});
			
		_Id.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_Id.getText().equals("ID"))
			   _Id.setText("");
	     }
		public void focusLost(FocusEvent e) {
		
			if(_Id.getText().length()==0)
				_Id.setText("ID");
		}
	});
		_surname.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_surname.getText().equals("Surname"))
			   _surname.setText("");
	     }


		public void focusLost(FocusEvent e) {
		
			if(_surname.getText().length()==0)
				_surname.setText("Surname");
		}
	});
		_city.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_city.getText().equals("City"))
			   _city.setText("");
	     }
		public void focusLost(FocusEvent e) {
		
			if(_city.getText().length()==0)
				_city.setText("City");
		}	
	});			
		_age.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_age.getText().equals("Age"))
			   _age.setText("");
	     }
		public void focusLost(FocusEvent e) {
		
			if(_age.getText().length()==0)
				_age.setText("Age");
		}
	});
		_balance.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_balance.getText().equals("Balance"))
			   _balance.setText("");
	     }


		public void focusLost(FocusEvent e) {
		
			if(_balance.getText().length()==0)
				_balance.setText("Balance");
		}
	});
		_phone.addFocusListener(new FocusListener() 
		{
	     public void focusGained(FocusEvent e)
	     {
		   if(_phone.getText().equals("Phone"))
			   _phone.setText("");
	     }


		public void focusLost(FocusEvent e) {
		
			if(_phone.getText().length()==0)
				_phone.setText("Phone");
		}
	});
			
	}
	public void GUI() {
		//setSize(700,500);
		setMinimumSize(new Dimension(800, 500));
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("BANK");
		setLayout(null);
		
		
	    
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
						timeField.setText(" Godzina " + sdfFormat.format(date));						
						Thread.sleep(1000);
						}
					} catch (Exception e) {					
						e.printStackTrace();
					}
			}
		};
		clock.start();		
	}
	public void Withdraw() {
		WithdrawButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e)
			     {
				   
				       Customer customer=list.getSelectedValue();
				       WithdrawWindow withdrawWindow=new WithdrawWindow(customer);				       
				       withdrawWindow.setVisible(true);    	
			    
			     }
		});
		
	}
	public void Deposit() {
		DepositButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e)
			     {				
				  
				   if(e.getSource()==DepositButton)
		            {

				       Customer customer1=list.getSelectedValue();
				       DepositWindow depositWindow=new DepositWindow(customer1);
				       depositWindow.setVisible(true);
				 
	             	}
			    
			     }
		});
		
		
	}

	public void EditCustomer()
	{
		EditCustomerButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e)
			     {				
				   Customer editCust=list.getSelectedValue();
				   
				   if(e.getSource()==EditCustomerButton) {
					   _name.setText(editCust.getName());
					   _surname.setText(editCust.getSurName());
					   _age.setText(Integer.toString(editCust.getAge()));
					   _city.setText(editCust.getCitye());
					   _phone.setText(editCust.getPhone());
					   _Id.setText(editCust.getId());
					   _balance.setText(Double.toString(editCust.getBalance()));
					   model.removeElement(list.getSelectedValue());
				   }
					   
				}  
			     
		});
	}
	
	
	public void RemoveCustomer()
	{
		RemoveCustomerButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e)
			     {				
				   if(e.getSource()==RemoveCustomerButton)			         
					 model.removeElement(list.getSelectedValue());	             			    
			     }
		});
	}
	public static boolean CheckCity(String sCity)
	{
		boolean city=true;
		if(Character.isUpperCase(sCity.charAt(0)))	{
			  for(int i=0;i<sCity.length();++i)
				if(!Character.isLetter(sCity.charAt(i)))
				{
					city=false;
					break;
				}
			}
		else return false; 
		if(city)
			return true;
		else return false;
	}
	public static boolean CheckNameSurname(String name,String surname)
	{
		boolean letterName=true,letterSurname=true;
		 if(name.length()>2 && (!name.equals("Name")&&!surname.equals("Surname"))) { 
		      if(Character.isUpperCase(name.charAt(0))) { 
			   for(int i=0;i<name.length();++i)
			          if(!Character.isLetter(name.charAt(i))) {
			    		return false;
			    	 }
		      } else return false;
		      if(Character.isUpperCase(surname.charAt(0))) {				  
			    for(int k=0;k<surname.length();++k)
			        if(!Character.isLetter(surname.charAt(k))){
			        	return false; }	
		        } else return false;	
		 }
		 else return false;
		 if(letterName && letterSurname)
		   return true;
		 else return false;
}
	public static boolean CheckPhone(String sPhone)
	{
		boolean phone=true;
		if(sPhone.equals("Phone")|| (sPhone.length()<7 || sPhone.length()>9))
				return false;
		else{
			  for(int i=0;i<sPhone.length();++i)
				     if(!Character.isDigit(sPhone.charAt(i))) 
				    		{
				    	 phone=false;
				    	 break;
				    		}
			    }
		if(phone)
			return true;
		else return false;
	}
	public static boolean CheckBalance(String sBalance)
	{
		boolean balance=true;
		double overZero=Double.parseDouble(sBalance);
		if(overZero>0) {
			for(int i=0;i<sBalance.length();i++)
			if(!Character.isDigit(sBalance.charAt(i)))
			{
				balance=false;
				break;
			}
		}
		else return false;
		if(balance)
			return true;
		else return false;
		
		
	}
	public static boolean CheckId(String sid)
	{
		boolean id=true;
		if(sid.length()!=11)
			return false;
		
		else {
			for(int i=0;i<sid.length();++i)
			  if(!Character.isDigit(sid.charAt(i)))
			  { 
				  id = false;
				  break;
			  }			     
		    }
		if(id)
			return true;
		else return false;
			
	}
	public static boolean CheckAge(String sAge)
	{
		boolean open=true;
		int age=Integer.parseInt(sAge);
		if (sAge.equals("Age")|| (age>130 )|| (sAge.length()>3)) 
			 return false;
		else {
			  for(int i=0;i<sAge.length();++i)
			     if(Character.isLetter(sAge.charAt(i))) 
			    		open=false;			    	 
		    }
		return open;	
}
	
	public void CreateCustomer() {
		AddCustomerButton.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e)
			     {	
				   customer=new Customer();
				   
				   sName=_name.getText();
				   sSurname=_surname.getText();
				   sCity=_city.getText();
				   sPhone=_phone.getText();
				   sId=_Id.getText();
				   sAge=_age.getText();
				  
			
					  if(CheckNameSurname(sName,sSurname)) {
						  customer.setName(_name.getText());
						  customer.setSurName(_surname.getText());
						  n=true;
						  NameLabel.setText("");
						  SurnameLabel.setText("");
					  }
					  else {
						  NameLabel.setText("Invalid value");
						  SurnameLabel.setText("Invalid value");
						  JOptionPane.showMessageDialog(null,"Wrong name or surname!\nPlease check ");
						  
					  }
						  
			    	  if(CheckCity(sCity))
			    	  {
			    		  CityLabel.setText("");
			    		  customer.setCity(_city.getText());
			    		  c=true;
			    	  }
			    	  else {
			    		  CityLabel.setText("Invalid value");
			    		  JOptionPane.showMessageDialog(null,"Wrong city's name!\nPlease check ");
			    		  }
			    	  if(CheckId(sId)) {
			    		  
			    		  IdLabel.setText("");
			    		  customer.setId(_Id.getText());
			    		  i=true;
			    	  }
			    	  else {
			    		  IdLabel.setText("Invalid value");
			    		  JOptionPane.showMessageDialog(null,"Wrong ID number!\nPlease check ");			    		  
			    	  }
			    		  
			    	 
					   if(CheckPhone(sPhone))
					   {
						   PhoneLabel.setText("");
						   customer.setPhone(_phone.getText());
						   p=true;						   
					   }
					   else
					   {   PhoneLabel.setText("Invalid value");
						   JOptionPane.showMessageDialog(null,"Wrong phone's number!\nPlease check ");
					   }
					   
					   if(CheckAge(sAge))
					   {
						   AgeLabel.setText("");
						   customer.setAge(Integer.parseInt(_age.getText()));
						   a=true;						   
					   }
					   else
					   {
						   AgeLabel.setText("Invalid value");
						   JOptionPane.showMessageDialog(null,"Wrong age!\nPlease check ");
					   }
					 
					     if(n&&i&&c&&p&&a){		    	  				 
							  
							   customer.setBalance(Double.parseDouble(_balance.getText()));
							   model.addElement(customer);
							   JOptionPane.showMessageDialog(null,"Correct!\nYou created a new customer ");
							   n=false;
							   i=false;
							   c=false;
							   p=false;
							   a=false;
							   RemoveTextField();
							  AddTextField();
							  Focus();							  
					     }		     
			     }
		});
	}
	public void RemoveTextField() {
		remove(_name);
		remove(_surname);
		remove(_city);
		remove(_age);
		remove(_phone);
		remove(_balance);
		remove(_Id);
	}
	public void AddTextField()
   {
		timeField = new JTextField();
		timeField .setText("CLOCK");
		timeField .setBounds(20, 11, 300, 20);
		timeField .setEditable(false);
		timeField.setFont(new Font("Arial", Font.BOLD, 13));
		timeField.setBorder(BorderFactory.createLineBorder(Color.yellow));
		add(timeField );
		
	   _name=new JTextField("Name");
		_name.setBounds(10, 60, 150, 25);
		_name.setBorder(BorderFactory.createLineBorder(Color.yellow));
		_name.setFont(new Font("Arial",Font.BOLD, 15));
		_name.setToolTipText("The name must start with a big letter");
		add(_name);
		
		NameLabel=new JLabel();
		NameLabel.setBounds(170, 60, 100, 20);
		NameLabel.setForeground(Color.RED);		
		add(NameLabel);
		
		_age=new JTextField("Age");
		_age.setBounds(10, 150, 150, 25);
		_age.setBorder(BorderFactory.createLineBorder(Color.yellow));
		_age.setFont(new Font("Arial", Font.BOLD, 15));
		_age.setToolTipText("Press only amount of year");
		add(_age);
		
		AgeLabel=new JLabel();
		AgeLabel.setBounds(170, 150, 100, 20);
		AgeLabel.setForeground(Color.RED);		
		add(AgeLabel);
		_age.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				char input=e.getKeyChar();
				if((input<'0'||input>'9')&&input!='\b')
					e.consume();
                  
			}
		});
				
		_surname=new JTextField("Surname");
		_surname.setBounds(10, 90, 150, 25);
		_surname.setFont(new Font("Arial", Font.BOLD, 15));
		_surname.setBorder(BorderFactory.createLineBorder(Color.yellow));
		_surname.setToolTipText("The surname must start with a big letter");
		add(_surname);
		SurnameLabel=new JLabel();
		SurnameLabel.setBounds(170, 90, 100, 20);
		SurnameLabel.setForeground(Color.RED);		
		add(SurnameLabel);
		
		_Id=new JTextField("ID");
		_Id.setBounds(10,120,150, 25);
		_Id.setFont(new Font("Arial", Font.BOLD, 15));
		_Id.setBorder(BorderFactory.createLineBorder(Color.yellow));
		_Id.setToolTipText("Press 11 digits");
		add(_Id);	
		
		IdLabel=new JLabel();
		IdLabel.setBounds(170, 120, 100, 20);
		IdLabel.setForeground(Color.RED);		
		add(IdLabel);
		_Id.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				char input=e.getKeyChar();
				if(input<'0'||input>'9')
					e.consume();                  
			}
		});
		
		_city=new JTextField("City");
		_city.setBounds(10, 180, 150, 25);
		_city.setFont(new Font("Arial", Font.BOLD, 15));
		_city.setBorder(BorderFactory.createLineBorder(Color.yellow));
		_city.setToolTipText("The city names must start with a big letter");
		add(_city);		
		CityLabel=new JLabel();
		CityLabel.setBounds(170, 180, 100, 20);
		CityLabel.setForeground(Color.RED);		
		add(CityLabel);
		
		_balance=new JTextField("Balance");
		_balance.setBounds(10,210,150, 25);
		_balance.setFont(new Font("Arial", Font.BOLD, 15));
		_balance.setBorder(BorderFactory.createLineBorder(Color.yellow));
		add(_balance);
		
		
		_balance.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e)
			{
				char input=e.getKeyChar();
				if((input<'0'||input>'9')&&input!='\b'&&input!='.')
					e.consume();                
			}
		});		
		
		_phone=new JTextField("Phone");
		_phone.setBounds(10, 240, 150, 25);
		_phone.setFont(new Font("Arial", Font.BOLD, 15));
		_phone.setBorder(BorderFactory.createLineBorder(Color.yellow));
		_phone.setToolTipText("Press from seven to nine digits");
		
		add(_phone);	
		PhoneLabel=new JLabel();
		PhoneLabel.setBounds(170, 240, 100, 20);
		PhoneLabel.setForeground(Color.RED);		
		add(PhoneLabel);
	}
   
	public void AddButton()
	{
		// list=new JList();
		Color c1=new Color(207, 196, 161);
		  list.setBounds(300,50,460,410);
		  list.setBorder(BorderFactory.createLineBorder(Color.yellow));
		
		  list.setBackground(c1);

		  add(list);
		  Color c=new Color(159, 137, 119);
		  AddCustomerButton=new JButton("Save");
		  AddCustomerButton.setBounds(10, 330, 90, 40);
		  AddCustomerButton.setBackground(c);
		  AddCustomerButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
		  add(AddCustomerButton);			
			
	   	  RemoveCustomerButton=new JButton("Remove");
		  RemoveCustomerButton.setBounds(105, 330, 90, 40);		  
		  RemoveCustomerButton.setBackground(c);
		  RemoveCustomerButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
		  add(RemoveCustomerButton);
		  
		  EditCustomerButton=new JButton("Edit");
		  EditCustomerButton.setBounds(200, 330, 90, 40);
		  EditCustomerButton.setBackground(c);
		  EditCustomerButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
		  add(EditCustomerButton);
			
		  WithdrawButton=new JButton("Withdraw");
		  WithdrawButton.setBounds(10, 410, 130, 30);
		  WithdrawButton.setBackground(c);
		  WithdrawButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
		  add(WithdrawButton);
		  
		 DepositButton=new JButton("Deposit");
		 DepositButton.setBounds(160, 410, 130, 30);
		 DepositButton.setBackground(c);
		 DepositButton.setBorder(BorderFactory.createLineBorder(Color.yellow));
		  add(DepositButton);
		  
		
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) {
		MainMenu okienko=new MainMenu();
		Color c=new Color(225, 209, 170);
		okienko.getContentPane().setBackground(c);
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okienko.setVisible(true);
	}
}
