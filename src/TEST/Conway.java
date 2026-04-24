package TEST;

import java.util.Scanner;

public class Conway {


    private String[][] cell = new String[20][20];
    private boolean[][] isAlive = new boolean[20][20];
    private int[][] count = new int[20][20];
    private Scanner sca = new Scanner(System.in);

    //private Map<String,Boolean> map = new LinkedHashMap<>();

    public void setCell(String[][] cell) {
        this.cell = cell;
    }

    public void setIsAlive(boolean[][] isAlive) {
        this.isAlive = isAlive;
    }

    Conway(boolean[][] isAlive) {
        setIsAlive(isAlive);
    }
/*
    public void setMap() {
        for (int i=0;i<cell.length;i++){
            for (int k=0;k<cell.length;k++){
                map.put(cell[i][k],isAlive[i][k]);
            }
        }
    }
 */

    public void World() throws InterruptedException {
        System.out.println("康威生命游戏！！");
        print(start1());
        System.out.println("开始演化");
        Thread.sleep(1000);

        while (true) {
            print(CellChanging());
            Thread.sleep(800);
            /*
            System.out.println("要不要继续 y/n");
            String str = sca.nextLine();
            if (str.equals("y")){
                System.out.println("继续");
            }else if (str.equals("n")){
                break;
            }else {
                System.out.println("非法字符,继续演化");
            }

             */
        }

    }

    private int[][] CountStatue() {

        for (int i = 0; i < isAlive.length; i++) {
            for (int k = 0; k < isAlive.length; k++) {
                if (i == 0) {
                    if (k == 0) {
                        if (isAlive[i][k + 1]) count[i][k]++;
                        if (isAlive[i + 1][k]) count[i][k]++;
                        if (isAlive[i + 1][k + 1]) count[i][k]++;
                    } else if (k == 19) {
                        if (isAlive[i][k - 1]) count[i][k]++;
                        if (isAlive[i + 1][k - 1]) count[i][k]++;
                        if (isAlive[i + 1][k]) count[i][k]++;
                    } else {

                        if (isAlive[i + 1][k - 1]) count[i][k]++;
                        if (isAlive[i + 1][k]) count[i][k]++;
                        if (isAlive[i + 1][k + 1]) count[i][k]++;
                        if (isAlive[i][k - 1]) count[i][k]++;
                        if (isAlive[i][k + 1]) count[i][k]++;
                    }
                } else if (i == 19) {
                    if (k == 0) {
                        if (isAlive[i - 1][k + 1]) count[i][k]++;
                        if (isAlive[i - 1][k]) count[i][k]++;
                        if (isAlive[i][k + 1]) count[i][k]++;
                    } else if (k == 19) {
                        if (isAlive[i - 1][k]) count[i][k]++;
                        if (isAlive[i - 1][k - 1]) count[i][k]++;
                        if (isAlive[i][k - 1]) count[i][k]++;
                    } else {
                        if (isAlive[i - 1][k - 1]) count[i][k]++;
                        if (isAlive[i - 1][k]) count[i][k]++;
                        if (isAlive[i - 1][k + 1]) count[i][k]++;
                        if (isAlive[i][k - 1]) count[i][k]++;
                        if (isAlive[i][k + 1]) count[i][k]++;
                    }
                } else {
                    if (k == 0) {
                        if (isAlive[i - 1][k]) count[i][k]++;
                        if (isAlive[i - 1][k + 1]) count[i][k]++;
                        if (isAlive[i][k + 1]) count[i][k]++;
                        if (isAlive[i + 1][k]) count[i][k]++;
                        if (isAlive[i + 1][k + 1]) count[i][k]++;
                    } else if (k == 19) {
                        if (isAlive[i - 1][k]) count[i][k]++;
                        if (isAlive[i - 1][k - 1]) count[i][k]++;
                        if (isAlive[i][k - 1]) count[i][k]++;
                        if (isAlive[i + 1][k]) count[i][k]++;
                        if (isAlive[i + 1][k - 1]) count[i][k]++;
                    } else {
                        if (isAlive[i - 1][k - 1]) count[i][k]++;
                        if (isAlive[i - 1][k]) count[i][k]++;
                        if (isAlive[i - 1][k + 1]) count[i][k]++;
                        if (isAlive[i][k - 1]) count[i][k]++;
                        if (isAlive[i][k + 1]) count[i][k]++;
                        if (isAlive[i + 1][k - 1]) count[i][k]++;
                        if (isAlive[i + 1][k]) count[i][k]++;
                        if (isAlive[i + 1][k + 1]) count[i][k]++;
                    }
                }
            }

        }

        return count;
    }

    private boolean[][] IsAlive() {
        count = CountStatue();
        int[][] n = new int[20][20];
        for (int i = 0; i < isAlive.length; i++) {
            for (int k = 0; k < isAlive.length; k++) {
                if (isAlive[i][k]) {
                    if (count[i][k] == 2 || count[i][k] == 3) {
                        isAlive[i][k] = true;
                    } else {
                        isAlive[i][k] = false;
                    }
                } else {
                    if (count[i][k] == 3) {
                        isAlive[i][k] = true;
                    } else {
                        isAlive[i][k] = false;
                    }
                }
            }
        }
        count = n.clone();
        return isAlive;
    }

    private String[][] CellChanging() {
        isAlive = IsAlive();
        String live = "😀";
        String die = "💀";

        for (int i = 0; i < cell.length; i++) {
            for (int k = 0; k < cell.length; k++) {
                if (isAlive[i][k]) {
                    cell[i][k] = live;
                } else {
                    cell[i][k] = die;
                }
            }
        }

        return cell;
    }

    private String[][] start1() {
        String live = "😀";
        String die = "💀";
        for (int i = 0; i < cell.length; i++) {
            for (int k = 0; k < cell.length; k++) {
                if (isAlive[i][k]) {
                    cell[i][k] = live;
                } else {
                    cell[i][k] = die;
                }
            }
        }
        return cell;
    }

    private void print(String[][] cell) {
        for (int i = 0; i < cell.length; i++) {
            System.out.print("[");
            for (int k = 0; k < cell.length; k++) {
                System.out.print(cell[i][k]);
                if (k != 19) System.out.print("|");
            }
            System.out.println("]");
        }
        System.out.println();
    }
}
