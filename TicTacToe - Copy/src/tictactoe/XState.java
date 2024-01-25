
import java.awt.Color;
import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author me89x
 */
public class XState implements SlotState {
    @Override
            public void draw(Graphics g, int x, int y) {
                g.setColor(Color.green);
                g.drawLine(x * 150 + 20, y * 150 + 20, x * 150 + 130, y * 150 + 130);
                g.drawLine(x * 150 + 130, y * 150 + 20, x * 150 + 20, y * 150 + 130);
            }

            @Override
            public String toString() {
                return "X";
            }
    
}
