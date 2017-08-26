package nearestNeigh;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is required to be implemented. Kd-tree implementation.
 *
 */
public class KDTreeNN implements NearestNeigh {

	Node node = null;
	ArrayList<Point> pointsArr;

	@Override
	public void buildIndex(List<Point> points) {
		node = new Node();

		pointsArr = (ArrayList<Point>) points;
		double xVariance = 0;
		double yVariance = 0;
		double[] xValue = new double[pointsArr.size()];
		double[] yValue = new double[pointsArr.size()];

		for (int i = 0; i < pointsArr.size(); i++) {

			xValue[i] = pointsArr.get(i).lat;

		}

		xVariance = Tool.variance(xValue);

		for (int i = 0; i < pointsArr.size(); i++) {

			yValue[i] = pointsArr.get(i).lon;

		}

		yVariance = Tool.variance(yValue);

		if (xVariance > yVariance) {

			node.dim = 0;

		} else if (xVariance < yVariance) {

			node.dim = 1;

		} else if (xVariance == yVariance && xVariance != 0) {

			node.dim = 0;

		} else if (xVariance == 0 && yVariance == 0) {

			node.point.lat = xValue[0];
			node.point.lon = yValue[0];
			return;

		}

		if (node.dim == 0) {

			Point[] pointArray = new Point[pointsArr.size()];

			for (int i = 0; i < pointsArr.size(); i++) {

				pointArray[i] = pointsArr.remove(i);

			}

			for (int i = 0; i < pointArray.length - 1; i++) {

				for (int j = 0; j < pointArray.length - i - 1; j++) {

					if (pointArray[j].lat > pointArray[j + 1].lat) {

						Point temp = pointArray[j];
						pointArray[j] = pointArray[j + 1];
						pointArray[j + 1] = temp;
					}
				}
			}

			for (int i = 0; i < pointArray.length; i++) {

				pointsArr.add(pointArray[i]);

			}

			pointArray = null;

			Point midPoint = Tool.getMidPoint(pointsArr);

			node.dimValue = midPoint.lat;
			node.point = midPoint;
			ArrayList<Point> leftPoints = new ArrayList<Point>();
			ArrayList<Point> rightPoints = new ArrayList<Point>();
			for (int i = 0; i < pointsArr.size(); i++) {

				if (pointsArr.get(i).lat < node.dimValue) {

					leftPoints.add(pointsArr.get(i));

				} else if (pointsArr.get(i).lat > node.dimValue) {

					rightPoints.add(pointsArr.get(i));

				}
			}

			node.leftNode = new Node();
			node.rightNode = new Node();

			buildTree(node.leftNode, leftPoints, node.dim);
			buildTree(node.rightNode, rightPoints, node.dim);

		} else if (node.dim == 1) {

			Point[] pointArray = new Point[pointsArr.size()];

			for (int i = 0; i < pointsArr.size(); i++) {

				pointArray[i] = pointsArr.remove(i);

			}

			for (int i = 0; i < pointArray.length - 1; i++) {

				for (int j = 0; j < pointArray.length - i - 1; j++) {

					if (pointArray[j].lon > pointArray[j + 1].lon) {

						Point temp = pointArray[j];
						pointArray[j] = pointArray[j + 1];
						pointArray[j + 1] = temp;
					}
				}
			}

			for (int i = 0; i < pointArray.length; i++) {

				pointsArr.add(pointArray[i]);

			}

			pointArray = null;

			Point midPoint = Tool.getMidPoint(pointsArr);

			node.dimValue = midPoint.lon;
			node.point = midPoint;
			ArrayList<Point> leftPoints = new ArrayList<Point>();
			ArrayList<Point> rightPoints = new ArrayList<Point>();
			for (int i = 0; i < pointsArr.size(); i++) {

				if (pointsArr.get(i).lon < node.dimValue) {

					leftPoints.add(pointsArr.get(i));

				} else if (pointsArr.get(i).lon > node.dimValue) {

					rightPoints.add(pointsArr.get(i));

				}
			}

			node.leftNode = new Node();
			node.rightNode = new Node();

			buildTree(node.leftNode, leftPoints, node.dim);
			buildTree(node.rightNode, rightPoints, node.dim);

		}
	}

