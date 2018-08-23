package unicam.cs.pa.forzaquattro.core;

public class GridFactory {

	private boolean done = false;
	
	public Grid getGrid(GridType type) {
		if(done) {
			return Grid.getInstance();
		}
		switch(type) {
			case CUSTOM: {
				Match.arrangeGrid();
				done = true;
				return Grid.getInstance();
			}
			case DEFAULT:
			default: {
				done = true;
				return Grid.getInstance();
			}
		}
	}
	
}
