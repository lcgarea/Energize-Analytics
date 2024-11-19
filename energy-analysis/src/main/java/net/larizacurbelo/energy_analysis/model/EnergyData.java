package net.larizacurbelo.energy_analysis.model;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * Abstract class representing common properties and behaviors of energy data.
 * Provides a foundation for both fossil and renewable energy subclasses.
 * 
 * This class stores information about energy production, consumption, 
 * imports, exports and various usages for a given country and year.
 * 
 * Attributes:
 * - countryName: The name of the country.
 * - energyProduction: Time series of energy production by year.
 * - energyConsumption: Time series of energy consumption by year.
 * - importedEnergy: Time series of imported energy data by year.
 * - exportedEnergy: Time series of exported energy data by year.
 * - energyUsage: Time series of energy usage data, broken down by sector 
 * 	              and year. Each map entry represents a year and the energy
 *                consumption by usage type.
 */

public abstract class EnergyData {
	
	protected String countryName;
	protected Map<Integer, Double>energyProduction; // Year -> Production 
	protected Map<Integer, Double> energyConsumption; // Year -> Consumption
	protected Map<Integer,Map<EnergySource, Double>> importedEnergy; //Year -> Source -> Value
	protected Map<Integer, Map<EnergySource, Double>> exportedEnergy; //Year -> Source -> Value
	protected Map<Integer, Map<EnergyUsageType, Optional<Double>>> energyUsage;//Year -> UsageType -> Value
	
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
	
	
	public String getCountryName() {
		return countryName;
	}

	public Map<Integer, Double> getEnergyProduction() {
		return Map.copyOf(energyProduction);
	}

	public Map<Integer, Double> getEnergyConsumption() {
		return Map.copyOf(energyConsumption);
	}


	public Map<Integer, Map<EnergySource, Double>> getImportedEnergy() {
		return Map.copyOf(importedEnergy);
	}


	public Map<Integer, Map<EnergySource, Double>> getExportedEnergy() {
		return Map.copyOf(exportedEnergy);
	}


	public Map<Integer, Map<EnergyUsageType, Optional<Double>>> getEnergyUsage() {
		return Map.copyOf(energyUsage);
	}


	// Abstract method to return energy type for the subclass
	public abstract String getEnergyTyp();
	
	/**
	 * Calculate the total production for a given year
	 * 
	 * 
	 * @param year The year for which total production  is calculated
	 * @return The total energy production for the year, or 0.0 if no data exists
	 */
	public double calculateTotalProduction(int year) {
		return energyProduction.getOrDefault(year, 0.0);
	}
	
	/**
	 * Calculate the total consumption for a given year
	 * @param year The year for which total production  is calculated
	 * @return The total energy consumption for the year, or 0.0 if no data exists
	 */
	public double calculateTotalConsumption(int year) {
		return energyConsumption.getOrDefault(year, 0.0);
	}
	
	/**
	 * Calculates the net import/export for a given year.
	 * Positive value indicates net import, negative value indicates net export.
	 * 
	 * @param year The year for which net import/export is calculated.
	 * @return Net import-export value for the year.
	 */
	public double calculateNetImportExport(int year) {
		double imported = importedEnergy.getOrDefault(year, Map.of())
				                        .values()
				                        .stream()
				                        .mapToDouble(Double::doubleValue)
				                        .sum();
		double exported = exportedEnergy.getOrDefault(year, Map.of())
				                        .values()
				                        .stream()
				                        .mapToDouble(Double::doubleValue)
				                        .sum();
		return imported - exported;
	}
	
	public double calculateConsumptionByType(int year, EnergyUsageType type) {
		return energyUsage.getOrDefault(year, Map.of())
				          .getOrDefault(type, Optional.of(0.0))
				          .orElse(0.0);
	}
	
	
	/**
	 * Abstract Builder class to build instances of EnergyData subclasses.
	 * @param <T> The type of the concrete builder (for method chaining).
	 */


	public abstract static  class EnergyDataBuilder<T extends EnergyDataBuilder<T>>{
		
		private String countryName;
		private Map<Integer, Double> energyProduction;
		private Map<Integer, Double> energyConsumption;
		private Map<Integer, Map<EnergySource, Double>> importedEnergy = Map.of();
		private Map<Integer, Map<EnergySource, Double>> exportedEnergy = Map.of();
		private Map<Integer, Map<EnergyUsageType, Optional<Double>>> energyUsage;
		
		/**
		 * Sets the name of the country for the energy data.
		 * 
		 * @param countryName
		 * @return The builder instance for method chaining.
		 */
		public T countryName(String countryName) {
			this.countryName = countryName;
			return self();
		}
		
		public T energyProduction(Map<Integer, Double> energyProduction) {
			this.energyProduction = energyProduction;
			return self();
		}
		
		public T energyConsumption(Map<Integer, Double> energyConsumption) {
			this.energyConsumption = energyConsumption;
			return self();
		}
		
		public T importedEnergy(Map<Integer, Map<EnergySource, Double>> importedEnergy) {
			this.importedEnergy = importedEnergy;
			return self();	
		}
		public T exportedEnergy(Map<Integer, Map<EnergySource, Double>> exportedEnergy) {
			this.exportedEnergy = exportedEnergy;
			return self();
		}
		public T energyUsage(Map<Integer, Map<EnergyUsageType, Optional<Double>>> energyUsage) {
			this.energyUsage = energyUsage;
			return self();
		}
		// Other builder methods for setting attributes...
		
		protected abstract T self();
		public abstract EnergyData build();
		
	}

}
