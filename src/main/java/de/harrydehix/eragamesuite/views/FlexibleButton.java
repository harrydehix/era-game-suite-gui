package de.harrydehix.eragamesuite.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class FlexibleButton extends JButton implements MouseListener, FocusListener {
    private Color pressedTextColor;
    private Color textColor;
    private Color pressedBackgroundColor;
    private Color backgroundColor;
    private Color hoveredBackgroundColor;
    private Color hoveredTextColor;
    private Color focusedBackgroundColor;
    private Color focusedTextColor;
    private boolean hovered, pressed, focused;
    private ArrayList<? extends FlexibleButton> focusList;

    public FlexibleButton(String content, Color backgroundColor, Color textColor, Color pressedBackgroundColor, Color pressedTextColor, Color hoveredBackgroundColor, Color hoveredTextColor, Color focusedBackgroundColor, Color focusedTextColor) {
        super(content);
        setForeground(textColor);
        setBackground(backgroundColor);
        setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        setFocusPainted(false);
        addMouseListener(this);
        addFocusListener(this);
        this.pressedTextColor = pressedTextColor;
        this.textColor = textColor;
        this.pressedBackgroundColor = pressedBackgroundColor;
        this.backgroundColor = backgroundColor;
        this.hoveredBackgroundColor = hoveredBackgroundColor;
        this.hoveredTextColor = hoveredTextColor;
        this.focusedBackgroundColor = focusedBackgroundColor;
        this.focusedTextColor = focusedTextColor;
    }

    public void addFocusList(ArrayList<? extends FlexibleButton> focusList) {
        this.focusList = focusList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        enablePressedLook();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        disablePressedLook();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        enableHoveredLook();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        disableHoveredLook();
    }

    @Override
    public void focusGained(FocusEvent e) {
        enableFocusLook();
    }

    @Override
    public void focusLost(FocusEvent e) {
        disableFocusLook();
    }

    private void defaultLook() {
        setForeground(textColor);
        setBackground(backgroundColor);
    }

    public void enableHoveredLook() {
        hovered = true;
        if (!pressed && !focused) {
            hoverLook();
        }
    }

    public void disableHoveredLook() {
        hovered = false;
        if (pressed) {
            pressedLook();
        }
        else if (focused) {
            focusLook();
        }
        else {
            defaultLook();
        }
    }

    private void hoverLook() {
        setForeground(hoveredTextColor);
        setBackground(hoveredBackgroundColor);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void enablePressedLook() {
        pressed = true;
        pressedLook();
    }

    public void disablePressedLook() {
        pressed = false;
        if (focused) {
            focusLook();
        }
        else if (hovered) {
            hoverLook();
        }
        else {
            defaultLook();
        }
    }

    private void pressedLook() {
        setForeground(pressedTextColor);
        setBackground(pressedBackgroundColor);
        setContentAreaFilled(false);
        setOpaque(true);
    }

    public void enableFocusLook() {
        focused = true;
        if (!pressed) {
            focusLook();
        }
        if (focusList != null) {
            for (FlexibleButton button : focusList) {
                if (button != this) {
                    button.disableFocusLook();
                }
            }
        }
    }

    public void disableFocusLook() {
        focused = false;
        if (pressed) {
            pressedLook();
        }
        else if (hovered) {
            hoverLook();
        }
        else {
            defaultLook();
        }
    }

    private void focusLook() {
        setForeground(focusedTextColor);
        setBackground(focusedBackgroundColor);
    }

    public Color getPressedTextColor() {
        return pressedTextColor;
    }

    public void setPressedTextColor(Color pressedTextColor) {
        this.pressedTextColor = pressedTextColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public Color getPressedBackgroundColor() {
        return pressedBackgroundColor;
    }

    public void setPressedBackgroundColor(Color pressedBackgroundColor) {
        this.pressedBackgroundColor = pressedBackgroundColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getHoveredBackgroundColor() {
        return hoveredBackgroundColor;
    }

    public void setHoveredBackgroundColor(Color hoveredBackgroundColor) {
        this.hoveredBackgroundColor = hoveredBackgroundColor;
    }

    public Color getHoveredTextColor() {
        return hoveredTextColor;
    }

    public void setHoveredTextColor(Color hoveredTextColor) {
        this.hoveredTextColor = hoveredTextColor;
    }

    public Color getFocusedBackgroundColor() {
        return focusedBackgroundColor;
    }

    public void setFocusedBackgroundColor(Color focusedBackgroundColor) {
        this.focusedBackgroundColor = focusedBackgroundColor;
    }

    public Color getFocusedTextColor() {
        return focusedTextColor;
    }

    public void setFocusedTextColor(Color focusedTextColor) {
        this.focusedTextColor = focusedTextColor;
    }
}
