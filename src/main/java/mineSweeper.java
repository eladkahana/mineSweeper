/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author כהנא אלעד
 */
public class mineSweeper {

    private static int rowsCount;//number of rows
    private static int columnsCount;//number of columns
    private static int minesCount;//number of mines
    private static final int[][] board = new int[rowsCount][columnsCount];//the game board

    /**
     * verify 
     * 
     * @param rowsCount
     * @param columnsCount
     * @param minesCount 
     */
    public mineSweeper(int rowsCount, int columnsCount, int minesCount) {
        //restart the game
        this.rowsCount = rowsCount;
        this.columnsCount = columnsCount;
        this.minesCount = minesCount;

        //ensure that the game meets the requiments
        exceptionCreateBoard();
    }

    public static int getRowsCount() {
        return rowsCount;
    }

    public static void setRowsCount(int rowsCount) {
        mineSweeper.rowsCount = rowsCount;
    }

    public static int getColumnsCount() {
        return columnsCount;
    }

    public static void setColumnsCount(int columnsCount) {
        mineSweeper.columnsCount = columnsCount;
    }

    public static int getMinesCount() {
        return minesCount;
    }

    public static void setMinesCount(int minesCount) {
        mineSweeper.minesCount = minesCount;
    }
    
    /**
     * ensure that the game meets the requiments
     */
    public static void exceptionCreateBoard() {
        if (rowsCount <= 0 || columnsCount <= 0) {
            throw new ArithmeticException("size cannot be zero or less");
        }

        if (minesCount < 1 || minesCount > ((rowsCount * columnsCount) - 1)) {
            throw new ArithmeticException("the mines amount should be between 0 and "
                    + ((rowsCount * columnsCount) - 1));

        }
    }

    /**
     * creating the game board
     * 
     * put the number- 9 as a mine in random place
     */
    public static void createBoard() {

        for (; minesCount > 0; minesCount--) {
            int i = (int) (Math.random() * rowsCount);
            int j = (int) (Math.random() * columnsCount);
            if (board[i][j] == 9) {
                minesCount++;
            }
            board[i][j] = 9;
        }
    }

    /**
     * put all values in the near cells in array
     * 
     * @param row - the row of the current cell
     * @param column - the column of the current cell
     * @return 
     */
    public static int[] getNearFields(int row, int column) {
        int[] closeCells = new int[8];
        int location = 0;
        
        //scattering mines
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (j < 0 || j > columnsCount - 1 || i < 0 || i > rowsCount - 1) {
                    continue;
                }
                closeCells[location] = board[i][j];
                location++;

            }
        }
        return closeCells;
    }

    /**
     * find how many mines there are near each cell 
     */
    public static void checkNoOfMines() {

        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (board[i][j] != 9) {
                    int[] closeCells = getNearFields(i, j);
                    for (int loc = 0; loc < 8; loc++) {
                        if (closeCells[loc] == 9) {
                            board[i][j]++;
                        }
                    }
                }
            }
        }

    }

    /**
     * open the near cells according the rules
     * 
     * @param row - the row of the current cell
     * @param column - the column of the current cell
     */
    public static void openEmttyFields(int row, int column) {
        //chaeck if the cell is mine
        if (boom(row, column)) {
            return;
        }
        
        //perform the action
        if (board[row][column] > 0 && board[row][column] < 9) {
            board[row][column] = board[row][column];//open this cell
        } else if (board[row][column] == 0) {
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = column - 1; j <= column + 1; j++) {
                    if (j < 0 || j > columnsCount - 1 || i < 0 || i > rowsCount - 1) {
                        continue;
                    }
                    if (board[i][j] != 0)//did we check it already? (should be *) {
                    {
                        continue;
                    }

                    board[i][j] = board[i][j];//open this cell

                    if (board[i][j] == 0) {
                        openEmttyFields(i, j);
                    }
                }
            }
        }
        
        //check if the user has won
        if (win()) {
            return;
        }
    }

    /**
     * check if the user has won
     * @return 
     */
    public static boolean win() {

        
        boolean over = false;
        int sum = 0;
        
        //count how many cells open
        for (int i = 0; i < rowsCount; i++) {
            for (int j = 0; j < columnsCount; j++) {
                if (board[i][j] >= 0 && board[i][j] < 9) {
                    sum++;
                }
            }
        }
        
        //check if all cells are opening
        if ((rowsCount * columnsCount - sum) == minesCount) {
            System.out.println("you win!");
            return over = true;
        }

        return over;
    }

    /**
     * chaeck if the user click on a mine
     * 
     * @param row - the row of the current cell
     * @param column - the column of the current cell
     * @return 
     */
    public static boolean boom(int row, int column) {
        boolean over;
        if (board[row][column] == 9) {
            System.out.println("you lose :(");
            return over = true;
        } else {
            return over = false;
        }
    }
}
