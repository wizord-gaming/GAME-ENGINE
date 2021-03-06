package engine;

import java.util.HashMap;

import engine.ui.UItheme;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.event.MouseEvent;

public abstract class window extends PApplet {

	public static UItheme uitheme = UItheme.Singleton();

	public input input = new input(this);
	public HashMap<String, scene> screens = new HashMap<>();
	public String selected;
	public loader Loader = new loader(this);

	public static UItheme getUItheme() {
		return uitheme;
	}

	@Override
	abstract public void settings();

	@Override
	abstract public void setup();

	abstract public void update();

	abstract public void keyUpdate();

	abstract public void mouseClick();

	@Override
	abstract public void mouseWheel();

	public void addScene(scene s) {
		screens.put(s.id, s);
	}

	public scene getScene(String s) {
		return screens.get(s);
	}

	public scene getSelectedScene() {
		return screens.get(selected);
	}

	public void selectScene(String s) {
		selected = s;
	}

	@Override
	public void draw() {
		background(uitheme.c_dark);
		update();
		screens.get(selected).update();
		PGraphics frame = createGraphics(width, height);
		frame.beginDraw();
		frame.background(uitheme.c_dark);
		screens.get(selected).draw(frame);
		frame.endDraw();
		image(frame, 0, 0);
	}

	@Override
	public void keyPressed() {
		input.set(key, true);
		screens.get(selected).key();
		keyUpdate();
	}

	@Override
	public void keyReleased() {
		input.set(key, false);
		screens.get(selected).key();
		keyUpdate();
	}

	@Override
	public void mousePressed() {
		input.Mouse.press(mouseButton, true);
		screens.get(selected).click();
		mouseClick();
	}

	@Override
	public void mouseReleased() {
		input.Mouse.press(mouseButton, false);
		screens.get(selected).click();
		mouseClick();
	}

	@Override
	public void mouseWheel(MouseEvent event) {
		float e = event.getCount();
		input.Mouse.scroll(e);
		mouseWheel();
	}

}
