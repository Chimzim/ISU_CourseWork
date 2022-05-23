package hw2;
import api.Outcome;
public class CricketGameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CricketGame a = new CricketGame(2,3,3,6);
//		a.bowl(Outcome.BOUNDARY_FOUR);
//		System.out.println("Expected: 1 Bowl zero Overs");
//		System.out.println(a.getBowlCount());
//		System.out.println(a.getOverCount());
//		a.bowl(Outcome.BOUNDARY_FOUR);
//		System.out.println("Expected: 0 Bowl 1 Overs");
//		System.out.println(a.getBowlCount());
//		System.out.println(a.getOverCount());
//		a.bowl(Outcome.BOUNDARY_FOUR);
//		System.out.println("Expected: 1 Bowl 1 Overs");
//		System.out.println(a.getBowlCount());
//		System.out.println(a.getOverCount());
//		a.bowl(Outcome.BOUNDARY_FOUR);
//		System.out.println(a.getBowlCount());
//		System.out.println(a.getOverCount());
		
	a.bowl(Outcome.WIDE);
	a.bowl(Outcome.BOUNDARY_FOUR);
	a.bowl(Outcome.HIT);
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.runOut();
	a.bowl(Outcome.CAUGHT_FLY);
	a.bowl(Outcome.HIT);
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.safe();
	a.bowl(Outcome.LBW);
	a.bowl(Outcome.WICKET);
	

	System.out.println(a.getCompletedInnings());
	System.out.println(a.getScore(false));
	
	a.bowl(Outcome.BOUNDARY_FOUR);
	a.bowl(Outcome.BOUNDARY_SIX);
	a.bowl(Outcome.NO_BALL);
	a.bowl(Outcome.NO_BALL);
	a.bowl(Outcome.NO_BALL);
	a.bowl(Outcome.NO_BALL);
	a.bowl(Outcome.NO_BALL);
	a.bowl(Outcome.NO_BALL);
	a.bowl(Outcome.HIT);
	a.runOut();
	a.bowl(Outcome.CAUGHT_FLY);
	a.bowl(Outcome.CAUGHT_FLY);
	a.bowl(Outcome.LBW);
	
	System.out.println(a.getScore(false));
	System.out.println(a.getCompletedInnings());

	a.bowl(Outcome.HIT);
	a.tryRun();
	a.tryRun();
	a.safe();
	a.bowl(Outcome.HIT);
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.tryRun();
	a.runOut();
	a.bowl(Outcome.HIT);
	a.tryRun();
	a.runOut();
	a.bowl(Outcome.BOUNDARY_FOUR);
	a.bowl(Outcome.WICKET);
	a.bowl(Outcome.HIT);
	a.tryRun();
	a.tryRun();
	a.safe();
	
	System.out.println(a.getCompletedInnings());
	System.out.println(a.getScore(false));
	
	}

}
