/*
 * The MIT License
 *
 * Copyright 2015 Ben Lloyd.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.shobute.arbigo.play;

import com.shobute.arbigo.common.Colour;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Ben Lloyd
 */
public class SideBar extends JPanel implements ActionListener {

    private Timer timer;
    private JButton jButtonResign;
    private FramePlay framePlay;

    public SideBar(final FramePlay framePlay) {
        this.framePlay = framePlay;
        
        setLayout(new BorderLayout());

        jButtonResign = new JButton();
        jButtonResign.setText("Resign");
        add(jButtonResign, BorderLayout.SOUTH);
        jButtonResign.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int option = JOptionPane.showConfirmDialog(framePlay,
                        "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option == YES_OPTION) {
                    framePlay.resign();
                }
            }
        });

        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // Clears the graphic.
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (framePlay.isGameOver()) {
            Color colour = framePlay.getColour();
            g2d.setColor(colour);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            
            g2d.setColor(Colour.getContranstColour(colour));
            String winner = framePlay.getPlayer().getName() + " wins!";
            g2d.drawString(winner, 5, 15);
        } else {
            String turn = framePlay.getPlayer().getName() + "'s turn";
            g2d.drawString(turn, 5, 15);

            String time = framePlay.getPlayer().getTime() + "s remaining";
            g2d.drawString(time, 5, 35);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        framePlay.getPlayer().decrementTime();
        if (framePlay.getPlayer().getTime() == 0) {
            framePlay.resign();
        }
        
        if (framePlay.isGameOver()) {
            timer.stop();
            jButtonResign.setEnabled(false);
        }
        
        repaint();
    }

}
