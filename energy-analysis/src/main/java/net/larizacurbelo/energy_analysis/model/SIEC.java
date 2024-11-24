package net.larizacurbelo.energy_analysis.model;
/**
 *Standard international energy product classification (SIEC):
 *   
 * <h2> Solar Photovoltaic:</h2>
 *

 */

public enum SIEC {
	
	
	// Solar Photovoltaic
	/**Solar Photovoltaic */
	RA420, /**Solar Photovoltaic */
	RA420KW_LT10, /**Solar Photovoltaic (10 KW)*/
	RA420KW_LT20,/**Solar Photovoltaic (20 KW)*/
	RA420KW_LT30,/**Solar Photovoltaic (30 kW) */
	RA420KW_LT30_RT,/** Solar Photovoltaic (30 kW, roof top)*/
	RA420KW_LT30_OG,/** Solar Photovoltaic (&lt; 30 kW, off grip)*/
	RA420KW20_1000,/**Solar Photovoltaic (0 kW - 1000 kW) */
	RA420KW30_1000,/**Solar Photovoltaic (30 kW - 1000 kW) */
	RA420KW30_1000_RT,/**Solar Photovoltaic (30 kW - 1000 kW, roof top) */
	RA420KW30_1000_OG,/**Solar Photovoltaic (30 kW - 1000 kW, off grid) */
	RA420MW_GT1,/** Solar Photovoltaic ( 1 + MW)*/
	RA420MW_GT1_RT,/** Solar Photovoltaic(1 + MW, roof top)*/
	RA420MW_GT1_OG,/**Solar Photovoltaic (1 + MW, roof top, off grid) */
	RA420OG	/**Solar Photovoltaicoff grid */
	
	//
}
