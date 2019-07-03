public class MapGrid {

    public static final int PADDING = 10;

    private int maxCol;
    private int maxRow;

    protected Cell[][] cellGrid;
    public Cursor cursor;


    public MapGrid (int maxCol, int maxRow){
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        this.cellGrid = new Cell[maxCol][maxRow];
        makeCellMap();
        this.cursor = new Cursor(0,0);
    }

    public void makeCellMap() {

        for (int i = 0; i < maxCol; i++){

            for (int j = 0; j < maxRow; j++){

                cellGrid[i][j] = new Cell (i, j);
            }
        }
    }


    public Cursor getCursor() {
        return this.cursor;
    }


    public String write() {

        String savedState = "";

        for (Cell[] cellArray : cellGrid) {

            for (Cell c : cellArray) {

                if (c.rectangle.isFilled()) {

                    savedState += "1";
                    continue;
                }
                savedState += "0";
            }
        }
        System.out.println(savedState);
        return savedState;
    }



}















