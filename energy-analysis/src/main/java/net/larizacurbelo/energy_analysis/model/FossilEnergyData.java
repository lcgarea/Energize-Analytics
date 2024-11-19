package net.larizacurbelo.energy_analysis.model;

/**
 * Abstract class for fossil energy data.
 * Stores specific attributes and behaviors for fossil energy sources,
 * including CO₂ emission factors and methods to calculate total emissions.
 */

public abstract class  FossilEnergyData extends EnergyData {
	
	private  double co2EmissionFactor; // CO2 Emission factor per unit of energy produced
	
	
	protected FossilEnergyData(FossilEnergyDataBuilder<?> builder) {
		super(builder);
		this.co2EmissionFactor = builder.co2EmissionFactor;
	}
	
	public double getCo2EmissionFactor () {
		return this.co2EmissionFactor;
	}
	
	/**
	 * Abstract method to calculate the total Co2 emissions.
	 * Subclasses must implement specific calculations based on energy type
	 * 
	 * @param year The year for which emissions are calculated.
	 * @return The total CO₂ emissions.
	 */
	public abstract double calculateTotalEmissions(int year);
	/**
	 * Builder for creating instances of FossilEnergyData subclasses.
	 * Ensures proper initialization and validation of attributes like
	 * CO₂ emission factors.
	 *
	 * @param <T> The type of the concrete builder (for method chaining).
	 */
	
	public abstract static  class FossilEnergyDataBuilder <T extends FossilEnergyDataBuilder<T>>
								 extends EnergyDataBuilder <T>{
		private double co2EmissionFactor = 1.0;
				
		public T co2EmissionFactor(double co2EmissionFactor) {
			if( co2EmissionFactor <= 0) {
				throw new IllegalArgumentException("CO2 emission factor must be greater than zero.");
			}
	           this.co2EmissionFactor = co2EmissionFactor;
	           return self();
	            
		}
		@Override
    	public abstract FossilEnergyData build();
		
	}
}
