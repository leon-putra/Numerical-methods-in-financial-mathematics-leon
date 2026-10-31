package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrand;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegrationDomain;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator;
import info.quantlab.numericalmethods.lecture.randomnumbers.MersenneTwister;
import info.quantlab.numericalmethods.lecture.randomnumbers.HaltonSequence;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGeneratorFrom1D;

/** 
 * Experiment comparing Monte-Carlo integration error using a low-discrepancy sequence 
 * versus a pseudo-random sequence
 * 
 * Test integral: cos(x)*cos(y) dx dy, over [-pi/2, pi/2]^2, with exact value 4.0 (like lecture.montecarlo.integration2d.experiment).
 * 
 * Conclusion: Halton sequence converges at rate O(1/N), much faster than the pseudo-random
 * Monte Carlo rate of O(1/sqrt(N)). For large N, Halton can be orders of magnitude more accurate.
 */
public class MonteCarloIntegrationLowDiscrepancy {

	public static void main(String[] args) {
		// Define the integration problem: integral of cos(x)*cos(y) over [-pi/2, pi/2]^2
		final double lowerBoundX = -Math.PI / 2;
		final double upperBoundX = +Math.PI / 2;
		final double lowerBoundY = -Math.PI / 2;
		final double upperBoundY = +Math.PI / 2;
		final double integralAnalytic = 4.0;
		
		// Define the integrand and the domain
		Integrand integrand = z -> Math.cos(z[0]) * Math.cos(z[1]);
		IntegrationDomain domain = new RectangleDomain(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY);
		System.out.println("Comparison: Low-discrepancy (Halton) vs Pseudo-random (Mersenne Twister)");
		System.out.println("Integral: cos(x)*cos(y) over [-pi/2, pi/2]^2");
		System.out.println("Exact value: " + integralAnalytic);
		System.out.println();
		
		// Test with different sample sizes
		long[] sampleSizes = { 100, 1_000, 10_000, 100_000, 1_000_000 };
		
		for (long n : sampleSizes) {
			
			//Low-discrepancy 
			RandomNumberGenerator lowDiscrepancyRng = new HaltonSequence(new int[] { 2, 3 });
			MonteCarloIntegrator quasiMonteCarloIntegrator = new MonteCarloIntegrator(n, lowDiscrepancyRng);
			double quasiResult = quasiMonteCarloIntegrator.integrate(integrand, domain);
			double quasiError = quasiResult - integralAnalytic;
			
			// Pseudo-random
			RandomNumberGenerator pseudoRandomRng = new RandomNumberGeneratorFrom1D (new MersenneTwister(3141), domain.getDimension());
			MonteCarloIntegrator pseudoMonteCarloIntegrator = new MonteCarloIntegrator(n, pseudoRandomRng);
			double pseudoResult = pseudoMonteCarloIntegrator.integrate(integrand, domain);
			double pseudoError = pseudoResult - integralAnalytic;
			
			// Results
			System.out.println("N=" + n);
			System.out.println(" Halton: " + quasiResult + ", error: " + quasiError);
			System.out.println(" Mersenne: " + pseudoResult + ", error: " + pseudoError);
			System.out.println();
			
		}
}
		/**
		 * Integration domain representing the rectangle [lowerBoundX, upperBoundX] x [lowerBoundY, upperBoundY].
		 */
		private static class RectangleDomain implements IntegrationDomain{
		
			private final double lowerBoundX;
			private final double upperBoundX;
			private final double lowerBoundY;
			private final double upperBoundY;
			
			RectangleDomain(double lowerBoundX, double upperBoundX, double lowerBoundY, double upperBoundY){
				this.lowerBoundX = lowerBoundX;
				this.upperBoundX = upperBoundX;
				this.lowerBoundY = lowerBoundY;
				this.upperBoundY = upperBoundY;
			}
			
			@Override
			public double[] fromUnitCube(double[] parametersOnUnitCube) {
				double x = lowerBoundX + (upperBoundX - lowerBoundX) * parametersOnUnitCube[0];
				double y = lowerBoundY + (upperBoundY - lowerBoundY) * parametersOnUnitCube[1];
				return new double[] { x, y };
			}
			
			@Override
			public int getDimension() {
				return 2;
			}
			
			@Override
			public double getDeterminantOfDifferential (double[] parametersOnUnitCurve) {
				return (upperBoundX - lowerBoundX) * (upperBoundY - lowerBoundY);			
			}
		}
	}
