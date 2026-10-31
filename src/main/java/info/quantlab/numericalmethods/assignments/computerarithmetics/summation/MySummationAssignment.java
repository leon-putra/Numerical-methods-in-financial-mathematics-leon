package info.quantlab.numericalmethods.assignments.computerarithmetics.summation;

import info.quantlab.numericalmethods.lecture.computerarithmetics.summation.DoubleVector;

public class MySummationAssignment implements DoubleVector {

	private double[] values;
	
	public MySummationAssignment(double[] values) {
		this.values = values;
	}
	
	@Override
	public double get(int i) {
		return values[i];
	}
	
	@Override
	public double sum() {
		double sum = 0.0;
		double error = 0.0;
		for (int i=0; i < values.length; i++) {
			double value = values[i] - error;
			double newSum= sum + value;
			error = (newSum - sum) - value;
			sum = newSum;
		}
		return sum;
	}
	
	@Override
	public long size() {
		return values.length;
	}
}
