package interfaces;


/**
 * Interface for a square on the chess board
 */
public interface ISquare {
    /**
     * Gets rank of square
     * @return
     * rank (row index)
     */
    int getRank();

    /**
     * Gets file of square
     * @return
     * file (column index)
     */
    int getFile();

}
