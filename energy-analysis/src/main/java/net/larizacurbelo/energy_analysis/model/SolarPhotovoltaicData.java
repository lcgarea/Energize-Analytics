package net.larizacurbelo.energy_analysis.model;
import java.util.Map;
import java.util.Optional;
/**
 *  Represents solar energy data specific to photovoltaic energy.
 *  
 *  Attributes:
 *  - installedCapacity: Time series of installed capacity  broken down capacity 
 * 	              class and year
 */
public class SolarPhotovoltaicData extends RenewableEnergyData{
	
	// installed capacity categorized by power class. //Year -> powerclass -> Value
	private final Map<Integer, Map<SIEC, Double>> installedCapacity;
	
	private SolarPhotovoltaicData(SolarPhotovoltaicDataBuilder builder) {
		super(builder);
		this.installedCapacity = builder.installedCapacity == 
				null? Map.of() : builder.installedCapacity;
	}
	public Map<Integer, Map<SIEC, Double>> getInstalledCapacity(){
		return Map.copyOf(this.installedCapacity);
	}
	/**
	 * Calculation to get the Installed Capacity by year and power
	 * @param year 
	 * @param type capacities of photovoltaic installation
	 * @return
	 */
	
	public Optional<Double> getInstalledCapacityByYearAndType(int year, SIEC type ){
		
		return Optional.ofNullable(installedCapacity.getOrDefault(year, Map.of()).get(type));
	}
	
	@Override
	public double calculateCO2ReductionPotential(int year) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("CO2 reduction calculation not implemented yet.");
		}


	public static class SolarPhotovoltaicDataBuilder extends RenewableEnergyDataBuilder<SolarPhotovoltaicDataBuilder>{
		private Map<Integer, Map<SIEC, Double>> installedCapacity = Map.of();
		
		/**
		 * Sets the installed capacity A map of year -> (capacity class -> installed capacity).
		 * @param installedCapacity
		 * @return the builder instance.
		 */
		
		public SolarPhotovoltaicDataBuilder installedCapacity
		(Map<Integer, Map<SIEC, Double>> installedCapacity) {
			if (installedCapacity == null) {
				throw new IllegalArgumentException("Installed capacity cannot be null");
			}
			this.installedCapacity = installedCapacity;
			return self();
		}
		
		protected SolarPhotovoltaicDataBuilder self() {
			return this;
		}
		@Override
		public SolarPhotovoltaicData build() {
			return new SolarPhotovoltaicData(this);
		}
		
	}

}
