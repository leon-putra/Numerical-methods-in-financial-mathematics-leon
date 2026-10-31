package info.quantlab.numericalmethods.assignments.montecarlo.integration;

import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrator;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.IntegrationDomain;
import info.quantlab.numericalmethods.lecture.montecarlo.integration.Integrand;

public class SimpsonsIntegrator implements Integrator {
	
	private final long numberOfValuationPoints;
	/**
	 * Create a Simpson's rule integrator
	 * 
	 * @param numberOfValuationPoints the minimum total number of valuation points.
	 */
	
	public SimpsonsIntegrator(long numberOfValuationPoints) {
		super();
		this.numberOfValuationPoints = numberOfValuationPoints;
	}
	
	@Override
	public double integrate(Integrand integrand, IntegrationDomain integrationDomain) {
		// calculate dimension and number of valuation points per dimension
		int dimension = integrationDomain.getDimension();
		int numberOfValuationPointsPerDimension = 2 * (int) Math.ceil(Math.pow(numberOfValuationPoints, 1.0 / dimension) / 2);
		int numberOfValuationPointsEffective = (int) Math.pow(numberOfValuationPointsPerDimension, dimension);
		double integralStep = 1.0 / (numberOfValuationPointsPerDimension - 1);
		
		double sum = 0.0;
		int[] multiIndex = new int[dimension];
		double[] pointOnUnitCube = new double[dimension];
		for (long pointCounter = 0; pointCounter < numberOfValuationPointsEffective; pointCounter++) {
			// convert multi-index to coordinates in [0,1]^d
			int weight = 1;
			for (int k = 0; k < dimension; k++) {
				pointOnUnitCube[k] = multiIndex[k] * integralStep;
				weight *= simpsonWeight(multiIndex[k], numberOfValuationPointsPerDimension);
			}
			// Transform the actual domain and evaluate the integrand
			double[] pointOnDomain = integrationDomain.fromUnitCube(pointOnUnitCube);
			double jacobianDeterminant = integrationDomain.getDeterminantOfDifferential(pointOnUnitCube);
			double integrandValue = integrand.value(pointOnDomain);
			
			// Accumulate weithgted contribution
			sum += weight * integrandValue * jacobianDeterminant;
			// Increment the multi-index
			for (int k = dimension - 1; k >=0; k--) {
				multiIndex[k]++;
				if (multiIndex[k] < numberOfValuationPointsPerDimension) {
					break;
				}
				multiIndex[k] = 0;
			}
		}
			return sum * Math.pow(integralStep / 3.0, dimension);
	}
	/**
	 * Returns the Simpson's rule weight
	 * @param index The index of the point (0, 1,...., numberOfPoints - 1).
	 * @param numberOfPoints the total number of points in this dimension.
	 * @return The weight: 1 for endpoints, 4 for odd indices, 2 for even indices in the middle
	 */
private int simpsonWeight(int index, int numberOfPoints) {
	if (index == 0 || index == numberOfPoints - 1) {
		return 1;
	} else if (index % 2 == 1) {
		return 4;
	} else { 
		return 2;
	}
}
}
