
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

}
