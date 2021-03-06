package engine.ui;

import java.util.HashMap;

import engine.scene;
import engine.window;
import processing.core.PConstants;
import processing.core.PGraphics;

public class slider extends UIelement {
    float maxVal, minVal;
    float slider = 0;
    boolean clicked;
    public UItheme colors;

    public slider(int x, int y, int sizex, int sizey, float minVal, float maxVal, scene s, window w, String name) {
        super(x, y, sizex, sizey, s, w, name);
        this.maxVal = maxVal;
        this.minVal = minVal;
        colors = window.getUItheme();

    }

    // public slider

    public slider(int x, int y, int sizex, int sizey, scene s, window w, String name) {
        this(x, y, sizex, sizey, 0, 1, s, w, name);
        colors = window.getUItheme();

    }

    public float getVal() {
        if (slider == 0) {
            return 0;
        }
        // Float value = (slider / sizex) * (maxVal - minVal) + minVal;
        Float value=mapRange(0,sizex,minVal,maxVal,slider);

        return value;

        // return (float) 0.5;
    }

    public static float mapRange(double a1, double a2, double b1, double b2, double s) {
        return (float) (b1 + ((s - a1) * (b2 - b1)) / (a2 - a1));
    }

    @Override
    public void draw(PGraphics b) {

        b.noStroke();
        b.fill(colors.c_light);
        b.rect(x, y + sizey / 2, sizex, 4, 2);
        float pos = mapRange(minVal, maxVal, 0, sizex, slider);
        pos=slider;
        b.fill(colors.c_hover);
        b.rect(x, y + sizey / 2, pos, 4, 2);

        // Hover
        if (hover()) {
            b.fill(colors.c_hover);
            if (clicked) {
                b.fill(colors.c_hover, 100);
            } else {
                b.fill(colors.c_hover, 50);
            
            }
            b.ellipse(pos + x, y + sizey / 2, sizey, sizey);
            b.fill(colors.c_hover);
            b.ellipse(pos + x, y + sizey / 2, sizey - 8, sizey - 8);
        }
        // Normal
        else {
            b.noStroke();
            b.fill(colors.c_hover);
            b.ellipse(pos + x, y + sizey / 2, sizey - 8, sizey - 8);
        }

    }

    @Override
    public void update(window w) {
        if(clicked){
            if (w.input.Mouse.left) {
                clicked = true;
                if (w.mouseX >= x && w.mouseX <= x + sizex && w.mouseY >= y && w.mouseY <= y + sizey) {
                    slider = w.mouseX-x;
                    slider = slider<=0? 1: slider;
                }
            }
        }

    }

    @Override
    public void click() {
        // mapRange(minVal, maxVal, 0, sizex,slider);
        if (w.input.Mouse.left) {
            clicked = true;
            if (w.mouseX >= x && w.mouseX <= x + sizex && w.mouseY >= y && w.mouseY <= y + sizey) {
                slider = w.mouseX-x;
                slider = slider<=0? 1: slider;

            }
        }
    }

    @Override
    public void ValUpdate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void key() {
        // TODO Auto-generated method stub

    }

}
