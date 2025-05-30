
public class Board  {


    private int rows;
    private int cols;
    
    /** The grid of pieces */
    private Player[][] grid;
    
    

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Player[rows][cols];
        // set each cell of the board to null (empty).
        reset();

    }
    
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = null;
            }
        }
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    
    /**
    * Returns the Player whose piece occupies the given location, 
    * @param row int
    * @param col int
    */
    public Player getCell(int row, int col ) throws IndexOutOfBoundsException{
        if( (row < 0) || (col < 0) || (row >= rows) || (col >= cols) ) {
            throw new IndexOutOfBoundsException();
        } else {
            return grid[row][col];
        }
    }
    
    //returns true if there are no more plays left
    public boolean boardFilled(){
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(grid[row][col] == null)
                return false;
            }
        }
        return true; 
    }

    // Returns true if move is possible given board state.  
    public boolean possibleMove(Move move) {
        // write this.  Right now, it ignores filled columns, claiming any move is possible
        for(int row = 0; row < rows; row++){
            if(grid[row][move.getColumn()] == null)
            return true;
        }
        return false;
    }
    
    // Adds a piece to the board for a given Move
    public void addPiece(Move move) {
        //this is a test stub, you need to rewrite this.
        loop:
        for(int row = 0; row < rows; row++){
            if(grid[row][move.getColumn()] == null){
                grid[row][move.getColumn()] = move.getPlayer();
                break loop;
            }
        }
    }

    // if the board contains a winning position, returns the Player that wins.
    // Otherwise, returns null.  You could ignore lastMove.
    public Player winner(Move lastMove) {
        if(colWinner(lastMove) || rowWinner(lastMove) || (diagonalWinner(lastMove))){
        return lastMove.getPlayer();
        } else{
        return null;
        }
    }

    public boolean colWinner(Move lastMove){
        Player x = lastMove.getPlayer();
        int run = 0;
        for(int row = 0; row < rows; row++){
            if(grid[row][lastMove.getColumn()] == x){
                run++;
            } else{
                run = 0;
            }
            if(run == 4){
                return true;
            }
        }
        return false;
    }

    public boolean rowWinner(Move lastMove){
        Player x = lastMove.getPlayer();
        int run = 0;
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(grid[row][col] == x){
                    run++;
                } else{
                    run = 0;
                }
                if(run == 4)
                return true;
            }
        }
        return false;
    }

    public boolean diagonalWinner(Move lastMove){
        Player p = lastMove.getPlayer();
        int run = 0;
        for(int row = 1; row < rows; row++){
            for(int x = 0; x <= row; x++){
                if(grid[row-x][x] == p){
                    run++;
                } else{
                    run = 0;
                }
                if(run == 4){
                    return true;
                }
            }
        }
    }
    
    
} // end Board class
