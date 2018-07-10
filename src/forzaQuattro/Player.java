package forzaQuattro;

public interface Player {
	
	public void step() throws IllegalTokenLocation, WinException, FullColumnException;

	public Grid getGrid();

}
