package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrand;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegrationDomain;
import info.quantlab.numericalmethods.lecture.randomnumbers.MersenneTwister;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGeneratorFrom1D;

public class MonteCarloIntegratorTest {

	public static void main(String[] args) {
		final long seed = 3141;
		
		IntegrationDomain domain = new IntegrationDomain() {
			@Override
			public double[] fromUnitCube(double[] x) {
				return new double[] { 3.0 * x[0]};
			}
			@Override
			public int getDimension() {
				return 1;
			}
			@Override
			public double getDeterminantOfDifferential(double[] x) {
				return 3.0;
			}
		};
		Integrand integrand = z -> z[0] * z[0];
		RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorFrom1D(new MersenneTwister(seed), domain.getDimension());
        MonteCarloIntegrator integrator = new MonteCarloIntegrator(1_000_000, randomNumberGenerator);

        double result = integrator.integrate(integrand, domain);

        System.out.println("Result:   " + result);
        System.out.println("Expected: " + 9.0);
    }	
}
