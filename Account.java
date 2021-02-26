package odnowa;

public class Account {
	private char _acctType;
	private double _balance;
	private String _acctId;

	public char getAcctType()
	{
		return _acctType;
	}
	public double getBalance()
	{
		return _balance;
	}
	public String getAcctId()
	{
		return _acctId;
	}
	public void setBalance(double dAmount)
	{
		this._balance=dAmount;
	}
	public void setId(String sId)
	{
		this._acctId=sId;
	}
	public void setAcctType(char cAcctType)
	{
		this._acctType=cAcctType;
	}
	//____________________Constructors________________
	public Account() {}
	public Account (char cType,double dAmount )
	{
		this._acctType=cType;
		this._balance=dAmount;
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
	public boolean Deposit(double dAmount)
	{

		if(dAmount>0)
		{	this._balance+=dAmount;
		  return true;
		}
		else
			return true;
	}
}
