import java.io.FileNotFoundException;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.File;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        int[][] arr = {{0,4,0,0,3,1,5,6,0},
                {0,0,0,0,0,0,0,4,2},
                {8,0,0,2,0,5,0,0,0},
                {3,1,2,4,0,0,0,8,0},
                {9,0,8,0,2,0,7,0,0},
                {0,0,4,0,0,3,2,0,9},
                {7,0,1,0,5,4,9,2,0},
                {0,3,0,7,0,2,8,5,0},
                {5,0,0,0,1,9,0,0,3}};
        print(arr);
        if (addNumber(arr)) {
            System.out.println("Solved");
        } else {
            System.out.println("Unsolvable");
        }

        print(arr);
    }

    /**
     * This program checks whether
     * @param arr is an 2-D array input
     * @param column is an int input
     * @param row is an int input
     * @param a is an int input
     * @return a boolean
     */
    public static boolean isValid(int[][] arr, int column, int row, int a) {
        int x0 = (int) (row / 3) * 3;
        int y0 = (int) (column / 3) * 3;

        // loop through columns
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == a) {
                return false;
            }
        }

        // loop through row
        for (int i = 0; i < 9; i++) {
            if (arr[i][column] == a) {
                return false;
            }
        }

        // loop through 3x3 matrix
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[x0 + i][y0 + j] == a) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean addNumber(int[][] arr) {
        for (int col = 0; col < 9; col++) {
            for(int row = 0; row < 9; row++) {
                if (arr[col][row] == 0) {
                    for (int k = 1; k <= 9; k++) {
                        if (isValid(arr, row, col, k)) {
                            arr[col][row] = k;
                            if (addNumber(arr)) {
                                return true;
                            } else {
                                arr[col][row] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    public static void print(int[][] arr) throws FileNotFoundException {
        PrintWriter out = new PrintWriter(new File("solution"));
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                out.println("*********************");
            }
            for (int column = 0; column < 9; column++) {
                if (column % 3 == 0 && column != 0) {
                    out.print("* ");
                }
                out.print(arr[row][column] + " ");
            }
            out.println();
        }
        out.close();
    }
}
