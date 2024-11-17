package net.larizacurbelo.energy_analysis.model;

public abstract class RenewableEnergyData extends EnergyData {
	
	protected RenewableEnergyData(EnergyDataBuilder<?> builder) {
		super(builder);
	}
	public abstract double getCo2ReductionPotential();

}
