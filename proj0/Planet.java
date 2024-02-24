public class Planet {
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
	yyPos = yP;
	xxVel = xV;
	yyVel = yV;
	mass = m;
	imgFileName = img;
    }
    public Planet(Planet p) {
	xxPos = p.xxPos;
	yyPos = p.yyPos;
	xxVel = p.xxVel;
	yyVel = p.yyVel;
	mass = p.mass;
	imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
	double dy = p.yyPos - this.yyPos;
	double distance = Math.sqrt(dx * dx + dy * dy);
	return distance;
    }
    public double calcForceExertedBy(Planet p) {
        double m1 = this.mass;
        double m2 = p.mass;
	double r = this.calcDistance(p);
	double forceExertedBy = ((Planet.G * m1 * m2) / (r * r));
	return forceExertedBy;
    }
    public double calcForceExertedByX(Planet p) {
	double dx = p.xxPos - this.xxPos;
	double r = this.calcDistance(p);
	double forceExertedBy = this.calcForceExertedBy(p);
	double forceExertedByX = forceExertedBy * (dx / r);
	return forceExertedByX;
    }
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
	double r = this.calcDistance(p);
	double forceExertedBy = this.calcForceExertedBy(p);
	double forceExertedByY = forceExertedBy * (dy / r);
	return forceExertedByY;
    }
    public double calcNetForceExertedByX(Planet[] planets) {
	double netForceExertedByX = 0;
	for (Planet p : planets) {
	    if (!this.equals(p)) {
	        netForceExertedByX = netForceExertedByX + this.calcForceExertedByX(p);
	    }
	}
	return netForceExertedByX;
    }
    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceExertedByY = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                netForceExertedByY = netForceExertedByY + this.calcForceExertedByY(p);
            }
        }
        return netForceExertedByY;
    }
    public void update(double dt, double fX, double fY) {
        double accelerationX = fX / this.mass;
	double accelerationY = fY / this.mass;
	double newVelX = this.xxVel + accelerationX * dt;
	double newVelY = this.yyVel + accelerationY * dt;
	double newPosX = this.xxPos + newVelX * dt;
	double newPosY = this.yyPos + newVelY * dt;
	this.xxPos = newPosX;
	this.yyPos = newPosY;
	this.xxVel = newVelX;
	this.yyVel = newVelY;
    }
    public void draw() {
	StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
    }
}
