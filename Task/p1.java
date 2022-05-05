package Task;

public class p1 {

	public static int getSum(int a, int b) {
		int sum = 0;
		for(int i = a; i <= b; i++) {
			sum+= i;
		}
		return sum;
	}
	public static void main(String[] args) {
		int a = 1, b = 10;
		System.out.print(getSum(a, b));
	}

}