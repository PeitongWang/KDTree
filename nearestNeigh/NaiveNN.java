package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to be implemented. Naive approach implementation.
 *
 */
public class NaiveNN implements NearestNeigh {

	ArrayList<Point> pointsArr;

	@Override
	public void buildIndex(List<Point> points) {

		pointsArr = (ArrayList<Point>) points;

	}

	@Override
	public List<Point> search(Point searchTerm, int k) {

		ArrayList<Point> resultPoints = new ArrayList<Point>();

		Point[] points = new Point[pointsArr.size()];

		for (int i = 0; i < pointsArr.size(); i++) {

			points[i] = pointsArr.get(i);

		}

		for (int i = 0; i < points.length - 1; i++) {

			for (int j = 0; j < points.length - 1 - i; j++) {

				Point temp = null;

				if (searchTerm.distTo(points[j]) > searchTerm.distTo(points[j + 1])) {

					temp = points[j];
					points[j] = points[j + 1];
					points[j + 1] = temp;

				}

			}

		}

		if (points.length >= k) {

			for (int i = 0; i < k; i++) {

				resultPoints.add(points[i]);

			}

		} else if (points.length < k) {

			for (int i = 0; i < points.length; i++) {

				resultPoints.add(points[i]);

			}

		}

		return resultPoints;
	}

	@Override
	public boolean addPoint(Point point) {

		if (pointsArr.contains(point)) {

			return false;

		}

		pointsArr.add(point);

		return true;
	}

	@Override
	public boolean deletePoint(Point point) {

		if (pointsArr.contains(point)) {

			pointsArr.remove(point);

			return true;

		}

		return false;
	}

	@Override
	public boolean isPointIn(Point point) {

		if (pointsArr.contains(point)) {

			return true;

		}

		return false;
	}

}
