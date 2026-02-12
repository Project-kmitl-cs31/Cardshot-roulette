package logic;
import javax.swing.JPanel;

public abstract class UIScreen extends JPanel {
    protected UIManager ui;
    
    public UIScreen(UIManager ui) { 
        this.ui = ui;
     }
    public abstract void show();
    public abstract void hide();
    public abstract void render(Game state);
}  
