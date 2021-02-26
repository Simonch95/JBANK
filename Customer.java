package odnowa;

public class Customer {
	private String _name,_surname,_city,_phone,_Id;
	private int age;
	private double _balance;
	
	public void setAge(int iAge)
	{
		this.age=iAge;
	}
	public int getAge()
	{
		return age;
	}
	public void setId(String iId)
	{
		this._Id=iId;
	}
	public String getId()
	{
		return _Id;
	}
	public void setBalance(double dBalance)
	{
		this._balance=dBalance;
	}
	public double getBalance()
	{
		return _balance;
	}
	public void setName(String sName)
	{
		this._name=sName;
	}
	public String getSurName()
	{
		return _surname;
	}
	public void setCity(String sCity)
	{
		this._city=sCity;
	}
	public String getCitye()
	{
		return _city;
	}
	public void setPhone(String sPhone)
	{
		this._phone=sPhone;
	}
	public String getPhone()
	{
		return _phone;
	}
	public void setSurName(String sSurName)
	{
		this._surname=sSurName;
	}
	public String getName()
	{
		return _name;
	}
	
	public boolean Deposit(double dAmount)
	{

		if(dAmount>0)
		{	this._balance+=dAmount;
		  return true;
		}
		else
			return true;
	}
	public String toString()
	{
		return _name + "_"+ _surname+"_" + _Id+"_"+ _city+"_"+
	          _phone+ "_"+Integer.toString(age)+ "_$ "+Double.toString(_balance);		
		
	}
	Customer()
	{
		
	}
}
