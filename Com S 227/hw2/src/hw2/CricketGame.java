package hw2;
/**
 * This will mimic a the simplistic aspects of a game of cricket through the given bowls per over, overs per innings, total innings, and the total amount of players for each team.
 * @author ogbondah
 *
 */
import api.Outcome;
import api.Defaults;
import static api.Outcome.*;

	public class CricketGame {
	501
		private int bowlsPerOver, totalInnings, oversPerInnings, numPlayers;
		private int currentInnings, totalOuts;
		private int side0Score, side1Score, inningsOvers, inningsBowls;
		private boolean inPlay, Running, gameOver;
		/**
		 * Constructs the cricket game without parameters.
		 * bowlsPerOver - Sets the bowls per over for to the default amount for a cricket game.
		 * totalInnings - Sets the total innings to the amount for a default amount cricket game.
		 * oversPerInnings - Sets the amount of overs per innings to the default amount for a cricket game.
		 * numPlayers - Sets the amount of players to the default amount for a cricket game. 
		 */
		public CricketGame() {
			bowlsPerOver = Defaults.DEFAULT_BOWLS_PER_OVER;
		    totalInnings = Defaults.DEFAULT_NUM_INNINGS;
			oversPerInnings = Defaults.DEFAULT_OVERS_PER_INNINGS;
			numPlayers = Defaults.DEFAULT_NUM_PLAYERS;
		
	}
		/**
		 * Constructs the Cricket game with the given Bowls per over, overs per innings, total amount of innings, and the total number of players on each team.
		 * @param givenBowlsPerOver - Bowls before the an over is reached.
		 * @param givenOversPerInnings - Amount of overs before the innings is concluded.
		 * @param givenTotalInnings - Total amount of innings in the cricket game.
		 * @param givenNumPlayers - The number of player for each team.
		 * bowlsPerOver - The number of bowls it takes to reach an over.
		 * oversPerInnings - The number of overs it takes to reach the end of an innings.
		 * totalInnings - The amount of innings in the game of cricket.
		 * numPlayers - The amount of players on each cricket team.
		 * totalOuts - Holds the number of players out for the current innings.
		 * side0Score - Holds the score of team 0.
		 * side1Score - Holds the score of team 1.
		 * currentInnings - Holds the amount of completed innings.
		 * inningsOvers - Holds the total amount of overs for the current innings.
		 * inningsBowls - Holds the total amount of bowls for the current innings.
		 * inPlay - Tells whether or not the ball has been hit and is in play.
		 * Running - Tells if the player is currently running to try an score a run.
		 * gameOver - Tells if the game is over or not.
		 */
		public CricketGame(int givenBowlsPerOver, int givenOversPerInnings, int givenTotalInnings, int givenNumPlayers) {
			
			bowlsPerOver = givenBowlsPerOver;
			oversPerInnings = givenOversPerInnings;
			totalInnings = givenTotalInnings;
			numPlayers = givenNumPlayers;
			totalOuts = 0;
			side0Score = 0;
			side1Score = 0;
			currentInnings = 0;
			inningsOvers = 0;
			inningsBowls = 0;
			inPlay = false;
			Running = false;
			gameOver = false;
		}
		/**
		 * Bowls to the batsman and based on the given outcome, it will assign points to the team up to bat, update the current bowls and overs for the innings, and update the current innings.
		 * @param outcome - User given outcome for the cricket game
		 */
		public void bowl(Outcome outcome) {
			if(totalInnings == currentInnings && totalInnings % 2 == 1) {
				totalInnings = totalInnings + 1;
			}
			
			int prev_innings = 0;
				if((outcome == WICKET) && (gameOver == false) && (inPlay == false)) {
					prev_innings = currentInnings;
					oversCount();
					if(prev_innings == currentInnings) {
					playerOuts();
					}
				}
				if((outcome == LBW) && (gameOver == false) && (inPlay == false)) {
					oversCount();
					if(prev_innings == currentInnings) {
					playerOuts();
					}
				}
				if((outcome == CAUGHT_FLY) && (gameOver == false) && (inPlay == false)) {
					oversCount();
					if(prev_innings == currentInnings) {
					playerOuts();
					}
				}
				if((outcome == WIDE) && (gameOver == false) && (inPlay == false)){
					scoreCount(WIDE);
				}
				if((outcome == NO_BALL) && (gameOver == false) && (inPlay == false)){
					scoreCount(NO_BALL);
				}
				if((outcome == BOUNDARY_SIX) && (gameOver == false) && (inPlay == false)){
					scoreCount(BOUNDARY_SIX);
					oversCount();
				}
				if((outcome == BOUNDARY_FOUR) && (gameOver == false) && (inPlay == false)) {
					scoreCount(BOUNDARY_FOUR);
					oversCount();
				}
				if((gameOver == false) && (inPlay == false) && (outcome == HIT)){
					inPlay = true;
				//	oversCount();		
				}	
		}
		/**
		 * Returns the total number of bowls currently for the current innings.
		 * @return Total number of bowls for the current innings.
		 */
		public int getBowlCount() {
			return inningsBowls;
		}
		/**
		 * Returns whether or not the ball is in play from the batsman recording a hit.
		 * @return If the ball is currently in play or not.
		 */
		public boolean isInPlay() {
			return inPlay;
		}
		/**
		 * Returns the total number of completed innings for the cricket game.
		 * @return Completed innings.
		 */
		public int getCompletedInnings() {
			return currentInnings;
		}
		/**
		 * Returns the total amount of players out for the current innings.
		 * @return Total number of players out for current innings.
		 */
		public int getOuts() {
			return totalOuts;
		}
		/**
		 * Returns the total number of overs for the current innings.
		 * @return Total amount of overs for current innings.
		 */
		public int getOverCount() {
			return inningsOvers;
		}
		/**
		 * Returns where the game is over or not.
		 * @return If the game has ended or not.
		 */
		public boolean isGameEnded() {
			return gameOver;
		}
		/**
		 * Returns the score of the batting side if True, but if false returns the not batting teams scores.
		 * @param battingSide - Take on a value of true or false to give a teams score.
		 * @return The score of the batting team if true, and non batting team if false.
		 */
		public int getScore(boolean battingSide) {
			if(battingSide == true) { 
				if(currentInnings % 2 == 0) {
					return side0Score;
				}
				else {
					return side1Score;
				}
			}
			else {
				if(currentInnings % 2 == 1) {
					return side0Score;
				}
				else {
					return side1Score;
				}
			}
		}
		/**
		 * Updated the outs for the current innings. Also makes sure the game is not over in order to count players out.
		 * If all the players on the cricket team are called out for the innings, then the innings is over.
		 */
		private void playerOuts() {
			if((side1Score >= side0Score) &&
					(currentInnings >= totalInnings -1)) {
				gameOver = true;
			}
			if( (currentInnings >= totalInnings -1) && 
					((totalOuts == numPlayers - 1) || (oversPerInnings == inningsOvers)) ) {
				gameOver = true;
			}
			
			totalOuts += 1;
			if(totalOuts == (numPlayers - 1) ) {
				totalOuts = 0;
				inningsOvers = 0;
				inningsBowls = 0;
				currentInnings++;
			}
		}
		/**
		 * Updates the total amount of overs and bowls for the current innings. Also checks to make sure the game is not over before updating bowls and overs.
		 * Updates the bowls once the limit per over is reached and once the over per innings is reach. Updated the innings.
		 */
		private void oversCount() {
			if((side1Score >= side0Score) &&
					(currentInnings >= totalInnings - 1)) {
				gameOver = true;
			}
			if( (currentInnings == totalInnings) && 
					((totalOuts == numPlayers - 1) || (oversPerInnings == inningsOvers)) ) {
				gameOver = true;
			}
		
			inningsBowls += 1;
			if(inningsBowls == bowlsPerOver) {
				inningsOvers += 1;
				inningsBowls = 0;
				if(inningsOvers == oversPerInnings) {
					currentInnings++;
					inningsOvers = 0;
					totalOuts = 0;
					}
				}
			}
		/**
		 * Updates the score of the batting team based on the current Innings and the outcome of the bowl.
		 * @param Points - User given outcome of the bowl to the batsman.
		 */
		private void scoreCount(Outcome Points) {
			if((side1Score > side0Score) &&
					(currentInnings == totalInnings)) {
				gameOver = true;
			}
			if( (currentInnings == totalInnings) && 
					((totalOuts == numPlayers) || (oversPerInnings == inningsOvers)) ) {
				gameOver = true;
			}
			
			if(Points == WIDE || Points == NO_BALL) {
				if(currentInnings % 2 == 0) {
					side0Score += 1;
				}
				else {
					side1Score += 1;
				}
			}
			if(Points == BOUNDARY_SIX) {
				if(currentInnings % 2 == 0) {
					side0Score += 6;
				}
				else {
					side1Score += 6;
				}
			}
			if(Points == BOUNDARY_FOUR) {
				if(currentInnings % 2 == 0) {
					side0Score += 4;
				}
				else {
					side1Score += 4;
				}
			}
			if(Points == HIT) {
				if(currentInnings % 2 == 0) {
					side0Score += 1;
				}
				else {
					side1Score += 1;
				}
			}
	
			
		}
		/**
		 * Simulates the action of the batsman running towards the other set of wickets after a hit is recorded.
		 * Batsman scores a run if they were already running towards a set of wickets.
		 */
		public void tryRun() {
			if((isRunning() == true) && (isInPlay() == true) 
					&& (gameOver == false) ){
				scoreCount(HIT);
			}
			if(isInPlay() == true) {
			Running = true;
			}
		}
		/**
		 * Simulates the action of the batsman either successfully scoring a running and staying in the safe zone, or staying in the safe zone after a hit.
		 */
		public void safe() {
			if( (isInPlay() == true) && 
					(isRunning() == true) && (gameOver == false) ){
				scoreCount(HIT);
				oversCount();
				Running = false;
				inPlay = false;
			}
			inPlay = false;
		}
		/**
		 * Simulates the action of the batsman attempting to score a run but being called out.
		 */
		public void runOut() {
			if( (isInPlay() == true) && (isRunning() == true) &&
					(gameOver == false) ){
				playerOuts();
				oversCount();
				Running = false;
				inPlay = false;
			}
			Running = false;
			inPlay = false;
		}
		/**
		 * Returns whether or not the batsman is currently running in an attempt to score points.
		 * @return If the batsman is running towards a set of wickets.
		 */
		public boolean isRunning() {
			return Running;
		}
		/**
		 * Returns which side is batting based on the current innings.
		 * @return Which team is up to bat.
		 */
		public int whichSideIsBatting() {
			if(currentInnings % 2 == 0) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}