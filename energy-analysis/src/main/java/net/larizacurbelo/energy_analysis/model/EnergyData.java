package net.larizacurbelo.energy_analysis.model;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * Abstract class representing common properties and behaviors of energy data.
 * Provides a foundation for both fossil and renewable energy subclasses
 * <p> This class stores information about energy production, consumption, 
 * imports, exports and various usages for a given country and year<p>
 * 
 * Attributes:
 * - countryName: The name of the country.
 * - energyProduction: Time series of energy production by year.
 * - energyConsumption: Time series of energy consumption by year.
 * - importedEnergy: Time series of imported energy data by year.
 * - exportedEnergy: Time series of exported energy data by year.
 * - energyUsage: Time series of energy usage data, broken down by sector 
 * 	 and year. Each map entry represents a year and the energy consumption by 
 *   usage type
 */

public abstract class EnergyData {
	
	protected String countryName;
	protected Map<Integer, Double>energyProduction;
	protected Map<Integer, Double> energyConsumption;
	protected Map<Integer,Map<EnergySource, Double>> importedEnergy;
	protected Map<Integer, Map<EnergySource, Double>> exportedEnergy;
	protected Map<Integer, Map<EnergyUsageType, Optional<Double>>> energyUsage;
	
	/**
	 * Constructor for EnergyData, used by subclasses.
	 * Parameters are set via EnergyDataBuilder class
	 * 
	 * @param builder the builder instance containing initialization values
	 */
	
	protected EnergyData(EnergyDataBuilder<?> builder) {
		this.countryName = builder.countryName;
		this.energyProduction = builder.energyProduction;
		this.energyConsumption = builder.energyConsumption;
		this.importedEnergy = builder.importedEnergy;
		this.exportedEnergy = builder.exportedEnergy;
		this.energyUsage = builder.energyUsage;
		
	}
	
	


	
	public abstract String getEnergyTyp();


	public abstract static  class EnergyDataBuilder<T extends EnergyDataBuilder<T>>{
		
		private String countryName;
		private Map<Integer, Double> energyProduction;
		private Map<Integer, Double> energyConsumption;
		private Map<Integer, Map<EnergySource, Double>> importedEnergy;
		private Map<Integer, Map<EnergySource, Double>> exportedEnergy;
		private Map<Integer, Map<EnergyUsageType, Optional<Double>>> energyUsage;
		protected abstract T self();
		public abstract EnergyData build();
		
		
	}

}
