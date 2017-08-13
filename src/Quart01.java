import java.util.HashMap;


public class Quart01 {
	// 1. Two Sum
	public int[] twoSum(int[] nums, int target) {
		int[] ret= new int[2];

		for (int i = 0; i<nums.length; i++){
			for (int j = 0; j<nums.length; j++){
				if (i != j){
					if (nums[i]+nums[j]==target){
						ret[0] = i;
						ret[1] = j;
						return ret;
					}
				}
			}
		}
		return null;
	}


	// 2. Add Two Number 
	/**
	 * Definition for singly-linked list.
	 */
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}


	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if ( l1.val == 0 && l2.val == 0 
				&& l1.next == null && l2.next == null){
			return new ListNode(0);
		}

		return addHelper(l1,l2,0);

	}

	public ListNode addHelper(ListNode l1, ListNode l2, int increment){
		int value = 0 + increment;

		if(l1 != null)		value += l1.val;
		if(l2 != null)		value += l2.val;

		if (value == 0
				&& (l1 == null || l1.next == null )
				&& (l2 == null || l2.next == null )){	
			return null;
		} else{
			int nextIncrement = 0;
			if (value >= 10){
				nextIncrement = 1;
				value = value - 10;
			} else {}

			ListNode ret = new ListNode(value);
			ret.next = addHelper(
					(l1==null) ? null : l1.next,
					(l2==null) ? null : l2.next,
					nextIncrement);

			return ret;
		}

	}
	
	
	
	// 2. Longest Substring Without Repeating Characters 
    public int lengthOfLongestSubstring(String s) {
		if (s.length()==0) return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max=0;
		for (int i=0, j=0; i<s.length(); ++i){
			if (map.containsKey(s.charAt(i))){
				j = Math.max(j,map.get(s.charAt(i))+1);
			}
			map.put(s.charAt(i),i);
			max = Math.max(max,i-j+1);
		}
		return max;
}

	
	
	
	

}
