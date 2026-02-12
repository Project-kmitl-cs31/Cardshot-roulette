package Item;

enum TurnNext {
    SAME_PLAYER,
    OTHER_PLAYER
}
class ActionResult {
    private boolean turnEnds;
    private TurnNext nextTurnPlayer;

    public ActionResult(boolean turnEnds, TurnNext nextTurnPlayer) {
        this.turnEnds = turnEnds;
        this.nextTurnPlayer = nextTurnPlayer;
    }
    public static ActionResult continueTurn() {
        return new ActionResult(false, TurnNext.SAME_PLAYER);
    }

    public static ActionResult endTurnSwitch() {
        return new ActionResult(true, TurnNext.OTHER_PLAYER);
    }

    public static ActionResult endTurnKeepTurn() {
        return new ActionResult(true, TurnNext.SAME_PLAYER);
    }
}

