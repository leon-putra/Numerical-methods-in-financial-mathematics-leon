package info.quantlab.numericalmethods.assignments.computerarithmetics.quadraticequation;

import info.quantlab.numericalmethods.lecture.computerarithmetics.quadraticequation.QuadraticEquation;
import info.quantlab.numericalmethods.lecture.computerarithmetics.quadraticequation.QuadraticEquationFactory;

public class QuadraticEquationAssignmentSolution implements QuadraticEquationFactory {

	/**
	 * Factory method creating an instance of an object
	 * implementing QuadraticEquation with coefficients p
	 * and q, representing the equation x^2 + p x + q.
	 * 
	 * @param q The constant coefficient q in x^2 + p x + q.
	 * @param p The linear coefficient p in x^2 + p x + q.
	 * @return The object implementing QuadraticEquation.
	 */
	@Override
	public QuadraticEquation createQuadraticEquation(double q, double p) {
		/*
		 * Replace the line below this comment with a construction of your implementation
		 * of the QuadraticEquation using the parameters q and p, then run the test
		 * QuadraticEquationAssignmentTest (see src/test/java)
		 */
		return new MyQuadraticEquation(q,p);
	}
}
