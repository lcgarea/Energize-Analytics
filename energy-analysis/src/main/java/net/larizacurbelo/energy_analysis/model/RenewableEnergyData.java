package net.larizacurbelo.energy_analysis.model;

/**
 * Abstract class for renewable energy data.
 * Stores common attributes and behaviors for renewable energy sources,
 * such as CO₂ reduction potential.
 */

public abstract class RenewableEnergyData extends EnergyData {
	
	protected RenewableEnergyData(RenewableEnergyDataBuilder<?> builder) {
		super(builder);
	}
	
	@Override
	public String getEnergyTyp() {
		return "Renewable";
	}
	
	/**
     * Abstract method to calculate the potential CO₂ emissions reduction.
     * Subclasses must implement specific calculations.
     *
     * @param year The year for which the reduction is calculated.
     * @return The CO₂ reduction potential.
     */
    public abstract double calculateCO2ReductionPotential(int year);
    

    /**
     * Abstract builder for RenewableEnergyData.
     * Provides a base for building renewable energy data instances.
     *
     * @param <T> The type of the concrete builder (for method chaining).
     */
    public abstract static  class  RenewableEnergyDataBuilder<T extends RenewableEnergyDataBuilder<T>> 
    		extends EnergyDataBuilder<T>{
    	@Override
    	public abstract RenewableEnergyData build();
   
    	
    }

}