	public void buildTree(Node node, ArrayList<Point> points, int mark) {

		if (mark == 0) {

			node.dim = 1;

		} else if (mark == 1) {

			node.dim = 0;

		}

		if (node.dim == 0) {

			Point[] pointArray = new Point[points.size()];

			for (int i = 0; i < points.size(); i++) {

				pointArray[i] = points.remove(i);

			}

			for (int i = 0; i < pointArray.length - 1; i++) {

				for (int j = 0; j < pointArray.length - i - 1; j++) {

					if (pointArray[j].lat > pointArray[j + 1].lat) {

						Point temp = pointArray[j];
						pointArray[j] = pointArray[j + 1];
						pointArray[j + 1] = temp;
					}
				}
			}

			for (int i = 0; i < pointArray.length; i++) {

				points.add(pointArray[i]);

			}

			pointArray = null;

			Point midPoint = Tool.getMidPoint(points);

			node.dimValue = midPoint.lat;
			node.point = midPoint;
			ArrayList<Point> leftPoints = new ArrayList<Point>();
			ArrayList<Point> rightPoints = new ArrayList<Point>();
			for (int i = 0; i < points.size(); i++) {

				if (points.get(i).lat < node.dimValue) {

					leftPoints.add(points.get(i));

				} else if (points.get(i).lat > node.dimValue) {

					rightPoints.add(points.get(i));

				}
			}

			node.leftNode = new Node();
			node.rightNode = new Node();

			buildTree(node.leftNode, leftPoints, node.dim);
			buildTree(node.rightNode, rightPoints, node.dim);

		} else if (node.dim == 1) {

			Point[] pointArray = new Point[points.size()];

			for (int i = 0; i < points.size(); i++) {

				pointArray[i] = points.remove(i);

			}

			for (int i = 0; i < pointArray.length - 1; i++) {

				for (int j = 0; j < pointArray.length - i - 1; j++) {

					if (pointArray[j].lon > pointArray[j + 1].lon) {

						Point temp = pointArray[j];
						pointArray[j] = pointArray[j + 1];
						pointArray[j + 1] = temp;
					}
				}
			}

			for (int i = 0; i < pointArray.length; i++) {

				points.add(pointArray[i]);

			}

			pointArray = null;

			Point midPoint = Tool.getMidPoint(points);

			node.dimValue = midPoint.lon;
			node.point = midPoint;
			ArrayList<Point> leftPoints = new ArrayList<Point>();
			ArrayList<Point> rightPoints = new ArrayList<Point>();
			for (int i = 0; i < points.size(); i++) {

				if (points.get(i).lon < node.dimValue) {

					leftPoints.add(points.get(i));

				} else if (points.get(i).lon > node.dimValue) {

					rightPoints.add(points.get(i));

				}
			}

			node.leftNode = new Node();
			node.rightNode = new Node();

			buildTree(node.leftNode, leftPoints, node.dim);
			buildTree(node.rightNode, rightPoints, node.dim);

		}

	}

	@Override
	public List<Point> search(Point searchTerm, int k) {
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Point> receivePoints = new ArrayList<Point>();
		nodes.add(node);

		if (!(node.leftNode == null && node.rightNode == null)) {

			if (node.dim == 0) {

				if (searchTerm.lat < node.dimValue) {

					if (node.leftNode != null) {

						receivePoints = searchNode(nodes, node.leftNode, searchTerm, k, points);

					}
				} else if (searchTerm.lat > node.dimValue) {

					if (node.rightNode != null) {

						receivePoints = searchNode(nodes, node.rightNode, searchTerm, k, points);
					}

				}

			} else if (node.dim == 1) {

				if (searchTerm.lon < node.dimValue) {

					if (node.leftNode != null) {

						receivePoints = searchNode(nodes, node.leftNode, searchTerm, k, points);

					}

				} else if (searchTerm.lon > node.dimValue) {

					if (node.rightNode != null) {

						receivePoints = searchNode(nodes, node.rightNode, searchTerm, k, points);

					}

				}

			}

		} else if (node.leftNode == null && node.rightNode == null) {

			receivePoints.add(node.point);

		}
		return receivePoints;
	}

