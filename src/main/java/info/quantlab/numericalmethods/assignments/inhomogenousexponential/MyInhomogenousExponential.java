package info.quantlab.numericalmethods.assignments.inhomogenousexponential;

import java.util.function.DoubleSupplier;

import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator1D;

public class MyInhomogenousExponential implements DoubleSupplier {
	
	private final RandomNumberGenerator1D uniformSequence;
	private final double[] times; 
	private final double[] intensities;
	private final double[] cumulativeLambda;
	
	public MyInhomogenousExponential(RandomNumberGenerator1D uniformSequence, double[] times, double[] intensities) {
		this.uniformSequence = uniformSequence;
		this.times = times;
		this.intensities = intensities;
		this.cumulativeLambda = computeCumulativeLambda();
	}

	private double[] computeCumulativeLambda() {
		double[] cum = new double[times.length];
		cum[0] = intensities[0] * times[0];
		for (int i = 1; i < times.length; i++) {
			cum[i] = cum[i-1] + intensities[i] * (times[i] - times[i-1]);
		}
		return cum;
	}
	
	@Override
	public double getAsDouble() {
		double u = uniformSequence.getAsDouble();
		double z = -Math.log(1.0 - u);
		return invertLambda(z);
		
	}
	private double invertLambda(double z) {
		if(z < cumulativeLambda[0]) {
			return z / intensities[0];
		}
		for (int i = 1; i < times.length;i++) {
			if(z < cumulativeLambda[i]) {
				return times[i-1] + (z - cumulativeLambda[i-1]) / intensities[i];
			}
		}
		int n = times.length;
		return times[n-1] + (z - cumulativeLambda[n-1]) / intensities[n];
	}
}
