package net.larizacurbelo.energy_analysis.model;

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
    
    public static abstract class  RenewableEnergyDataBuilder<T extends RenewableEnergyDataBuilder<T>> 
    		extends EnergyDataBuilder<T>{
    	@Override
    	public abstract RenewableEnergyData build();
    	
    }

}
