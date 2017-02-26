package pl.edu.pg.ftims.projekt;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
/**
 * The class contains segments and methods of moving the snake.
 *
 */
public class Snake {
	private List<Segment> segments = new ArrayList<Segment>();
	private Segment lastSegment;
	private int maxX, maxY, xPanel, yPanel;
	private int length;
	private int size;

	enum Course {
		North, South, East, West, Last;
	}

	Course course; // course where snake moves
	Course lastCourse;

	public Snake(int maxX, int maxY, int size) {
		this.maxX = maxX;
		this.maxY = maxY;
		this.size = size; //size of panel
		xPanel = maxX - size * 20;
		yPanel = maxX - size * 20;

		Segment segment0 = new Segment(xPanel + 2 * size, yPanel);
		Segment segment1 = new Segment(xPanel + size, yPanel);
		Segment segment2 = new Segment(xPanel, yPanel);

		segments.add(segment0);
		segments.add(segment1);
		segments.add(segment2);

		course = Course.East;
		lastCourse = Course.East;
		length = 3;
		lastSegment = new Segment(xPanel - size, yPanel);
	}

	public void draw(Graphics2D graphics2d) {
		
		for(Segment x: segments){
			x.draw(graphics2d);
		}
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).draw(graphics2d);
		}
	}

	void setCourse(Course newCourse) {

		// (newCourse == Course.Last) ? course = lastCourse : course =
		// newCourse;

		if (newCourse == Course.Last) {
			course = lastCourse; // if another button was pushed, do not change
									// the course
		} else {
			course = newCourse;
		}

	}

	Course getCourse() {
		return course;
	}

	Course getLastCourse() {
		return lastCourse;
	}

	public int getLength() {
		return length;
	}

	public List<Segment> getSegments() {
		return segments;

	}
	
	public int getSize() {
		return size;
	}

	public boolean ifAlive() {
		boolean alive = true;
		for (int i = 2; i < length; i++) {
			if ((segments.get(0).getX() == segments.get(i).getX())
					&& (segments.get(0).getY() == segments.get(i).getY())) {
				alive = false;
				break;
			}
		}
		return alive;
	}

	private void goEast() {
		if (lastCourse != Course.West) {
			moveBodyOfSnake();

			if (segments.get(0).getX() == (maxX - size)) { 	// if snake goes outside the background,
															// it appears on the top of the background
				segments.get(0).setX(10);
			} else {
				segments.get(0).setX(segments.get(0).getX() + size);
			}
			lastCourse = Course.East;
		} else {
			goWest();
		}
	}

	private void goWest() {
		if (lastCourse != Course.East) {
			moveBodyOfSnake();

			if (segments.get(0).getX() == xPanel) {
				segments.get(0).setX(maxX - size);
			} else {
				segments.get(0).setX(segments.get(0).getX() - size);
			}
			lastCourse = Course.West;
		} else {

			goEast();
		}

	}

	private void goSouth() {
		if (lastCourse != Course.North) {
			moveBodyOfSnake();

			if (segments.get(0).getY() == (maxY - size)) {
				segments.get(0).setY(yPanel);
			} else {
				segments.get(0).setY(segments.get(0).getY() + size);
			}
			lastCourse = Course.South;
		} else {
			goNorth();
		}
	}

	private void goNorth() {
		if (lastCourse != Course.South) {
			moveBodyOfSnake();

			if (segments.get(0).getY() == yPanel) {
				segments.get(0).setY(maxX - size);
			} else {
				segments.get(0).setY(segments.get(0).getY() - size);
			}
			lastCourse = Course.North;
		} else {
			goSouth();
		}
	}

	public void moveSnake() {

		if (course == Course.North) {
			goNorth();
		} else if (course == Course.South) {
			goSouth();
		} else if (course == Course.East) {
			goEast();
		} else if (course == Course.West) {
			goWest();
		} else {
		}

		/*
		 * switch (course){ case North: //if course is set to right, snake moves
		 * right goNorth(); break;
		 * 
		 * case South: //if course is set to left, snake moves left goSouth();
		 * break;
		 * 
		 * case East: //if course is set down, snake moves down goEast(); break;
		 * 
		 * case West: //if course is set up, snake moves up goWest(); break;
		 * 
		 * default: //if course is different, do nothing break; }
		 */
	}

	public void moveBodyOfSnake() {

		lastSegment = segments.get(length - 1);

		for (int i = length - 1; i > 0; i--) {
			segments.get(i).setX(segments.get(i - 1).getX());
			segments.get(i).setY(segments.get(i - 1).getY());
		}
	}

	public boolean eatAndGrow(Food myFood) {
		if ((segments.get(0).getX() == myFood.getX()) && (segments.get(0).getY() == myFood.getY())) {
			Segment newSegment = new Segment(lastSegment.getX(), lastSegment.getY());
			segments.add(newSegment);
			length = segments.size();
			// Toolkit.getDefaultToolkit().beep();
			return true;
		} else {
			return false;
		}
	}

}

