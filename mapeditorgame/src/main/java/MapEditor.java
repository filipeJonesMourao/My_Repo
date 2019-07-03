import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.mouse.Mouse;
import org.academiadecodigo.simplegraphics.mouse.MouseEvent;
import org.academiadecodigo.simplegraphics.mouse.MouseEventType;
import org.academiadecodigo.simplegraphics.mouse.MouseHandler;

import java.io.*;

import static java.lang.Enum.valueOf;
import static org.academiadecodigo.simplegraphics.mouse.MouseEventType.MOUSE_CLICKED;

public class MapEditor implements KeyboardHandler, MouseHandler {

    private Cursor cursor;
    private MapGrid map;
    private int maxCol;
    private int maxRow;
    private Keyboard keyboard;
    private Mouse mouse;
    private Direction direction;
    private String filepath = "/Users/codeforallsintra/Desktop/Projects/Repositories/class_repo/solutions/oop/mapeditorgame/src/main/resources/saved-state.txt";
    private boolean paintingOn;

    protected MapEditor(int maxCol, int maxRow) {

        this.maxCol = maxCol;
        this.maxRow = maxRow;
        init();
    }

    private void init() {

        this.map = new MapGrid(maxCol, maxRow);
        this.cursor = map.getCursor();
        this.keyboard = new Keyboard(this);
        keyboardInit();
        this.mouse = new Mouse(this);
        mouseInit();
    }


