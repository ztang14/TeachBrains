package Task;

public class p9 {

	public static int findIndex(int[] array, int x) {
		int index = -1;
		for(int i = 0; i < array.length; i++) {
			if(array[i] == x) {
              index = i;
			}
		}
		return index;
	}
	public static void main(String[] args) {
	  int[] array = {1, 4, 6 ,7, 12, 52, 4};
	  System.out.print(findIndex(array, 7));
	}

}
