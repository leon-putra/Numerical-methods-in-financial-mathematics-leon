package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import info.quantlab.numericalmethods.assignments.montecarlo.check.MonteCarloIntegrationImplementationChecker;

/**
 * This class tests the implementation of your class.
 * The actual test code is not part of this project.
 * 
 * @author Christian Fries
 */
public class MonteCarloIntegrationAssignmentTest {

	@Test
	void testMonteCarloIntegrator() {
		if(!MonteCarloIntegrationImplementationChecker.check(new MonteCarloIntegrationAssignmentSolution(), "monte carlo integrator")) fail();
	}

	@Test
	void testIntegralOfDoubleBinaryOperator() {
		if(!MonteCarloIntegrationImplementationChecker.check(new MonteCarloIntegrationAssignmentSolution(), "monte carlo integrator 2D")) fail();
	}

	@Test
	void testSimpsonsIntegrator() {
		if(!MonteCarloIntegrationImplementationChecker.check(new MonteCarloIntegrationAssignmentSolution(), "simpsons integrator")) fail();
	}
}
