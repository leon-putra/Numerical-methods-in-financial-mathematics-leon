package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import java.util.function.DoubleBinaryOperator;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrand;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegrationDomain;
import info.quantlab.numericalmethods.lecture.randomnumbers.MersenneTwister;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGenerator;
import info.quantlab.numericalmethods.lecture.randomnumbers.RandomNumberGeneratorFrom1D;
import info.quantlab.numericalmethods.assignments.montecarlo.check.MonteCarloIntegrationAssignment;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegratorFactory;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.MonteCarloIntegratorFactory;

public class MonteCarloIntegrationAssignmentSolution implements MonteCarloIntegrationAssignment {

	/**
	 * The solution of the first part of exercise, implementing a MonteCarloIntegratorFactory providing a MonteCarloIntegrator
	 * 
	 * @return A class implementing MonteCarloIntegratorFactory
	 */
	@Override
	public MonteCarloIntegratorFactory getMonteCarloIntegratorFactory() {
		 
		return new MyMonteCarloIntegratorFactory();
	}

	/**
	 * The solution of the second part of exercise.
	 * 
	 * Calculating the integral f(x,y) dx dy for a general binary operator
	 * using the integration domain [a,b] x [c,d] 
	 * using your Monte-Carlo Integrator.
	 * 
	 * @param function The function to integrate, given as a {@link DoubleBinaryOperator}.
	 * @param lowerBoundX The lower bound a for the integral of dx.
	 * @param upperBoundX The upper bound b for the integral of dx.
	 * @param lowerBoundY The lower bound c for the integral of dy.
	 * @param upperBoundY The upper bound d for the integral of dy.
	 * @return The value of the integral f(x,y) dx dy.
	 */
	@Override
	public double getIntegral(DoubleBinaryOperator function, double lowerBoundX, double upperBoundX, double lowerBoundY, double upperBoundY) {
		IntegrationDomain domain = new RectangleDomain(lowerBoundX, upperBoundX, lowerBoundY, upperBoundY);
			Integrand integrand = z -> function.applyAsDouble(z[0], z[1]);
			RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorFrom1D(new MersenneTwister(3141), domain.getDimension());
			MonteCarloIntegrator integrator = new MonteCarloIntegrator(1_000_000, randomNumberGenerator);
			return integrator.integrate(integrand, domain);
		}

	/**
	 * The solution of the third part of exercise, implementing a IntegratorFactory providing a SimpsonsIntegrator
	 * 
	 * @return A class implementing IntegratorFactory
	 */
	@Override
	public IntegratorFactory getSimpsonsIntegratorFactory() {
		
		// Replace the following line by a statement return-ing a new instance of your implementation of MonteCarloIntegratorFactory 
		return new MySimpsonsIntegratorFactory();
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
