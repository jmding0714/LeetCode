/**
 * Created by udingji on 5/25/17.
 */
public class DungeonGame {

    // The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K) was initially positioned in the top-left room and must fight his way through the dungeon to rescue the princess.

    // The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.

    // Some of the rooms are guarded by demons, so the knight loses health (negative integers) upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase the knight's health (positive integers).

    // In order to reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.


    // Write a function to determine the knight's minimum initial health so that he is able to rescue the princess.

    // For example, given the dungeon below, the initial health of the knight must be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.

    // -2 (K)	-3	    3
    // -5	    -10	    1
    // 10	    30	    -5 (P)


    // Idea: Create a 2D dp array to store the min amount of health needed to reach each cell
    public int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;

        int r = dungeon.length, c = dungeon[0].length;

        int[][] dp = new int[r][c];

        dp[r-1][c-1] = Math.max(1,1-dungeon[r-1][c-1]);

        for (int i = r - 2; i >= 0; i--){
            dp[i][c-1] = Math.max(1,dp[i+1][c-1] - dungeon[i][c-1]);
        }

        for (int i = c - 2; i >= 0; i--){
            dp[r-1][i] = Math.max(1,dp[r-1][i+1] - dungeon[r-1][i]);
        }


        for (int i = r - 2; i >= 0; i--){
            for (int j = c - 2; j >= 0; j--){
                int down = Math.max(1,dp[i+1][j] - dungeon[i][j]);
                int right = Math.max(1,dp[i][j+1] - dungeon[i][j]);
                dp[i][j] = Math.min(down,right);
            }
        }

        return dp[0][0];
    }

}
