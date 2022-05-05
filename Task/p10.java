package Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class p10 {

    public static List<Integer> twoSum(int[] nums, int target) {
    	List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
          if(map.containsKey(target - nums[i])){
            res.add(map.get(target - nums[i]));
            res.add(i);
          }else{
              map.put(nums[i], i);
          }
        }
        return res;
      }
	public static void main(String[] args) {
	  int[] array = {1, 3, 5, 7, 9, 11};
	  System.out.print(twoSum(array, 20));
	}

}
