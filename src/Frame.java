enum FrameType { NORMAL, SPARE, STRIKE };

public class Frame {

    private int throw1;
    private int throw2;
    private int score;
    private FrameType type;
    private boolean isFrameEnd;
    private boolean finishScore;

    public Frame() {
        this.throw1 = -1;
        this.throw2 = 0;
        this.score = 0;
        this.type = FrameType.NORMAL;
        this.isFrameEnd = false;
        this.finishScore = false;
    }

    // this function add the score of pins
    public void makeRoll(int pins) {
        this.score += pins;
        // if this is first try
        if (this.throw1 == -1) {
            this.throw1 = pins;
            if (pins == Game.MAX) {
                this.type = FrameType.STRIKE;
                this.isFrameEnd = true;
            }
        }
        // if this is second try
        else {
            this.throw2 = pins;
            if (this.score == Game.MAX) {
                this.type = FrameType.SPARE;
            } else {
                this.finishScore();
            }
            this.isFrameEnd = true;
        }
    }

    public boolean isFrameEnd() {
        return this.isFrameEnd;
    }

    public void bonusScore(int num) {
        this.score += num;
    }

    public void finishScore() {
        this.finishScore = true;
    }

    public boolean isFinishScore() {
        return this.finishScore;
    }

    public int getScore() {
        if (!this.finishScore) {
            return 0;
        }
        return this.score;
    }

    public int getThrow1() {
        return this.throw1;
    }

    public int getThrow2() {
        return this.throw2;
    }

    public void setThrow1(int t) {
        this.throw1 = t;
    }

    public void setThrow2(int t) {
        this.throw2 = t;
    }

    public FrameType getType() {
        return this.type;
    }

    public void frameEnd() {
        this.isFrameEnd = true;
    }
}
