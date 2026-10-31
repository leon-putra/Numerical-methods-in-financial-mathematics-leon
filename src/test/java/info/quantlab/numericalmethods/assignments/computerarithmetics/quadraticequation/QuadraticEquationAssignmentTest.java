package info.quantlab.numericalmethods.assignments.computerarithmetics.quadraticequation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.assignments.computerarithmetics.check.QuadraticEquationImplemenationChecker;
import info.quantlab.numericalmethods.lecture.computerarithmetics.quadraticequation.QuadraticEquationFactory;

public class QuadraticEquationAssignmentTest {

	@Test
	void testBasic() {
		QuadraticEquationFactory quadraticEquationFactory = new QuadraticEquationAssignmentSolution();

		System.out.println("Testing basic functionality of " + quadraticEquationFactory.getClass().getCanonicalName());

		boolean success = QuadraticEquationImplemenationChecker.check(quadraticEquationFactory, "basic");
		
		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved the basic part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}

	@Test
	void testStability() {
		QuadraticEquationFactory quadraticEquationFactory = new QuadraticEquationAssignmentSolution();

		System.out.println("Testing numerical stability of " + quadraticEquationFactory.getClass().getCanonicalName());

		boolean success = QuadraticEquationImplemenationChecker.check(quadraticEquationFactory, "accuracy");
		
		if(!success) {
			System.out.println("Sorry, the test failed.");
		}
		else {
			System.out.println("Congratulation! You solved the accuracy part of the exercise.");
		}

		System.out.println("_".repeat(79));

		if(!success) fail();
	}
}
