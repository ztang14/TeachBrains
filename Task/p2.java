package Task;

public class p2 {

	public static int reverse(int x) {
	    long num = 0;
	    while(x != 0){
	        num = num * 10 + x % 10;
	        x /= 10;  
	    }
	    if(Math.abs(num) > Integer.MAX_VALUE){
	        return 0;
	    }
	    else{
	        return (int)num;
	    }
	}
	public static void main(String[] args) {
		int x = 567989;
		System.out.print(reverse(x));
	}

}
