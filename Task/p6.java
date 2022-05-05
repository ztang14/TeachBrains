package Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class p6 {

	public static List<Integer> findDuplicates(int[] array){
		List<Integer> res = new ArrayList<>();
		HashSet<Integer> set = new HashSet<>();
		for(int a : array) {
		  if(set.contains(a)) {
			  res.add(a);
		  }
		  set.add(a);
		}
		return res;
	}
	
	public static void main(String[] args) {
      int[] array = {2, 3, 5, 6, 8, 3, 5};
      System.out.print(findDuplicates(array));
	}

}
