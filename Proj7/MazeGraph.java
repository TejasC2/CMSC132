package graph;

import graph.WeightedGraph;
import maze.Juncture;
import maze.Maze;

/**
 * <P>
 * The MazeGraph is an extension of WeightedGraph. The constructor converts a
 * Maze into a graph.
 * </P>
 */
public class MazeGraph extends WeightedGraph<Juncture> {

	/*
	 * STUDENTS: SEE THE PROJECT DESCRIPTION FOR A MUCH MORE DETAILED EXPLANATION
	 * ABOUT HOW TO WRITE THIS CONSTRUCTOR
	 */

	/**
	 * <P>
	 * Construct the MazeGraph using the "maze" contained in the parameter to
	 * specify the vertices (Junctures) and weighted edges.
	 * </P>
	 * 
	 * <P>
	 * The Maze is a rectangular grid of "junctures", each defined by its X and Y
	 * coordinates, using the usual convention of (0, 0) being the upper left
	 * corner.
	 * </P>
	 * 
	 * <P>
	 * Each juncture in the maze should be added as a vertex to this graph.
	 * </P>
	 * 
	 * <P>
	 * For every pair of adjacent junctures (A and B) which are not blocked by a
	 * wall, two edges should be added: One from A to B, and another from B to A.
	 * The weight to be used for these edges is provided by the Maze. (The Maze
	 * methods getMazeWidth and getMazeHeight can be used to determine the number of
	 * Junctures in the maze. The Maze methods called "isWallAbove",
	 * "isWallToRight", etc. can be used to detect whether or not there is a wall
	 * between any two adjacent junctures. The Maze methods called "getWeightAbove",
	 * "getWeightToRight", etc. should be used to obtain the weights.)
	 * </P>
	 * 
	 * @param maze to be used as the source of information for adding vertices and
	 *             edges to this MazeGraph.
	 */
	public MazeGraph(Maze maze) {
		//store width and height
		int x = maze.getMazeWidth();
		int y = maze.getMazeHeight();
		//create vertexes based on width and height
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				try {
					Juncture juc = new Juncture(i,j);
					this.addVertex(juc);
				} catch (IllegalArgumentException e) {

				}
			}
		}
		//add junctures based on walls going back and forth between edge
		//try catch exceptions with each addEdge call
		for (int k = 0; k < x; k++) {
			for (int l = 0; l < y; l++) {
				if (maze.isWallAbove(new Juncture(k, l)) == false) {
					try {
						this.addEdge(new Juncture(k, l), new Juncture(k, l-1),
								maze.getWeightAbove(new Juncture(k, l)));
						this.addEdge(new Juncture(k, l-1), new Juncture(k, l),
								maze.getWeightBelow(new Juncture(k, l-1)));
					} catch (IllegalArgumentException e) {

					}
				}
				if (maze.isWallToRight(new Juncture(k, l)) == false) {
					try {

						this.addEdge(new Juncture(k, l), new Juncture(k + 1, l),
								maze.getWeightToRight(new Juncture(k, l)));
						this.addEdge(new Juncture(k + 1, l), new Juncture(k, l),
								maze.getWeightToLeft(new Juncture(k+1, l)));
					} catch (IllegalArgumentException e) {

					}
				}
				if (maze.isWallToLeft(new Juncture(k, l)) == false) {
					try {

						this.addEdge(new Juncture(k, l), new Juncture(k - 1, l),
								maze.getWeightToLeft(new Juncture(k, l)));
						this.addEdge(new Juncture(k - 1, l), new Juncture(k, l),
								maze.getWeightToRight(new Juncture(k-1, l)));
					} catch (IllegalArgumentException e) {

					}
				}
				if (maze.isWallBelow(new Juncture(k, l)) == false) {
					try {
						this.addEdge(new Juncture(k, l), new Juncture(k, l +1),
								maze.getWeightBelow(new Juncture(k, l)));
						this.addEdge(new Juncture(k, l + 1), new Juncture(k, l),
								maze.getWeightAbove(new Juncture(k, l+1)));
					} catch (IllegalArgumentException e) {

					}

				}
			}
		}

	}
}
