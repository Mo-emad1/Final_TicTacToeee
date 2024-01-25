
import java.awt.Graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author me89x
 */
public class EmptyState implements SlotState{
    
    @Override
            public void draw(Graphics g, int x, int y) {
            }
            @Override
            public String toString() {
                return "Empty";
            }
    
}
