package net.larizacurbelo.energy_analysis.model;

public abstract class  FossilEnergyData extends EnergyData {
	
	private  double co2EmissionFactor; // Emission factor per unit of energy produced
	
	
	protected FossilEnergyData(EnergyDataBuilder<?> builder, double co2Emission) {
		super(builder);
		this.co2EmissionFactor = co2Emission;
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
	
	public static abstract class FossilEnergyDataBuilder <T extends FossilEnergyDataBuilder<T>>
								 extends EnergyDataBuilder{
		private double co2EmissionFactor;
		
		protected FossilEnergyDataBuilder self() {
			return this;
		}
		
		public  FossilEnergyDataBuilder co2EmissionFactor(double co2EmissionFactor) {
	            this.co2EmissionFactor = co2EmissionFactor;
	            return self();
	            
		}
		
		
		@Override
		public abstract FossilEnergyData build();
	}


}