	public ArrayList<Point> searchNode(ArrayList<Node> nodes, Node node, Point searchTerm, int k,
			ArrayList<Point> points) {

		nodes.add(node);

		if (!(node.leftNode == null && node.rightNode == null)) {

			if (node.dim == 0) {

				if (searchTerm.lat < node.dimValue) {

					if (node.leftNode != null) {

						searchNode(nodes, node.leftNode, searchTerm, k, points);

					}
				} else if (searchTerm.lat > node.dimValue) {

					if (node.rightNode != null) {

						searchNode(nodes, node.rightNode, searchTerm, k, points);
					}

				}

			} else if (node.dim == 1) {

				if (searchTerm.lon < node.dimValue) {

					if (node.leftNode != null) {

						searchNode(nodes, node.leftNode, searchTerm, k, points);

					}

				} else if (searchTerm.lon > node.dimValue) {

					if (node.rightNode != null) {

						searchNode(nodes, node.rightNode, searchTerm, k, points);

					}

				}

			}

		} else if (node.leftNode == null && node.rightNode == null) {

			if (k == 1) {

				Point tempPoint = node.point;

				for (int i = nodes.size() - 2; i >= 0; i--) {

					if (nodes.get(i).leftNode != null && nodes.get(i).rightNode != null) {

						if (searchTerm.distTo(nodes.get(i).point) < searchTerm.distTo(tempPoint)) {

							tempPoint = nodes.get(i).point;

						}

						if (nodes.get(i).dim == 0) {

							if (Math.abs(searchTerm.lat - nodes.get(i).point.lat) < searchTerm
									.distTo(nodes.get(i).point)) {

								if (searchTerm.lat > nodes.get(i).point.lat) {

									if (searchTerm.distTo(nodes.get(i).leftNode.point) < searchTerm.distTo(tempPoint)) {

										tempPoint = nodes.get(i).leftNode.point;

									}

								} else if (searchTerm.lat < nodes.get(i).point.lat) {

									if (searchTerm.distTo(nodes.get(i).rightNode.point) < searchTerm
											.distTo(tempPoint)) {

										tempPoint = nodes.get(i).rightNode.point;

									}

								}

							}

						} else if (nodes.get(i).dim == 1) {

							if (Math.abs(searchTerm.lon - nodes.get(i).point.lon) < searchTerm
									.distTo(nodes.get(i).point)) {

								if (searchTerm.lon > nodes.get(i).point.lon) {

									if (searchTerm.distTo(nodes.get(i).leftNode.point) < searchTerm.distTo(tempPoint)) {

										tempPoint = nodes.get(i).leftNode.point;

									}

								} else if (searchTerm.lon < nodes.get(i).point.lon) {

									if (searchTerm.distTo(nodes.get(i).rightNode.point) < searchTerm
											.distTo(tempPoint)) {

										tempPoint = nodes.get(i).rightNode.point;

									}

								}

							}

						}

					} else {

						if (searchTerm.distTo(nodes.get(i).point) < searchTerm.distTo(tempPoint)) {

							tempPoint = nodes.get(i).point;

						}

					}

				}

				points.add(tempPoint);

			} else if (k > 1) {

				ArrayList<Node> tempNodes = new ArrayList<Node>();

				for (int i = nodes.size() - 1; i >= 0; i--) {

					tempNodes.add(nodes.get(i));

					if (nodes.get(i).leftNode != null && nodes.get(i).rightNode != null) {

						tempNodes.add(nodes.get(i).leftNode);
						tempNodes.add(nodes.get(i).rightNode);

					}
				}

				Node[] nodesArr = new Node[tempNodes.size()];

				for (int i = 0; i < tempNodes.size(); i++) {

					nodesArr[i] = tempNodes.get(i);

				}

				for (int i = 0; i < nodesArr.length - 1; i++) {

					for (int j = 0; j < nodesArr.length - 1 - i; j++) {

						Node temp = null;

						if (searchTerm.distTo(nodesArr[j].point) > searchTerm.distTo(nodesArr[j + 1].point)) {

							temp = nodesArr[j];
							nodesArr[j] = nodesArr[j + 1];
							nodesArr[j + 1] = temp;

						}

					}

				}

				if (nodesArr.length >= k) {

					for (int i = 0; i < k; i++) {

						points.add(nodesArr[i].point);

					}

				} else if (nodesArr.length < k) {

					for (int i = 0; i < nodesArr.length; i++) {

						points.add(nodesArr[i].point);

					}

				}

			}

		}

		return points;

	}

	@Override
	public boolean addPoint(Point point) {

		boolean bool = false;

		if (isPointIn(point) == true) {

			return bool;

		}

		if (node.dim == 0) {

			if (point.lat < node.point.lat) {

				if (node.leftNode == null) {

					node.leftNode = new Node();

					node.leftNode.point = point;

					bool = true;

				} else if (node.leftNode != null) {

					bool = addNodeOfPoint(node.leftNode, point);

				}

			} else if (point.lat > node.point.lat) {

				if (node.rightNode == null) {

					node.rightNode = new Node();

					node.rightNode.point = point;

					bool = true;
					return bool;

				} else if (node.rightNode != null) {

					bool = addNodeOfPoint(node.rightNode, point);

				}

			}

		} else if (node.dim == 1) {

			if (point.lon < node.point.lon) {

				if (node.leftNode == null) {

					node.leftNode = new Node();

					node.leftNode.point = point;

					bool = true;

				} else if (node.leftNode != null) {

					bool = addNodeOfPoint(node.leftNode, point);

				}

			} else if (point.lon > node.point.lon) {

				if (node.rightNode == null) {

					node.rightNode = new Node();

					node.rightNode.point = point;

					bool = true;
					return bool;

				} else if (node.rightNode != null) {

					bool = addNodeOfPoint(node.rightNode, point);

				}

			}

		}

		return bool;
	}

