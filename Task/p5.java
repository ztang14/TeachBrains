package Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class p5 {

	public static Integer[] remove(Integer[] array, int x) {
      List<Integer> tempList = new ArrayList<Integer>(Arrays.asList(array));
      int index = Arrays.asList(array).indexOf(x);
      tempList.remove(index);
      return tempList.toArray(new Integer[0]);
	}
	public static void main(String[] args) {
	  Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	  Integer[] array2 = remove(array, 6);
	  for(Integer a : array2) {
	    System.out.print(a + " ");
	  }
	}
}
