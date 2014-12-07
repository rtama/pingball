package ui;

import java.awt.Color;
import java.awt.Shape;


public class ColoredShape {
	
	private final Shape shape;
	private final Color color;
	
	public ColoredShape(Shape shape, Color color){
		this.shape = shape;
		this.color = color;
	}
	
	/**
	 *
	 * @return the shape of this colored shape
	 */
	public Shape getShape(){
		return shape;
	}
	
	/**
	 * 
	 * @return the color of this colored shape
	 */
	public Color getColor() {
		return color;
	}

}
