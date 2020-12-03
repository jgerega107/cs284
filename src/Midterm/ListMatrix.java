package Midterm;

import java.util.ArrayList;
import java.util.List;

public class ListMatrix extends ListCollection<Integer> {
  private int rows;
  private int columns;

  /**
   * Initializes a `ListMatrix` with the specified number of rows and columns. By
   * default, ALL elements are set to 0.
   * 
   * @param rows
   * @param columns
   */
  public ListMatrix(int rows, int columns) {
    super();
    this.rows = rows;
    this.columns = columns;
    List<Integer> zeroList = new ArrayList<>();
    for(int i = 0; i < columns; i++){
      zeroList.add(0);
    }
    for(int i = 0; i < rows; i++){
      super.addList(zeroList);
    }
    super.setNodeCount(rows*columns);
  }

  /**
   * @return the number of rows
   */
  public int numRows() {
    return this.rows;
  }


  /**
   * 
   * @return the number of columns
   */
  public int numColumns() {
    return this.columns;
  }

  /**
   * Adds the `ListMatrix` to `ListMatrix other`, storing the result in the caller
   * (this)
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @complexity Runtime N^2: There are two for loops, one nested in the other, both incrementing at a constant rate and with statements inside of a constant runtime.
   */
  public void add(ListMatrix other) {
    if(!(other.columns == columns && other.rows == rows)){
      throw new IllegalArgumentException("Dimensions of matrices do not match.");
    }
    for(int row = 0; row < collection.size(); row++){
      for(int col = 0; col < collection.get(row).size(); col++){
        this.setElem(row, col, this.getElem(row, col) + other.getElem(row, col));
      }
    }
  }

  /**
   * Returns the transpose of the matrix
   * 
   * @param matrix
   * @return matrix tranpose
   */
  public static ListMatrix transpose(ListMatrix matrix) {
    ListMatrix matrix1 = new ListMatrix(matrix.columns, matrix.rows);
    for(int row = 0; row < matrix1.rows; row++){
      for(int col = 0; col < matrix1.columns; col++){
        matrix1.setElem(row, col, matrix.getElem(col, row));
      }
    }
    return matrix1;
  }

  /**
   * Multiplies the `ListMatrix` with `ListMatrix other`, returning the result as
   * a new `ListMatrix.
   * 
   * @throws IllegalArgumentException if dimensions do not peoperly coincide
   * @param other
   * @return
   */
  public ListMatrix multiply(ListMatrix other) {
    if(!(other.columns == columns && other.rows == rows)){
      throw new IllegalArgumentException("Dimensions of matrices do not match.");
    }
    ListMatrix matrix1 = new ListMatrix(other.rows, other.columns);
    for(int row = 0; row < collection.size(); row++){
      for(int col = 0; col < collection.get(row).size(); col++){
        matrix1.setElem(row, col, this.getElem(row, col) * other.getElem(row, col));
      }
    }
    return matrix1;
  }

  public static void main(String[] args) {
    ListMatrix matrix = new ListMatrix(3, 4);
    matrix.setElem(1, 3, 6);
    matrix.setElem(2, 1, 6);
    matrix.setElem(0, 2, 6);
    matrix.setElem(0, 3, 2);
    ListMatrix matrix2 = new ListMatrix(3, 4);
    matrix2.setElem(1, 3, 3);
    matrix2.setElem(2, 1, 9);
    matrix2.setElem(0, 2, 32);
    matrix2.setElem(0, 3, 5);
    System.out.println(matrix.toString());
    System.out.println(matrix2.toString());
    System.out.println(matrix);
    System.out.println(matrix.multiply(matrix2));
  }

}
