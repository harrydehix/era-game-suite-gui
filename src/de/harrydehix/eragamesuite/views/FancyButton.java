package de.harrydehix.eragamesuite.views;

import de.harrydehix.eragamesuite.GameFonts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FancyButton extends JButton implements MouseListener {
    private Color mousePressedTextColor, textColor, mousePressedBackgroundColor, backgroundColor, mouseHoveredBackgroundColor, mouseHoveredTextColor;
    private boolean hover, pressed;

    public FancyButton(String text, Color backgroundColor, Color textColor) {
        this(text, backgroundColor, textColor, backgroundColor, textColor, backgroundColor, textColor);
    }

    public FancyButton(String text, Color backgroundColor, Color textColor, Color mousePressedBackgroundColor, Color mousePressedTextColor) {
        this(text, backgroundColor, textColor, mousePressedBackgroundColor, mousePressedTextColor, backgroundColor, textColor);
    }

    public FancyButton(String text, Color backgroundColor, Color textColor, Color mousePressedBackgroundColor, Color mousePressedTextColor, Color mouseHoveredBackgroundColor, Color mouseHoveredTextColor) {
        super(text);
        setForeground(textColor);
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        setFont(GameFonts.BUTTON_FONT);
        setFocusPainted(false);
        addMouseListener(this);
        this.mousePressedTextColor = mousePressedTextColor;
        this.textColor = textColor;
        this.mousePressedBackgroundColor = mousePressedBackgroundColor;
        this.backgroundColor = backgroundColor;
        this.mouseHoveredBackgroundColor = mouseHoveredBackgroundColor;
        this.mouseHoveredTextColor = mouseHoveredTextColor;

        this.addActionListener((e) -> {
            hover = false;
            pressed = false;
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        setContentAreaFilled(false);
        setOpaque(true);
        setForeground(mousePressedTextColor);
        setBackground(mousePressedBackgroundColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressed = false;
        setContentAreaFilled(true);
        if (hover) {
            setForeground(mouseHoveredTextColor);
            setBackground(mouseHoveredBackgroundColor);
        }
        else {
            setForeground(textColor);
            setBackground(backgroundColor);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        hover = true;
        if (pressed) {
            return;
        }
        setForeground(mouseHoveredTextColor);
        setBackground(mouseHoveredBackgroundColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        hover = false;
        if (pressed) {
            setContentAreaFilled(false);
            setOpaque(true);
            setForeground(mousePressedTextColor);
            setBackground(mousePressedBackgroundColor);
        }
        else {
            setContentAreaFilled(true);
            setForeground(textColor);
            setBackground(backgroundColor);
        }
    }
}
