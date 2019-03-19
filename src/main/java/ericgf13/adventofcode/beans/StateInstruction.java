package ericgf13.adventofcode.beans;


import ericgf13.adventofcode.enums.Direction;

public class StateInstruction {

    private int writeVal0;
    private int writeVal1;
    private Direction moveDir0;
    private Direction moveDir1;
    private String nextState0;
    private String nextState1;

    public void setWriteVal(int val, String writeVal) {
        if (val == 0) {
            this.writeVal0 = Integer.parseInt(writeVal);
        } else {
            this.writeVal1 = Integer.parseInt(writeVal);
        }
    }

    public void setMoveDir(int val, Direction moveDir) {
        if (val == 0) {
            this.moveDir0 = moveDir;
        } else {
            this.moveDir1 = moveDir;
        }
    }

    public void setNextState(int val, String nextState) {
        if (val == 0) {
            this.nextState0 = nextState;
        } else {
            this.nextState1 = nextState;
        }
    }

    public Integer getWriteVal(int val) {
        if (val == 0) {
            return writeVal0;
        }
        return writeVal1;
    }

    public Direction getMoveDir(int val) {
        if (val == 0) {
            return moveDir0;
        }
        return moveDir1;
    }

    public String getNextState(int val) {
        if (val == 0) {
            return nextState0;
        }
        return nextState1;
    }
}
