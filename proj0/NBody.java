public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
	int numPlanets = in.readInt();
	double radiusUniv = in.readDouble();
	return radiusUniv;
    }
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
	int numPlanets = in.readInt();
	double radiusUniv = in.readDouble();
	Planet[] planets = new Planet[numPlanets];
	int index = 0;
	while (index < numPlanets) {
	    double xP = in.readDouble();
	    double yP = in.readDouble();
	    double xV = in.readDouble();
	    double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
	    img = "images/" + img;
	    // System.out.print(xP + " " + yP + " " + xV + " " + yV + " " + m + " " + img);
	    // System.out.print("\n");
	    planets[index] = new Planet(xP, yP, xV, yV, m, img);
	    index = index + 1;
	}
	return planets;
    }
    /* The method that simulates the given universe. */
    public static void main(String[] args) {
	/* First, parse the given command line arguments
	 * 0th as T
	 * 1st as dt
	 * 2nd as filename which gives the universe to simulate. */
	double T = Double.parseDouble(args[0]);
	double dt = Double.parseDouble(args[1]);
	String filename = args[2];
	
	/* Then, get the file describes the universe according to given filename
	 * and read radius of the universe and planets from the file. */
	double radius = readRadius(filename);
	Planet[] planets = readPlanets(filename);

	/* Enables double buffering. */
	StdDraw.enableDoubleBuffering();

	/* set time variable to 0. */
	double time = 0;

	/** Create a loop to loop until the time variable is T.
          * every pass going through the loop body, redraw the universe. */
	while (time < T) {
	    double[] xForces = new double[planets.length];
	    double[] yForces = new double[planets.length];
	    
	    /* store the net x and y forces for each planet in
	     * xForces and yForces array respectively. */ 
	    for (int i = 0; i < planets.length; i += 1) {
	        xForces[i] = planets[i].calcNetForceExertedByX(planets);
		yForces[i] = planets[i].calcNetForceExertedByY(planets);
	    }
	   
            /* update every planet with new position and velocity. */
	    for (int j = 0; j < planets.length; j += 1) {
	        planets[j].update(dt, xForces[j], yForces[j]);
	    }

	    /* Then begin to draw things.
	     * First, set the scale of the universe and draw the background. */
	    StdDraw.setScale(-radius, radius);
	    StdDraw.clear();
	    StdDraw.picture(0, 0, "images/starfield.jpg");
	    
	    /* draw the planets. */
	    for (Planet p : planets) {
	        p.draw();
	    }
	    
	    /* show the drawing to the onscreen canvas. */
	    StdDraw.show();
	    StdDraw.pause(1);

	    /* increase time variable by dt. */
	    time += dt;
	}
	System.out.println(planets.length);
	System.out.println(radius);
	for (Planet p : planets) {
	    System.out.println(p.xxPos + " " + p.yyPos + " " + p.xxVel + " " + p.yyVel + " " + p.mass + " " + p.imgFileName);
	}
    }
}
