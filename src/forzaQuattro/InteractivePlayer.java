package forzaQuattro;



public  class InteractivePlayer implements Player {

	private String name;
	private  Color color;
	private static  Grid myField;
	
	
	public InteractivePlayer(String name, Color color,Grid grid) {
		this.name=name;
		this.color=color;
		this.myField=grid;
	}


	@Override
	public void insertToken(int column) {
		Token token=new Token(this.color, 0);
		this.myField.insert(token, column);
	}

}
