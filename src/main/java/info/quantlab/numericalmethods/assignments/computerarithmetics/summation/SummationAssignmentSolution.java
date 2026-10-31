package info.quantlab.numericalmethods.assignments.computerarithmetics.summation;

import info.quantlab.numericalmethods.lecture.computerarithmetics.summation.DoubleVector;
import info.quantlab.numericalmethods.lecture.computerarithmetics.summation.DoubleVectorFactory;

public class SummationAssignmentSolution implements DoubleVectorFactory {

	/**
	 * Factory method creating an instance of an object
	 * implementing DoubleVector.
	 * 
	 * @param values Array of values.
	 * @return Object implementing DoubleVector interface.
	 */
	@Override
	public DoubleVector createDoubleVector(double[] values) {
		return new MySummationAssignment(values);
	}
}
