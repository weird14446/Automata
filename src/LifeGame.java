public class LifeGame {
    private char[][] board;
    int unit = 0, gen = 0;

    public LifeGame(int row, int col) {
        board = new char[row][col];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                board[i][j] = ' ';
    }

    public LifeGame(char[][] arr) {
        board = new char[arr.length][arr[0].length];
        for (int i = 0; i < arr.length; i++)
            System.arraycopy(arr[i], 0, board[i], 0, arr[0].length);
        numberOfUnit();
    }

    public void set(int i, int j, char val) {
        board[i][j] = val;
    }

    public void nextGeneration() {
        gen++;
        char[][] nextBoard = new char[board.length][board[0].length];
        for (int i = 0; i < nextBoard.length; i++)
            for (int j = 0; j < nextBoard[0].length; j++)
                nextBoard[i][j] = ' ';
        int[] dx = {1, 1, -1, -1, -1, 0, 1, 0};
        int[] dy = {-1, 1, -1, 1, 0, -1, 0, 1};
        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                for (int k = 0; k < dx.length; k++) {
                    if (!(0 <= i + dy[k] && i + dy[k] < board.length
                            && 0 <= j + dx[k] && j + dx[k] < board[0].length)) continue;
                    else if (board[i + dy[k]][j + dx[k]] == '@') cnt++;
                }
                if (cnt == 3) nextBoard[i][j] = '@';
                else if (cnt == 2) nextBoard[i][j] = board[i][j];
                else nextBoard[i][j] = ' ';
                cnt = 0;
            }
        }
        board = nextBoard;
        numberOfUnit();
    }

    private void numberOfUnit() {
        unit = 0;
        for (char[] chars : board)
            for (char i : chars)
                if (i == '@') unit++;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("generation: ").append(gen).append("\n");
        result.append("number of unit: ").append(unit).append("\n");
        result.append("-".repeat(board[0].length)).append("\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                result.append(board[i][j]);
            if (i == board.length - 1) break;
            result.append("\n");
        }
        result.append("\n").append("-".repeat(board[0].length));
        return result.toString();
    }
}
