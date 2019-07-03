package org.academiadecodigo.bootcamp.gfx.simplegfx;

import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.AbstractGridPosition;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    //private Rectangle rectangle;
    private Rectangle rectangle;
    private SimpleGfxGrid simpleGfxGrid;

    /**
     * Simple graphics position constructor
     *
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(SimpleGfxGrid grid) {
        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);

        simpleGfxGrid = grid;
        //Rectangle
        rectangle = new Rectangle(grid.columnToX(getCol()), grid.rowToY(getRow()), grid.getCellSize(), grid.getCellSize());
        rectangle.setColor(Color.BLUE);
        show();
    }

    /**
     * Simple graphics position constructor
     *
     * @param col  position column
     * @param row  position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid) {
        super(col, row, grid);

        simpleGfxGrid = grid;

        rectangle = new Rectangle(grid.columnToX(col), grid.rowToY(row), grid.getCellSize(), grid.getCellSize());
        rectangle.setColor(Color.BLUE);
        show();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        rectangle.fill();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
        rectangle.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {

/*
        int dx=0;
        int dy=0;

        if (direction == GridDirection.UP) dy=-1;
        if (direction == GridDirection.RIGHT) dx=1;
        if (direction == GridDirection.LEFT) dx=-1;
        if (direction == GridDirection.DOWN) dy=1;

*/
        int oldX = rectangle.getX();
        int oldY = simpleGfxGrid.rowToY(this.getRow());

        super.moveInDirection(direction, distance);

       int dCol =  simpleGfxGrid.columnToX(this.getCol()) - oldX;
       int dRow = simpleGfxGrid.rowToY(this.getRow()) - oldY;





     //  rectangle.translate(dx*distance,dy*distance);
     rectangle.translate(dCol, dRow);
    }

    /**
     * @see AbstractGridPosition#setColor(GridColor)
     */
    @Override
    public void setColor(GridColor color) {
        super.setColor(color);
        rectangle.setColor(SimpleGfxColorMapper.getColor(color));
    }
}
