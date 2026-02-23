package src.Nut1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainmenuScreen extends UIScreen{
    private JButton btnStart;
    private JButton btnExit;
    private JLabel bgImage;
    private JLabel titleImage; // เพิ่มตัวแปรสำหรับเก็บรูปชื่อเกม

    public MainmenuScreen(UIManager ui){
        super(ui);
        this.setLayout(null);
        
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = screensize.width;
        int h = screensize.height;

        JLayeredPane lp = new JLayeredPane();
        lp.setBounds(0,0,w,h);
        
        // ---------------------------------------------------------
        // 1. จัดการรูปโลโก้ / ชื่อเกม (ด้านบนสุด)
        // ---------------------------------------------------------
        // ** อย่าลืมเปลี่ยนชื่อไฟล์ "image/title_game.png" ให้ตรงกับไฟล์รูปของคุณ **
        ImageIcon titleIcon = new ImageIcon("image/title.png"); 
        // ปรับขนาดโลโก้ตามความเหมาะสม (ในตัวอย่างตั้งกว้าง 400, สูง 150)
        Image titleImg = titleIcon.getImage().getScaledInstance(800, 300, Image.SCALE_SMOOTH);
        
        titleImage = new JLabel(new ImageIcon(titleImg));
        // จัดให้อยู่กึ่งกลางหน้าจอ (w/2 - ครึ่งหนึ่งของความกว้างรูป) 
        // และให้อยู่สูงกว่าปุ่ม Start (h/2 - 180)
        titleImage.setBounds((w/2) - 390, (h/2) - 450, 800, 300);


        // ---------------------------------------------------------
        // 2. จัดการปุ่ม START (ตรงกลาง)
        // ---------------------------------------------------------
        ImageIcon startIcon = new ImageIcon("image/start_button.png"); 
        Image startImg = startIcon.getImage().getScaledInstance(500, 160, Image.SCALE_SMOOTH);

        btnStart = new JButton(new ImageIcon(startImg));
        btnStart.setBounds((w/2) - 250 , (h/2) + 50 , 500 , 160);
        
        btnStart.setContentAreaFilled(false); 
        btnStart.setBorderPainted(false);
        btnStart.setFocusPainted(false);
        btnStart.setOpaque(false);
        
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ui.openGameScreen();
            }
        });

        ImageIcon exitIcon = new ImageIcon("image/Exit_button_.png"); 
        Image exitImg = exitIcon.getImage().getScaledInstance(500, 160, Image.SCALE_SMOOTH);

        btnExit = new JButton(new ImageIcon(exitImg));
        btnExit.setBounds((w/2)- 250, (h/2) + 130 , 500, 160);
        
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setOpaque(false);
        
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        // ---------------------------------------------------------
        // 4. จัดการรูปภาพพื้นหลัง (Background)
        // ---------------------------------------------------------
        ImageIcon icon = new ImageIcon("image/War.png");
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        bgImage = new JLabel(icon);
        bgImage.setBounds(0,0,w,h);

        // เพิ่ม Component ลงใน LayeredPane
        lp.add(bgImage, Integer.valueOf(0));     // พื้นหลังอยู่ชั้นล่างสุด
        lp.add(titleImage, Integer.valueOf(1));  // โลโก้เกมอยู่ชั้นบน
        lp.add(btnStart, Integer.valueOf(1));    // ปุ่ม Start อยู่ชั้นบน
        lp.add(btnExit, Integer.valueOf(1));     // ปุ่ม Exit อยู่ชั้นบน
        
        this.add(lp);
        this.setBounds(0,0,w,h);
    }

    @Override
    public void show() { 
        this.setVisible(true);
    }

    @Override
    public void hide() {
        this.setVisible(false); 
    }

    @Override
    public void render(Game state) {
       
    }
}