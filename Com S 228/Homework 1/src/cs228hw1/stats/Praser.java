package cs228hw1.stats;

public class Praser<T extends Number> implements IParser {
	private Number data;
	@Override
	public Number parse(String str) {
		// TODO Auto-generated method stub
		try {
			data = Integer.parseInt(str);
		}
		catch (Exception e) {
			data = null;
		}
		return data;
	}

}
