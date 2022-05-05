package Task;

public class p3 {

	public static void arithmetic(int x) {
		for (int i = 1; i <= 10; ++i)
            System.out.println(x + " * " + i +
                               " = " + x * i);
	}
    public static void main(String arg[])
    {  
      int x = 5;
      arithmetic(x);
    }
}
