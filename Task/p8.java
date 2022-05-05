package Task;

public class p8 {

	public static int findSecond(int[] array) {
		if(array.length < 2) return -1;

	    int first = Integer.MIN_VALUE;
	    int second = Integer.MIN_VALUE;

	    for(int value : array) {
	        if(value > first) {
	            second = first;
	            first = value;

	        } else if(value > second && value != first) {
	            second = value;
	        }
	    }
	    return second;
	}

	public static void main(String[] args) {
		int[] array = {5, 7, 32, 14, 35, 12, 78};
		System.out.print(findSecond(array));
	}

}
