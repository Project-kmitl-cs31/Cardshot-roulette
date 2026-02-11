package Nut;

enum TurnNext {
    SAME_PLAYER,
    OTHER_PLAYER
}

class ActionResult {
    private boolean turnEnds;
    private TurnNext nextTurnPlayer;
    private String message ;

    public ActionResult(boolean turnEnds, TurnNext nextTurnPlayer, String message) {
        this.turnEnds = turnEnds;
        this.nextTurnPlayer = nextTurnPlayer;
        this.message = message;
    }
    public static ActionResult continueTurn(String msg) {
        return new ActionResult(false, TurnNext.SAME_PLAYER, msg);
    }

    public static ActionResult endTurnSwitch(String msg) {
        return new ActionResult(true, TurnNext.OTHER_PLAYER, msg);
    }

    public static ActionResult endTurnKeepTurn(String msg) {
        return new ActionResult(true, TurnNext.SAME_PLAYER, msg);
    }
}