	public boolean addNodeOfPoint(Node node, Point point) {

		boolean bool = false;

		if (node.dim == 0) {

			if (point.lat < node.point.lat) {

				if (node.leftNode == null) {

					node.leftNode = new Node();

					node.leftNode.point = point;

					bool = true;

				} else if (node.leftNode != null) {

					bool = addNodeOfPoint(node.leftNode, point);

				}

			} else if (point.lat > node.point.lat) {

				if (node.rightNode == null) {

					node.rightNode = new Node();

					node.rightNode.point = point;

					bool = true;
					return bool;

				} else if (node.rightNode != null) {

					bool = addNodeOfPoint(node.rightNode, point);

				}

			}

		} else if (node.dim == 1) {

			if (point.lon < node.point.lon) {

				if (node.leftNode == null) {

					node.leftNode = new Node();

					node.leftNode.point = point;

					bool = true;

				} else if (node.leftNode != null) {

					bool = addNodeOfPoint(node.leftNode, point);

				}

			} else if (point.lon > node.point.lon) {

				if (node.rightNode == null) {

					node.rightNode = new Node();

					node.rightNode.point = point;

					bool = true;
					return bool;

				} else if (node.rightNode != null) {

					bool = addNodeOfPoint(node.rightNode, point);

				}

			}

		}

		return bool;

	}

	@Override
	public boolean deletePoint(Point point) {

		boolean bool = false;

		if (isPointIn(point) == false) {

			return bool;

		}

		if (node.point.equals(point)) {

			if (node.leftNode == null && node.rightNode == null) {

				pointsArr.remove(node.point);

				bool = true;

			} else if (!(node.leftNode != null && node.rightNode != null)) {

				pointsArr.remove(node.point);
				Node newNode = new Node();
				newNode.dim = node.dim;
				node = newNode;
				buildTree(node, pointsArr, node.dim);

				bool = true;

			}

		}

		ArrayList<Point> leftPoints = new ArrayList<Point>();
		ArrayList<Point> rightPoints = new ArrayList<Point>();
		for (int i = 0; i < pointsArr.size(); i++) {

			if (pointsArr.get(i).lat < node.dimValue) {

				leftPoints.add(pointsArr.get(i));

			} else if (pointsArr.get(i).lat > node.dimValue) {

				rightPoints.add(pointsArr.get(i));

			}
		}

		if (deletePointNode(point, node.leftNode, leftPoints) == true
				|| deletePointNode(point, node.rightNode, rightPoints) == true) {

			bool = true;

		}

		return bool;

	}

	public boolean deletePointNode(Point point, Node node, ArrayList<Point> points) {

		boolean bool = false;

		if (node == null) {

			return bool;

		}

		if (node.point.equals(point)) {

			if (node.leftNode == null && node.rightNode == null) {

				pointsArr.remove(node.point);

				bool = true;
			}

		} else if (!(node.leftNode == null && node.rightNode == null)) {

			pointsArr.remove(node.point);
			points.remove(node.point);
			Node newNode = new Node();
			newNode.dim = node.dim;
			node = newNode;
			buildTree(node, points, node.dim);

			bool = true;

		}

		ArrayList<Point> leftPoints = new ArrayList<Point>();
		ArrayList<Point> rightPoints = new ArrayList<Point>();
		for (int i = 0; i < points.size(); i++) {

			if (points.get(i).lat < node.dimValue) {

				leftPoints.add(points.get(i));

			} else if (points.get(i).lat > node.dimValue) {

				rightPoints.add(points.get(i));

			}
		}

		if (deletePointNode(point, node.leftNode, leftPoints) == true
				|| deletePointNode(point, node.rightNode, rightPoints) == true) {

			bool = true;

		}

		return bool;

	}

	@Override
	public boolean isPointIn(Point point) {

		boolean bool;

		if (pointsArr.contains(point)) {

			bool = true;

		} else {

			bool = false;

		}

		return bool;
	}

}
