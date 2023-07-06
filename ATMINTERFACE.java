import java.util.*;
class NotFoundException extends Exception
{
	public String toString()
	{
		return "PASSWORD||USERNAME MISMATCH";
	}
}
class MinBlncException extends Exception
{
	public String toString()
	{
		return "YOUR DEBBITING BALANCE EXCEEDING THE PRESENT BALANCE\n SO PLEASE TRY AGAIN";
	}
}
class TransferBlncException extends Exception
{
	public String toString()
	{
		return "YOUR TRANSFERING BALANCE EXCEEDING THE PRESENT BALANCE\n SO PLEASE TRY AGAIN";
	}
}
interface Bank
{
	public void Credentials(String Username,String Password) throws NotFoundException;
	void Deposit(double blnc);
	public void Withdraw(double blnc) throws MinBlncException;
	void displayBalance();
	void Quit();
	public void Transfer(double blnc,long acc) throws TransferBlncException;
}
class Customer implements Bank
{
	String username[]=new String[10];
	String password[]=new String[10];
	double tot;
	int flag=0;
	Customer(double cash)
	{
		tot=cash;
	}
	public void Credentials(String u,String p) throws NotFoundException
	{
		int i,j,flag1=0,flag2=0;
		for(i=0;i<username.length;i++)
		{
			if(u.equals(username[i]))
				flag1=1;
		}
		for(j=0;j<password.length;j++)
		{
			if(p.equals(password[j]))
				flag2=1;
		}
		if(flag1==0||flag2==0)
			throw new NotFoundException();
		System.out.println("ENTERED PASSWORD AND USERNAME MATCHED.");
		flag=1;
	}
	public void Deposit(double blnc)
	{
		System.out.println("SUCCESSFULLY DEPOSITED"+blnc);
		tot=tot+blnc;
	}
	public void Withdraw(double blnc) throws MinBlncException
	{
		if(blnc>tot)
			throw new MinBlncException();
			System.out.println("SUCCESSFULLY WITHDRAWN"+blnc);
			tot=tot-blnc;	
	}
	public void displayBalance()
	{
		System.out.println("YOUR PRESENT AMOUNT IS:"+tot);
	}
	public void Quit()
	{
		System.out.println("EXITED.");
	}
	public void Transfer(double blnc,long acc) throws TransferBlncException
	{
		if(blnc>tot)
			throw new TransferBlncException();
		System.out.println("SUCCESSFULLY AMOUNT TRANSFERRED");
		tot=tot-blnc;
	}
}
class Main
{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		Customer c=new Customer(0);
		List<String> li=new LinkedList<String>();
		Date d=new Date();
		String u,p;
		int s,z=0,se;
		long acc;
		do{
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1.FOR NEW ACCOUNT\n2.FOR LOGINn\n3.FOR EXIT");
		System.out.print("Enter your choice:");
		se=sc.nextInt();
		if(se==1)
		{
		System.out.print("ENTER NEW USERNAME:");
		c.username[z]=sc.next();
		System.out.print("ENTER PASSWORD:");
		c.password[z]=sc.next();
		z++;
		}
		else if(se==2)
		{
		System.out.println("ENTER YOUE CREDENTIALS!!");
		System.out.print("ENTER YOUR USERNAME:");
		u=sc.next();
		System.out.print("ENTER YOUR PASSWORD:");
		p=sc.next();
		try
		{
			c.Credentials(u,p);
		}
		catch(NotFoundException e)
		{
			System.out.println(e);
		}
		if(c.flag==0)
		{
			System.out.println("PLEASE TRY AGAIN");
		}
		else
		{
			int ch;
			double b;
			do{
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("1.Deposit\n2.Withdraw\n3.Checkbalance\n4.Transfer\n5.Transaction History\n6.Exit\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.print("Enter A Number To Perform Respective Action:");
			ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					System.out.print("PLEASE ENTER AMOUNT TO DEPOSIT:");
					b=sc.nextDouble();
					c.Deposit(b);
					li.add("DEPOSITED AMOUNT"+b+" "+d.toString());
					break;
				case 2:
					System.out.print("ENTER AMOUNT FOR WITHDRAWING");
					b=sc.nextDouble();
					try
					{
						c.Withdraw(b);
						li.add("WITHDRAWN AMOUNT"+b+" "+d.toString());
						break;
					}
					catch(MinBlncException e)
					{
						System.out.println(e);
						break;
					}
				case 3:
					c.displayBalance();
					li.add("CHECKED BALANCE"+d.toString());
					break;
				case 4:
					System.out.println("ENTER ACCOUNT NUMBER TO WHICH YOU WANT TO TRANSFER:");
					acc=sc.nextLong();
					System.out.print("ENTER THE AMOUNT YOU WANT TO TRANSFER:");
					b=sc.nextDouble();
					try
					{
						c.Transfer(b,acc);
						li.add("TRANSFERED AMOUNT"+b+d.toString());
						break;
					}
					catch(TransferBlncException t)
					{
						System.out.println(t);
						break;
					}
				case 5:
					System.out.println("~~~~~TRANSACTION HISTORY~~~~~");
					for(String mm:li)
					{
						System.out.println(li);
						break;
					}
					break;
				case 6:
					c.Quit();
					break;
				default:
					System.out.println("INVALID CHOICE");
					break;	
			}
			}while(ch!=6);
		}
	}
	else
		System.out.println("EXITED.");
	}while(se!=3);
}
}