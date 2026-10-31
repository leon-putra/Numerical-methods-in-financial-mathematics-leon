package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrator;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegratorFactory;

public class MySimpsonsIntegratorFactory implements IntegratorFactory {
	
	@Override
	public Integrator getIntegrator(long numberOfValuationPoints) {
		return new SimpsonsIntegrator(numberOfValuationPoints);
	}

}
