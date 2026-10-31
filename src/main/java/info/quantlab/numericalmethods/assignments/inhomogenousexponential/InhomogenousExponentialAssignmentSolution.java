package info.quantlab.numericalmethods.assignments.inhomogenousexponential;

import java.util.function.DoubleSupplier;

import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator1D;


/**
 * Solution of the assignment in on the generation of an inhomogenous exponential distributed random number sequence.
 */
public class InhomogenousExponentialAssignmentSolution implements InhomogenousExponentialAssignment {

	/**
	 * Create a sequence having inhomogenous exponential distribution.
	 * 
	 * @param uniformSequence Uniform input sequence.
	 * @param times The boundaries of the intervals where the intensity is piecewise constant; positive and strictly increasing times.
	 * @param intensities	The constant intensities. The array is one element larger than the array times.
	 * 						The first entry is from to 0 to times[0].
	 * 						The i-the entry is from times[i-1] to times[i].
	 * 						The last entry n is from times[n-1] to ∞
	 */
	public DoubleSupplier createRandomNumberGeneratorInhomogenousExponential(RandomNumberGenerator1D uniformSequence, double[] times, double[] intensities) {
		/*
		 * Replace the line below this comment with a construction of your implementation.
		 */
		return new MyInhomogenousExponential(uniformSequence, times, intensities);
	}
}
