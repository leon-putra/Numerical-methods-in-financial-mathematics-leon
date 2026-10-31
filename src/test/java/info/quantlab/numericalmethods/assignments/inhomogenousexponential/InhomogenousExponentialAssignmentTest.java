package info.quantlab.numericalmethods.assignments.inhomogenousexponential;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.assignments.inhomogenousexponential.check.InhomogenousExponentialImplemenationChecker;
import info.quantlab.numericalmethods.assignments.inhomogenousexponential.check.InhomogenousExponentialImplemenationChecker.Check;


public class InhomogenousExponentialAssignmentTest {

	@Test
	void testBasicFunctionality() {
		if(!InhomogenousExponentialImplemenationChecker.check(new InhomogenousExponentialAssignmentSolution(), Check.BASIC)) fail();
	}
	
	@Test
	void testSampling() {
		if(!InhomogenousExponentialImplemenationChecker.check(new InhomogenousExponentialAssignmentSolution(), Check.SAMPLING)) fail();
	}

	@Test
	void testAccuracy() {
		if(!InhomogenousExponentialImplemenationChecker.check(new InhomogenousExponentialAssignmentSolution(), Check.ACCURACY)) fail();
	}
}
