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
	
	
	
	// 3. Longest Substring Without Repeating Characters 
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

	
	// 6. ZigZag Conversion
	public static String convert(String s, int numRows){
		if (numRows == 1){
			return s;
		} else {			
			
			StringBuilder ret = new StringBuilder("");
			int wholeStep = (numRows - 1)*2;
			
			for (int i = 0; i < numRows; i++){

				
				if (i == 0 || i == numRows-1){
					for (int index = i; index < s.length(); index+=wholeStep){
						ret.append(s.substring(index,index+1));

					}
				}
				
				else {
					boolean offsetStep = true;
					int index = i;
					
					int firstHalfStep = (numRows - i - 1) * 2;
					int secondHalfStep = wholeStep - firstHalfStep;
					while (index < s.length()){
						ret.append(s.substring(index, index+1));
						
						if (offsetStep){
							 offsetStep = false;
							 index+=firstHalfStep;
						} else {
							 offsetStep = true;
							 index+=secondHalfStep;
						}
					}
				}
			}			
			return ret.toString();
		}		
	}

	
	
	
	// 7. ZigZag Conversion
    public int reverse(int x) {
    	if (x == 1){
    		return x;
    	}
    	
    	boolean isNeg = false;
    	if (x < 0){
    		isNeg = true;
    		x*=-1;
    	}
    	int ret = 0;
    	while (x>0){
    		int curDig = x % 10;
    		x = x/10;
    		
    		int newRet = (ret * 10) + curDig;
    		// Check for integer overflow
    		if ((newRet - curDig) /10 != ret){
    			return 0;
    		}
    		ret = newRet;
    	}
    	if (isNeg) return -ret;
    	else return ret;
    }
	
	

}
