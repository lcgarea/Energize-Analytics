package net.larizacurbelo.energy_analysis.model;

public abstract class  FossilEnergyData extends EnergyData {
	/**The CO2 emission factor specific to the fossil fuel type*/
	private  double co2Emission;
	
	
	protected FossilEnergyData(EnergyDataBuilder<?> builder, double co2Emission) {
		super(builder);
		this.co2Emission = co2Emission;
	}
	
	public double getCo2Emission () {
		return this.co2Emission;
	}
	
	public abstract double calculateTotalEmissions();


}
