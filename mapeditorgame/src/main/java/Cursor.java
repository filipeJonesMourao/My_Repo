import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;


public class Cursor extends Cell {

    public Cursor (int col, int row) {

        super(col, row);
        this.rectangle = new Rectangle(this.col + MapGrid.PADDING + 1, this.row + MapGrid.PADDING + 1, SIZE - 1, SIZE - 1);
        this.rectangle.setColor(Color.ORANGE);
        this.rectangle.fill();
    }

    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }
}
