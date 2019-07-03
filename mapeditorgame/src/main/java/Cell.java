import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Cell {

    protected Rectangle rectangle;
    protected int col;
    protected int row;

    protected static final int SIZE = 15;


    protected Cell(int col, int row){

        this.col = col * SIZE;
        this.row = row * SIZE;
        this.rectangle = new Rectangle(this.col + MapGrid.PADDING, this.row + MapGrid.PADDING, SIZE, SIZE);
        rectangle.draw();
    }

    protected int getRow() {
        return this.row;
    }
    protected int getCol() {
        return this.col;
    }








}
