package Task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class p7 {

	public static List<Character> findCommon(String s1, String s2){
	  List<Character> res = new ArrayList<>();
	  HashSet<Character> set = new HashSet<>();
	  for(char a : s1.toCharArray()) {
		  set.add(a);
	  }
	  for(char b : s2.toCharArray()) {
		  if(set.contains(b)) {
			  if(!res.contains(b)) {
			    res.add(b);
			  }
		  }
	  }
	  return res;
	}
	public static void main(String[] args) {
		String s1 = "hotdog";
		String s2 = "colddog";
        System.out.print(findCommon(s1, s2));
	}

}
