package swings;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author Mohamed
 */
public class JMyButton extends JButton {
    public JMyButton(){
        setOpaque(false);
        setContentAreaFilled(false);
        setForeground(Color.white);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        g.setColor(new Color(70,130,180));
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, 5);
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g){
        g.setColor(Color.BLUE);
        g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 5, 5);
    }
}