    private void keyboardInit() {

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent paint = new KeyboardEvent();
        paint.setKey(KeyboardEvent.KEY_SPACE);
        paint.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent clear = new KeyboardEvent();
        clear.setKey(KeyboardEvent.KEY_C);
        clear.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent save = new KeyboardEvent();
        save.setKey(KeyboardEvent.KEY_S);
        save.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent load = new KeyboardEvent();
        load.setKey(KeyboardEvent.KEY_L);
        load.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent dark = new KeyboardEvent();
        dark.setKey(KeyboardEvent.KEY_D);
        dark.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
        keyboard.addEventListener(paint);
        keyboard.addEventListener(clear);
        keyboard.addEventListener(save);
        keyboard.addEventListener(load);
        keyboard.addEventListener(dark);

    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_SPACE:
                changePaintingStatus();
                break;

            case KeyboardEvent.KEY_C:
                clear();
                break;

            case KeyboardEvent.KEY_S:
                save();
                break;

            case KeyboardEvent.KEY_L:
                load();
                break;

            case KeyboardEvent.KEY_A:
                dark();
                break;

            case KeyboardEvent.KEY_D:
                dark();
                break;

            case KeyboardEvent.KEY_LEFT:
                direction = Direction.LEFT;
                paint();
                move(direction);
                break;

            case KeyboardEvent.KEY_RIGHT:
                direction = Direction.RIGHT;
                paint();
                move(direction);
                break;

            case KeyboardEvent.KEY_UP:
                direction = Direction.UP;
                paint();
                move(direction);
                break;

            case KeyboardEvent.KEY_DOWN:
                direction = Direction.DOWN;
                paint();
                move(direction);
                break;
        }
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }


    public enum Direction {

        UP,
        DOWN,
        LEFT,
        RIGHT
    }


    private void move(Direction d) {

        direction = null;

        switch (d) {

            case UP:
                if (cursor.getRow() == 0) {
                    break;
                }
                cursor.setRow(cursor.getRow() - 1);
                cursor.rectangle.translate(0, -1 * Cell.SIZE);
                break;

            case DOWN:
                if (cursor.getRow() == maxRow - 1) {
                    break;
                }
                cursor.setRow(cursor.getRow() + 1);
                cursor.rectangle.translate(0, Cell.SIZE);
                break;

            case LEFT:
                if (cursor.getCol() == 0) {
                    break;
                }
                cursor.setCol(cursor.getCol() - 1);
                cursor.rectangle.translate(-1 * Cell.SIZE, 0);
                break;

            case RIGHT:
                if (cursor.getCol() == maxCol - 1) {
                    break;
                }
                cursor.setCol(cursor.getCol() + 1);
                cursor.rectangle.translate(Cell.SIZE, 0);
                break;

            default:
                break;
        }
    }


    private void paint() {

        int x = cursor.getCol();
        int y = cursor.getRow();

        if (this.map.cellGrid[x][y].rectangle.isFilled() && !paintingOn) {

            this.map.cellGrid[x][y].rectangle.draw();
            return;
        }

        if (!this.map.cellGrid[x][y].rectangle.isFilled() && paintingOn) {
            this.map.cellGrid[x][y].rectangle.fill();
        }
    }

    private void mousePaint(int x, int y) {

        if (this.map.cellGrid[x][y].rectangle.isFilled() && !paintingOn) {

            this.map.cellGrid[x][y].rectangle.draw();
            return;
        }

        if (!this.map.cellGrid[x][y].rectangle.isFilled() && paintingOn) {
            this.map.cellGrid[x][y].rectangle.fill();
        }
    }


    private void changePaintingStatus() {

        if (!paintingOn) {
            paintingOn = true;
            return;
        }
        paintingOn = false;
    }


    private void dark() {

        for (Cell[] cellArray : this.map.cellGrid) {

            for (Cell c : cellArray) {

                c.rectangle.fill();
            }
        }
    }


    private void clear() {

        for (Cell[] cellArray : this.map.cellGrid) {

            for (Cell c : cellArray) {

                if (c.rectangle.isFilled()) {
                    c.rectangle.draw();
                }
            }
        }
    }


    private void save() {

        String savedState = map.write();

        FileWriter fileWriter = null;

        try {

            fileWriter = new FileWriter(filepath);

            fileWriter.write(savedState);

            fileWriter.close();

        } catch (IOException e) {

            System.err.println(e.getMessage());

        } finally {

            try {

                fileWriter.close();

            } catch (IOException e) {

                System.err.println(e.getMessage());
            }
        }
    }


    private String readFile() {

        String loadedState = "";

        FileReader fileReader;
        BufferedReader bufferedReader = null;

        try {

            fileReader = new FileReader(filepath);
            bufferedReader = new BufferedReader(fileReader);

            loadedState = bufferedReader.readLine();

        } catch (IOException e) {

            System.err.println(e.getMessage());

        } finally {

            try {

                if (bufferedReader.readLine() == null) {

                    bufferedReader.close();
                }

            } catch (IOException e) {

                System.err.println(e.getMessage());
            }
        }
        return loadedState;
    }


    private void load() {

        Cell[][] oldGrid = this.map.cellGrid;
        int cellCounter = 0;

        for (int i = 0; i < this.maxRow; i++) {

            for (int j = 0; j < this.maxCol; j++) {

                String test = readFile();

                if (test.charAt(cellCounter) == '1') {

                    System.out.println(readFile().charAt(j));

                    oldGrid[i][j].rectangle.fill();

                } else {

                    System.out.println(readFile().charAt(j));
                    oldGrid[i][j].rectangle.draw();
                }

                cellCounter++;
            }
        }
    }


    ///////////////////
    // MOUSE ATTEMPT //
    ///////////////////


    private void mouseInit() {

        MouseEvent move = new MouseEvent(MapGrid.PADDING, MapGrid.PADDING, MouseEventType.MOUSE_MOVED);
        MouseEvent click = new MouseEvent(MapGrid.PADDING, MapGrid.PADDING, MouseEventType.MOUSE_CLICKED);

        mouse.addEventListener(MouseEventType.MOUSE_MOVED);
        mouse.addEventListener(click.getEventType());
    }


    @Override
    public void mouseClicked(MouseEvent click) {

        System.out.println("Clicking");


        /*int x = (int) Math.floor((event.getX() + MapGrid.PADDING) / Cell.SIZE);
        int y = (int) Math.floor((event.getY() + MapGrid.PADDING) / Cell.SIZE);*/

        int x = (int) Math.floor(click.getX() / Cell.SIZE) - 1;
        int y = (int) Math.floor(click.getY() / Cell.SIZE) - 2;

        System.out.println(x + " " + y);

        //mouseChangeCursor(x, y);
        mousePaint(x, y);

    }


    public void mouseChangeCursor(int x, int y) {


        cursor.rectangle.delete();
        //cursor.setCol(adjustedX);
        //cursor.setRow(adjustedY);
        cursor = new Cursor(x, y);

    }


    public void mouseMoved(MouseEvent MOUSE_MOVED) {


    }


}
