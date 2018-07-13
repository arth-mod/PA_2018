package players;

public enum MatchType {

	ONEvsONE,
	ONEvsPC,
	PCvsPC;
	
//	public final int value;
//	
//	MatchType(int value){
//		this.value = value;
//	}
	
	
	public static MatchType fromInt(int i) {
		switch(i) {
		case 0: return ONEvsONE;
		case 1: return ONEvsPC;
		case 2: return PCvsPC;
		default:
		}
		return null;
	}
	
	
	@Override
	public String toString() {
		switch(this) {
		case ONEvsONE: return "OnevsOne";
		case ONEvsPC: return "OnevsPc";
		case PCvsPC: return "PcvsPc";
		}
		return "";
	}
}
