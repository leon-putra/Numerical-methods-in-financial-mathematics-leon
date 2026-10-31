package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrator;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.MonteCarloIntegratorFactory;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator;

public class MyMonteCarloIntegratorFactory implements MonteCarloIntegratorFactory {

	@Override
	public Integrator getIntegrator(RandomNumberGenerator randomNumberGenerator, long numberOfSamplePoints) {
		return new MonteCarloIntegrator(numberOfSamplePoints, randomNumberGenerator);
	}
}
