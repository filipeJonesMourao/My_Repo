package org.academiadecodigo.codeforall.simplegfx;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Line;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class SimpleGfxGrid {

    public static final int PADDING = 10;
    private int distance = 0;

    public static final int WIDTH = 750;
    public static final int HEIGHT = 600;

    public static final int COLS = 16;
    public static final int ROWS = 10;

    private int width = 50;
    private int height = 50;

    private Rectangle grid = new Rectangle(PADDING,PADDING, WIDTH, HEIGHT);

    private Rectangle columns;
    private Rectangle rows;

    public SimpleGfxGrid(){

        grid.draw();

        while (distance < 660){

            distance += 50;
            columns = new Rectangle((PADDING + distance),PADDING, width, HEIGHT);
            columns.draw();
        }
        distance = 0;
        while(distance < 510){

            distance += 50;
            rows  = new Rectangle(PADDING, (PADDING + distance), WIDTH, height);
            rows.draw();
        }
    }

}
