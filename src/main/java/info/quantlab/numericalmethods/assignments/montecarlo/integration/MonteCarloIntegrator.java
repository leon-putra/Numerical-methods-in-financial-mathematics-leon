package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrator;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrand;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegrationDomain;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator;
/**
 * Implementation of integrator using Monte Carlo integrator on a general domain
 */

public class MonteCarloIntegrator implements Integrator  {
	
	private final RandomNumberGenerator randomNumberGenerator;
	private final long numberOfEvaluationPoints;
	/**
	 * 
	 * @param numberOfEvaluationPoints the number of sample points to be used.
	 * @param randomNumberGenerator the random number generator providing points in the unit cube.
	 */
	public MonteCarloIntegrator(long numberOfEvaluationPoints, RandomNumberGenerator randomNumberGenerator) {
		super();
		this.numberOfEvaluationPoints = numberOfEvaluationPoints;
		this.randomNumberGenerator = randomNumberGenerator;
	}
	
	@Override
	public double integrate(Integrand integrand, IntegrationDomain integrationDomain) {
	
// check if the dimension of the domain mathces the dimension of the random number sequence.
	if (randomNumberGenerator.getDimension() < integrationDomain.getDimension()) {
		throw new IllegalArgumentException(
				"Dimension of the random number generator " + randomNumberGenerator.getDimension() + " is smaller than the dimension of the integration domain.");
	}
	// Monte carlo integration
	double sum = 0.0;
	for (long i = 0; i < numberOfEvaluationPoints; i++) {
		double[] parametersOnUnitCube = randomNumberGenerator.getNext();
		double[] pointOnDomain = integrationDomain.fromUnitCube(parametersOnUnitCube);
		double jacobianDeterminant = integrationDomain.getDeterminantOfDifferential(parametersOnUnitCube);
		double integrandValue = integrand.value(pointOnDomain);
		
		sum += integrandValue * jacobianDeterminant;
	}
	
	return sum / numberOfEvaluationPoints;
		
	}
	
}
