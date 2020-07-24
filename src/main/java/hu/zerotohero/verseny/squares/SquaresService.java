package hu.zerotohero.verseny.squares;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;


@Service
public class SquaresService {
    ArrayList<List<Point>> foundSquares;

    public int findPoints(List<Point> points) {
        foundSquares = new ArrayList<>();
        for (int firstIndex = 0; firstIndex < points.size() - 3; firstIndex++) {
            Point firstPoint = points.get(firstIndex);
            for (int secondIndex = firstIndex + 1; secondIndex < points.size() - 2; secondIndex++) {
                Point secondPoint = points.get(secondIndex);
                for (int thirdIndex = secondIndex + 1; thirdIndex < points.size() - 1; thirdIndex++) {
                    Point thirdPoint = points.get(thirdIndex);
                    // If three points don't form two equal sides of a square, skip the third point
                    if (pointsFormPotentialSquare(firstPoint, secondPoint, thirdPoint)) {
                        for (int fourthIndex = thirdIndex + 1; fourthIndex < points.size(); fourthIndex++) {
                            Point fourthPoint = points.get(fourthIndex);
                            // If three points already form two sides of a square,
                            // its enough to check if all four are on a circle
                            if (pointsOnCircle(firstPoint, secondPoint, thirdPoint, fourthPoint)) {
                                List<Point> square = new ArrayList<>();
                                square.add(firstPoint);
                                square.add(secondPoint);
                                square.add(thirdPoint);
                                square.add(fourthPoint);
                                if (!squareAlreadyFound(square)) {
                                    foundSquares.add(square);
                                }
                            }
                        }
                    }
                }
            }
        }
        return foundSquares.size();
    }

    boolean squareAlreadyFound(List<Point> points) {
        for (List<Point> foundPoints : foundSquares) {
            if (foundPoints.containsAll(points)) {
                return true;
            }
        }
        return false;
    }

    boolean pointsOnCircle(Point p1, Point p2, Point p3, Point p4) {
        double centerX = (p1.x + p2.x + p3.x + p4.x) / 4.0;
        double centerY = (p1.y + p2.y + p3.y + p4.y) / 4.0;

        double radiusP1 = p1.distanceSq(centerX, centerY);
        double radiusP2 = p2.distanceSq(centerX, centerY);
        double radiusP3 = p3.distanceSq(centerX, centerY);
        double radiusP4 = p4.distanceSq(centerX, centerY);

        return radiusP1 == radiusP2 && radiusP1 == radiusP3 && radiusP1 == radiusP4;
    }

    private boolean pointsFormPotentialSquare(Point p1, Point p2, Point p3) {
        double d12 = p1.distanceSq(p2);
        double d13 = p1.distanceSq(p3);
        double d23 = p2.distanceSq(p3);

        return d12 == d13 || d12 == d23 || d13 == d23;
    }
}