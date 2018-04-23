import java.util.Random;

class App {

    int mines;
    int width;
    int height;
    char[][] table;

    public App(int width, int height, int mines) {

        this.height = height;
        this.width = width;
        this.mines = mines;
        this.table = new char[height][width];
    }

    public static void main(String[] args) {
        App app = new App(10, 10, 20);

        app.createMap();
        app.drawMap();
    }

    public void createMap() {
        for(int y = 0; y < height; y ++) {
            for(int x = 0; x < width; x ++) {
                table[y][x] = '.';
            }
        }
        placeMines();
        for(int y = 0; y < height; y ++) {
            for(int x = 0; x < width; x ++) {
                if (table[y][x] == '.') {
                    table[y][x]=minesNear(y, x);
                }
            }
        }
    }

    public void drawMap() {
        for(int y = 0; y < height; y ++) {
            for(int x = 0; x < width; x ++) {
                System.out.print(table[y][x]);
            }
            System.out.print("\n");
        }
    }

    private void placeMines() {
        int minesPlaced = 0;
        Random random = new Random();
        while(minesPlaced < mines) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            if(table[y][x] != '*') {
                table[y][x] = '*';
                minesPlaced++;
            }
        }
    }

    private char minesNear(int y, int x) {
        int mines = 0;
        mines += mineAt(y - 1, x - 1);
        mines += mineAt(y - 1, x);
        mines += mineAt(y - 1, x + 1);
        mines += mineAt(y, x - 1);
        mines += mineAt(y, x + 1);
        mines += mineAt(y + 1, x - 1);
        mines += mineAt(y + 1, x);
        mines += mineAt(y + 1, x + 1);
        if(mines > 0) {
            return (char)(mines + 48);
        } else {
            return '0';
        }
    }

    private int mineAt(int y, int x) {
        if(y >= 0 && y < height && x >= 0 && x < width && table[y][x] == '*') {
            return 1;
        } else {
            return 0;
        }
    }
}