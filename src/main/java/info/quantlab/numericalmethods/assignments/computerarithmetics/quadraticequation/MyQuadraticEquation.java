package info.quantlab.numericalmethods.assignments.computerarithmetics.quadraticequation;

import info.quantlab.numericalmethods.lecture.computerarithmetics.quadraticequation.QuadraticEquation;

public class MyQuadraticEquation implements QuadraticEquation {

	private double [] coeff = new double[2];
	
	public MyQuadraticEquation(double q, double p) {
		coeff[0] = q;
		coeff[1] = p;
		}
	
	@Override
	public double[] getCoefficients() {
		return coeff;
	}
	
	@Override
	public boolean hasRealRoot() {
	 double discriminant = coeff[1] * coeff[1] - 4.0 * coeff[0];
	 return discriminant >= 0;
	}
	
	@Override
	public double getSmallestRoot() {
	if (!hasRealRoot()) {
		return Double.NaN;
	}
		double discriminant = coeff[1] * coeff[1] - 4.0 * coeff[0];
		double sqrtD = Math.sqrt(discriminant);
		double x1;
		if (coeff[1] >= 0) {
			x1 = (-coeff[1] - sqrtD) / 2.0;
		}else {
			x1 = (-coeff[1] + sqrtD) / 2.0;
		}
		
		double x2 = (x1 != 0.0) ? coeff[0] / x1 : 0.0;
		return Math.min(x1, x2);
	}
}
