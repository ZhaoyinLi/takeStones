
import java.util.List;

public class AlphaBetaPruning {

	public AlphaBetaPruning() {

	}

	int move = 0;
	double value = 0.0;
	int nov = 0;
	int noe = 0;
	int maxDepth = 0;
	int maxDepthp=0;
	double factor = 0.0;
	double alpha = Double.NEGATIVE_INFINITY;
	double beta = Double.POSITIVE_INFINITY;

	/**
	 * This function will print out the information to the terminal, as specified in
	 * the homework description.
	 */
	public void printStats() {
		// TODO Add your code here
		System.out.println("Move: " + move);
		System.out.println("Value:  " + value);
		System.out.println("Number of Nodes Visited: " + nov);
		System.out.println("Number of Nodes Evaluated:  " + noe);
		System.out.println("Max Depth Reached:  " + maxDepth);
		System.out.println("Avg Effective Branching Factor: " + factor);

	}

	/**
	 * This function will start the alpha-beta search
	 * 
	 * @param state This is the current game state
	 * @param depth This is the specified search depth
	 */
	public void run(GameState state, int depth) {
  	    nov++;
     	maxDepthp=depth;
//    	value=alphabeta( state,  depth, alpha, beta, ((state.getIniStonAmt()-state.getSize())%2)==0);
//    	maxDepth=depth-maxDepth;
//    	factor=(double)(nov-1)/(nov-noe);
		int big = 0;
		int cnt = 0;
		for (int x = 1; x <= state.getSize(); x++) {
			if (state.getStone(x) != true) {
				cnt++;
			}
		}
		boolean maxPlayer;
		if (cnt % 2 != 0) {
			maxPlayer = false;
		} else
			maxPlayer = true;

		List<GameState> successors = state.getSuccessors();
		if ( successors.isEmpty() || depth == 0) {
			value = state.evaluate();
			noe++;
		}
		if (maxPlayer) {
			for (GameState suCnt : successors) {
				double val = alphabeta(suCnt, depth - 1, alpha, beta, !maxPlayer);
				if (val > alpha) {
					alpha = val;
					big = suCnt.getLastMove();
				} else if (val == alpha) {
					if (suCnt.getLastMove() < big) {
						big = suCnt.getLastMove();
					}
				}
			}
			value = alpha;
		} else {
			for (GameState suCnt : successors) {
				double val = alphabeta(suCnt, depth - 1, alpha, beta, !maxPlayer);
				if (val < beta) {
					beta = val;
					big = suCnt.getLastMove();
				} else if (val == beta) {
					if (suCnt.getLastMove() < big) {
						big = suCnt.getLastMove();
					}
				}
			}
			value = beta;
		}

		move = big;
		factor = (double) (nov - 1) / (nov - noe);
		// TODO Add your code here
	}

	/**
	 * This method is used to implement alpha-beta pruning for both 2 players
	 * 
	 * @param state     This is the current game state
	 * @param depth     Current depth of search
	 * @param alpha     Current Alpha value
	 * @param beta      Current Beta value
	 * @param maxPlayer True if player is Max Player; Otherwise, false
	 * @return int This is the number indicating score of the best next move
	 */
	private double alphabeta(GameState state, int depth, double alpha, double beta, boolean maxPlayer) {
		// TODO Add your code here
		if (maxPlayer)
			value = maxV(state, depth, alpha, beta);
		else
			value = minV(state, depth, alpha, beta);
		return value;
		// return 0.0;
	}

	private double maxV(GameState state, int depth, double alpha, double beta) {
		if(maxDepthp-depth>maxDepth) {
			maxDepth=maxDepthp-depth;
		}
		nov++;
		List<GameState> successors = state.getSuccessors();
		double value;
		if (successors.isEmpty() || depth == 0) {
			noe++;
			value = state.evaluate();
			return value;
		}
		value = Double.NEGATIVE_INFINITY;
		//for (int i = 0; i < successors.size(); i++) {
		for(GameState succ:successors) {
			value = Math.max(value, minV(succ, depth - 1, alpha, beta));
			if (value >=beta )
				//return value;
				break;
			alpha = Math.max(alpha, value);
		}

		return value;
	}

	private double minV(GameState state, int depth, double alpha, double beta) {
		if(maxDepthp-depth>maxDepth) {
			maxDepth=maxDepthp-depth;
		}
		nov++;
		List<GameState> successors = state.getSuccessors();
		double value;
		if (successors.isEmpty() || depth == 0) {
			noe++;
			value = state.evaluate();
			return value;
		}
		value = Double.POSITIVE_INFINITY;
		//for (int i = 0; i < successors.size(); i++) {
		for(GameState succ:successors) {
			value = Math.min(value, maxV(succ, depth - 1, alpha, beta));
			if (value <= alpha)
				//return value;
				break;
			beta = Math.min(beta, value);
		}
		return value;
	}

}
