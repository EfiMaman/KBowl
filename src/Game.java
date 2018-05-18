public class Game {
    public static final int MAX = 10;

    private FramesManager framesManager;
    private int lastThrow;
    private boolean isFirstTry;

    public Game() {
        this.framesManager = new FramesManager();
        this.lastThrow = 0;
        this.isFirstTry = true;
    }

    // this method is called for each roll with number of pins knocked down
    public void roll(int pins) {
        // check the input
        if (isRollLegal(pins)) {
            this.framesManager.addRoll(pins);
            rollDone(pins);
        }
        // illegal roll
        else {
            System.out.println("Illegal roll");
        }
    }

    // this method returns the total score for that game
    public int score() {
        return this.framesManager.getScore();
    }

    // this method called when roll is done and update the relevant variables
    private void rollDone(int pins) {
        if (this.isFirstTry) {
            if (pins < Game.MAX) {
                lastThrow = pins;
                isFirstTry = false;
            }
        } else {
            lastThrow = 0;
            this.isFirstTry = true;
        }
    }

        // this function return true if the given roll is legal

    private boolean isRollLegal(int pins) {
        if (pins < 0 || pins > Game.MAX) {
            return false;
        } else if (!this.isFirstTry && lastThrow + pins > Game.MAX) {
            return false;
        } else if (this.framesManager.isGameEnd()) {
            return false;
        }
        return true;
    }
}
