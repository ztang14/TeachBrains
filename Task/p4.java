package Task;

import java.util.Arrays;

public class p4 {
    public static void sort(int[] array) {
    	Arrays.sort(array);
    	for(int a : array) {
    		System.out.print(a + ", ");
    	}
    }
	public static void main(String[] args) {
		int[] array = {10, 56, 98, 0, 42, 6};
		sort(array);
	}
}