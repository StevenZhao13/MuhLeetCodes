import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;


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


	// 12. Int to Roman
	public String intToRoman(int num) {
		int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

		StringBuilder sb = new StringBuilder();

		for(int i=0;i<values.length;i++) {
			while(num >= values[i]) {
				num -= values[i];
				sb.append(strs[i]);
			}
		}
		return sb.toString();


	}


	// 14. 	Longest Common Prefix

	public static String longestCommonPrefix(String[]  strs){

		if (strs.length == 0){
			return "";
		}

		String shortest = strs[0]; 

		for (int i = 1; i < strs.length; i++){
			if (shortest.length() <= strs[i].length()){
			} else{
				shortest = strs[i];
			}
		}

		System.out.println(shortest);
		StringBuilder fab = new StringBuilder(""); 

		boolean carryOn = true;
		for (int i = 0; i < shortest.length() && carryOn; i++){
			char rn = shortest.charAt(i);

			for (int j = 0; j < strs.length; j++){

				if (rn == strs[j].charAt(i)){

				} else {
					carryOn = false;
				}
			}

			if (carryOn)		fab.append(rn);
			else;
		}


		return fab.toString();
	}


	// 20. parenthesis paring validation
	public boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		Hashtable<Character, Character> ht = new Hashtable<Character, Character>();
		ht.put((char)41,(char)40);
		ht.put((char)93,(char)91);
		ht.put((char)125,(char)123);


		for (int i = 0; i< s.length(); i++){
			if (s.charAt(i) == 40 || s.charAt(i) == 91 || s.charAt(i) == 123){
				stack.push(s.charAt(i));

			} else if(s.charAt(i) == 41 || s.charAt(i) == 93 || s.charAt(i) == 125){
				if (!stack.isEmpty() && stack.peek() == ht.get(s.charAt(i))){
					stack.pop();
				} else {
					return false;
				}
			}
		}

		return stack.isEmpty();

	}


	// 21. Merging sorted list
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	// 22. generate parenthesis
	public List<String> generateParenthesis(int n) {
		List<String> list = new ArrayList<String>();
		backtrack(list, "", 0, 0, n);
		return list;
	}

	public void backtrack(List<String> list, String str, int open, int close, int max){

		if(str.length() == max*2){
			list.add(str);
			return;
		}

		if(open < max)
			backtrack(list, str+"(", open+1, close, max);
		if(close < open)
			backtrack(list, str+")", open, close+1, max);
	}


	// 23. Merge k Sorted Linked List
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode returnHeadNode = null;

		ListNode lastNode = null;



		boolean listEmptied = false;

		while (!listEmptied){

			int smallestIndex = findSmallestNodeIndex(lists);

			if (smallestIndex == -1){
				return null;

			} else {
				
				ListNode newNode = new ListNode(lists[smallestIndex].val);
				
				lists[smallestIndex] = lists[smallestIndex].next;
				
				if (returnHeadNode == null) {
					returnHeadNode = newNode;
					lastNode = returnHeadNode;
				} else {
					lastNode.next = newNode;
					lastNode = newNode;
				}
				
			}
		}

		return returnHeadNode;
	}

	public int findSmallestNodeIndex(ListNode[] lists){
		int smallestIndex = -1;					// if the list is empty, it will stay be -1
		int smallestNo = Integer.MAX_VALUE;

		for (int i = 0; i < lists.length; i++){

			if (		lists[i] != null
					&& 	lists[i].val < smallestNo){
				smallestNo = lists[i].val;
				smallestIndex = i;
			} else {}
		}

		return smallestIndex;
	}


}



