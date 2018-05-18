public class LastFrame extends Frame {
    private int throw3;
    private FrameType type;

    public LastFrame() {
        super();
        super.setThrow2(-1);
        this.throw3 = 0;
        this.type = FrameType.NORMAL;
    }

    @Override
    public void makeRoll(int pins) {
        super.bonusScore(pins);

        // if this is first try
        if (super.getThrow1() == -1) {
            super.setThrow1(pins);
            if (pins == Game.MAX) {
                this.type = FrameType.STRIKE;
            }
        }

        // if this is second try
        else if (super.getThrow2() == -1) {
            super.setThrow2(pins);
            // spare
            if (this.type == FrameType.NORMAL && super.getThrow1() + super.getThrow2() == 10) {
                this.type = FrameType.SPARE;
            }
            // normal
            else if (this.type == FrameType.NORMAL) {
                super.frameEnd();
                super.finishScore();
            }
        }

        // if this is third try
        else {
            super.frameEnd();
            super.finishScore();
        }
    }
}
