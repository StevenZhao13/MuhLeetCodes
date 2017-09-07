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


	private static class Foo{
		int i;
		Foo(){}
	}
	
	public static void main(String[] args){
		Foo a = new Foo(); 
		Foo b = new Foo(); 
		Foo c = a;

		System.out.println("someString" == "someString" );
		System.out.println(a.equals(b));

		System.out.println(a.equals(b));
	}
}