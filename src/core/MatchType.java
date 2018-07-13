package core;

/**
 * Definisce i tipi di {@code Match} disponibili.
 */
public enum MatchType {

	ONEvsONE,
	ONEvsPC,
	PCvsPC;
	
	/**
	 * Ritorna un {@code MatchType} a partire dall'indice inserito
	 * @param i indice
	 * @return
	 */
	public static MatchType fromInt(int i) {
		switch(i) {
		case 0: return ONEvsONE;
		case 1: return ONEvsPC;
		case 2: return PCvsPC;
		}
		return ONEvsONE;
	}
	
//	@Override
//	public String toString() {
//		switch(this) {
//		case ONEvsONE: return "OnevsOne";
//		case ONEvsPC: return "OnevsPc";
//		case PCvsPC: return "PcvsPc";
//		}
//		return "";
//	}
}
