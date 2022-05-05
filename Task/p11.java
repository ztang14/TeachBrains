package Task;

import java.util.Scanner;

public class p11 {
	
	public static void main(String args[])

	{

	double c;

	Scanner in = new Scanner(System.in);

	double a; double b;

	System.out.println("Input your salary");

	a=in.nextInt();

	System.out.println("Give your serving years");

	b=in.nextInt();

	c=a*5/100; 

	if (b>5||b==5)

	{

	System.out.println("Here's your bonus: " + c);

	}

	else

	{

	System.out.println("Your bonus access is denied");

	}

	}

}



