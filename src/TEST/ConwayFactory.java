package TEST;

import java.util.Random;

public class ConwayFactory {



    public static boolean[][] yushe(){
        boolean[][] grid = new boolean[20][20];

// 脉冲星相对坐标（17×17 图案，相对于 (0,0)）
        int[][] pulsarCoords = {
                {2,4},{2,5},{2,6},{2,10},{2,11},{2,12},
                {4,2},{4,7},{4,9},{4,14},
                {5,2},{5,7},{5,9},{5,14},
                {6,2},{6,7},{6,9},{6,14},
                {7,4},{7,5},{7,6},{7,10},{7,11},{7,12},
                {9,4},{9,5},{9,6},{9,10},{9,11},{9,12},
                {10,2},{10,7},{10,9},{10,14},
                {11,2},{11,7},{11,9},{11,14},
                {12,2},{12,7},{12,9},{12,14},
                {14,4},{14,5},{14,6},{14,10},{14,11},{14,12}
        };

        int offsetRow = 1;
        int offsetCol = 1;

        for (int[] p : pulsarCoords) {
            int r = p[0] + offsetRow;
            int c = p[1] + offsetCol;
            if (r >= 0 && r < 20 && c >= 0 && c < 20) {
                grid[r][c] = true;
            }
        }
        return grid;
    }

    public static boolean[][] yushe2(){
        boolean[][] grid = new boolean[20][20];
        Random rand = new Random();

// 随机背景（10% 存活）
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                grid[i][j] = rand.nextDouble() < 0.1;
            }
        }

// 在中心附近放置一个滑翔机（坐标相对网格中央偏移）
        int cx = 10, cy = 10;
        grid[cx][cy+1] = true;
        grid[cx+1][cy+2] = true;
        grid[cx+2][cy] = true;
        grid[cx+2][cy+1] = true;
        grid[cx+2][cy+2] = true;

        return grid;
    }

    public static boolean[][] yushe3(){
        boolean[][] grid = new boolean[20][20];

        int[][] simkinGun = {
                {1,5},{1,6},{2,5},{2,6},
                {11,5},{11,6},{11,7},{12,4},{12,8},{13,3},{13,9},{14,3},{14,9},
                {15,6},{16,4},{16,8},{17,5},{17,6},{17,7},{18,5},{18,6}
        };

        int offsetRow = 2;   // 调整位置
        int offsetCol = 2;

        for (int[] p : simkinGun) {
            int r = p[0] + offsetRow;
            int c = p[1] + offsetCol;
            if (r >= 0 && r < 20 && c >= 0 && c < 20) {
                grid[r][c] = true;
            }
        }
        return grid;
    }

}
