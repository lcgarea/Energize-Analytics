package net.larizacurbelo.energy_analysis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.larizacurbelo.energy_analysis.model.SIEC;
import net.larizacurbelo.energy_analysis.model.RenewableEnergyData;
import net.larizacurbelo.energy_analysis.model.SolarPhotovoltaicData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Optional;

public class SolarPhotovoltaicDataTest {
	private Map<Integer, Map<SIEC, Double>> sampleCapacity;
	
	@Test
	void testBuilderSetsInstalledCapacity() {
		Map<Integer, Map<SIEC, Double>> capacityData = Map.of(
	            2023, Map.of(SIEC.RA420, 5000.0),
	            2022, Map.of(SIEC.RA420KW_LT10, 100.0)
	        );
		
		SolarPhotovoltaicData solarData = new SolarPhotovoltaicData
				.SolarPhotovoltaicDataBuilder()
				.installedCapacity(capacityData)
				.countryName("Germany")
				.build();
		assertNotNull(solarData.getInstalledCapacity());
		assertEquals(50000.0, solarData.getInstalledCapacityByYearAndType
				(2023, SIEC.RA420).orElse(0.0));
		assertEquals(100.0, solarData.getInstalledCapacityByYearAndType
				(2022, SIEC.RA420KW_LT10).orElse(0.0));
		
		

	}
	
	@Test
	void testHandlesNullInstalledCapacity() {
		

		
		//assertNotNull(solarData.getInstalledCapacity(),"Installed capacity should default to an empty map.") ;
		//assertTrue(solarData.getInstalledCapacity().isEmpty(), "Installed capacity should be empty when null is provided.");
		assertThrows(IllegalArgumentException.class, ()-> new SolarPhotovoltaicData.SolarPhotovoltaicDataBuilder()
				.installedCapacity(null)
				.build());
		 
	}
	
	@Test
    void testGetInstalledCapacityByYearAndType() {
        // Arrange
        Map<Integer, Map<SIEC, Double>> capacityData = Map.of(
            2023, Map.of(SIEC.RA420, 5000.0),
            2022, Map.of(SIEC.RA420KW_LT10, 100.0)
        );

        SolarPhotovoltaicData solarData = new SolarPhotovoltaicData.SolarPhotovoltaicDataBuilder()
            .countryName("France")
            .installedCapacity(capacityData)
            .build();

        // Act & Assert
        assertEquals(Optional.of(5000.0), solarData.getInstalledCapacityByYearAndType(2023, SIEC.RA420));
        assertEquals(Optional.of(100.0), solarData.getInstalledCapacityByYearAndType(2022, SIEC.RA420KW_LT10));
        assertEquals(Optional.empty(), solarData.getInstalledCapacityByYearAndType(2021, SIEC.RA420));
    }

	


}
