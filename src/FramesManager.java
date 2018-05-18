import java.util.ArrayList;

public class FramesManager {
    private ArrayList<Frame> frames;
    private int turn;

    // constructor
    public FramesManager() {
        this.turn = 0;
        this.frames = new ArrayList<Frame>(10);
        for (int i = 0; i < 9; i++) {
            this.frames.add(new Frame());
        }
        this.frames.add(new LastFrame());
    }

    // this function update the frame according given pins
    public void addRoll(int pins) {
        this.frames.get(this.turn).makeRoll(pins);
        // if current frame ended, update previous frames score
        Frame currentFrame = this.frames.get(turn);
        if (currentFrame.isFrameEnd()) {
            if (turn > 0) {
                Frame prevFrame = this.frames.get(turn-1);

                // spare
                if (prevFrame.getType() == FrameType.SPARE) {
                    prevFrame.bonusScore(currentFrame.getThrow1());
                    prevFrame.finishScore();
                }
                // strike
                else if (prevFrame.getType() == FrameType.STRIKE) {
                    prevFrame.bonusScore(currentFrame.getThrow1());
                    if (currentFrame.getType() != FrameType.STRIKE) {
                        prevFrame.bonusScore(currentFrame.getThrow2());
                        prevFrame.finishScore();
                    }
                }
            }
            if (turn > 1) {
                if (!frames.get(turn - 2).isFinishScore()) {
                    this.frames.get(turn - 2).bonusScore(currentFrame.getThrow1());
                    this.frames.get(turn - 2).finishScore();
                }
            }
            this.turn++;
        }
    }

    // this functions summarise the score of all frames
    public int getScore() {
        int score = 0;
        for (Frame frame : frames) {
            score += frame.getScore();
        }
        return score;
    }

    // this function return true if game is over
    public boolean isGameEnd() {
        if (this.frames.get(9).isFinishScore()) {
            return true;
        }
        return false;
    }

}
