package src.Nut1;

public class test {
    public static void main(String[] args) {
        Game game = new Game();
        UIManager uiManager = new UIManager();
        uiManager.bindGame(game);
        game.setUIManager(uiManager);
        uiManager.openMainMenu();

    }
}
