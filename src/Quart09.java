import java.util.Collections;
import java.util.HashMap;




public class Quart09 {

	//	public boolean canCross(int[] stones) {
	//        if(stones[1] > 1) return false;
	//        if(stones.length == 2) return true;
	//        return helper(stones, 1, 1);
	//    }
	//    private boolean helper(int[] arr, int i, int step){
	//        boolean pass = false;
	//        if(i == arr.length-1) return true;
	//        for(int j = i+1; j < arr.length; j++){
	//            if(arr[j] <= arr[i] + step + 1 && arr[j] >= arr[i]+step-1){
	//                pass = pass || helper(arr, j, arr[j] - arr[i]);
	//            }
	//        }
	//        return pass;
	//    }
	//

	// 403. Frog Jump
	public static boolean canCross(int[] stones) {

		if(stones[1] > 1) return false;
		if(stones.length == 2) return true;

		// Cook up the hash first
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();

		for (int i = 0; i < stones.length; i++){
			hash.put(stones[i], i);
		}
		return toNextStone(stones, 1, 1, hash);
	}

	public static boolean toNextStone(int[] stones, int curStoneIndex, int lastJumpDist, HashMap<Integer, Integer> hash){

		//		System.out.println("Attempting to jump from " + stones[curStoneIndex] + " to somewhere " + lastJumpDist + " away");

		if (curStoneIndex == stones.length - 1){
			return true;
		}
		int targetLocationShort = stones[curStoneIndex] + lastJumpDist - 1 ;
		int targetLocationMid = stones[curStoneIndex] + lastJumpDist ;
		int targetLocationLong = stones[curStoneIndex] + lastJumpDist + 1;

		if (hash.containsKey(targetLocationLong) &&
				toNextStone(stones, hash.get(targetLocationLong), lastJumpDist+1, hash)){
			return true;
		}
		if (hash.containsKey(targetLocationMid) &&
				toNextStone(stones, hash.get(targetLocationMid), lastJumpDist, hash)){
			return true;
		}
		if (hash.containsKey(targetLocationShort) && lastJumpDist > 1 &&
				toNextStone(stones, hash.get(targetLocationShort), lastJumpDist-1, hash)){
			return true;
		}
		return false;
	}


	// 413. Arithmetic Slices
	public static int numberOfArithmeticSlices(int[] nums){

		int sum = 0;
		int lengthCounter = 2;

		int curGap = -1;
		for (int i = 2; i < nums.length; i++){

			if (nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){

				lengthCounter ++;

			} else {
				if (lengthCounter >= 3){
					sum += getPossiblePick(lengthCounter);
				}
				lengthCounter = 2;
			}

		}

		if (lengthCounter >= 3){
			sum += getPossiblePick(lengthCounter);
		}

		return sum;
	}


	public static int getPossiblePick(int i){
		return (i-1) * (i-2) / 2;		
	}




	// 416. Partition Equal Subset Sum
	public boolean canPartition(int[] nums) {
		// check edge case
		if (nums == null || nums.length == 0) {
			return true;
		}
		// preprocess
		int volumn = 0;
		for (int num : nums) {
			volumn += num;
		}
		if (volumn % 2 != 0) {
			return false;
		}
		volumn /= 2;
		// dp def
		boolean[] dp = new boolean[volumn + 1];
		// dp init
		dp[0] = true;
		// dp transition
		for (int i = 1; i <= nums.length; i++) {
			for (int j = volumn; j >= nums[i-1]; j--) {
				dp[j] = dp[j] || dp[j - nums[i-1]];
			}
		}
		return dp[volumn];
	}



	// 419. Battleship in a Board
	public int countBattleships(char[][] board) {
		if (board.length==0) return 0; 
		
		int counter = 0;
		
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[0].length; j++){
				if (board[i][j] == 'X'){
					if (		(i > 0 && board[i-1][j] == 'X')
							|| 	(j > 0 && board[i][j-1] == 'X')
							){
					} else {
						counter++;
					}
				} else {}
			}
		}
		
		return counter;
	}



	public static void main(String[] args){

		int[] nums= {1,2,3,4};
		System.out.println(numberOfArithmeticSlices(nums));

	}
}