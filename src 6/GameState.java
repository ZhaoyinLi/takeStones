

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameState {
    private int size;            // The number of stones
    private boolean[] stones;    // Game state: true for available stones, false for taken ones
    private int lastMove;        // The last move

    /**
     * Class constructor specifying the number of stones.
     */
    public GameState(int size) {

        this.size = size;

        //  For convenience, we use 1-based index, and set 0 to be unavailable
        this.stones = new boolean[this.size + 1];
        this.stones[0] = false;

        // Set default state of stones to available
        for (int i = 1; i <= this.size; ++i) {
            this.stones[i] = true;
        }

        // Set the last move be -1
        this.lastMove = -1;
 
    }

    /**
     * Copy constructor
     */
    public GameState(GameState other) {
        this.size = other.size;
        this.stones = Arrays.copyOf(other.stones, other.stones.length);
        this.lastMove = other.lastMove;
    }


    /**
     * This method is used to compute a list of legal moves
     *
     * @return This is the list of state's moves
     */
    public List<Integer> getMoves() {
        // TODO Add your code here
    	List<Integer> myList = new ArrayList<>();
    	
    	//boolean flag=false;
//    	for(int i=0;i<size;i++) {
//    	if(stones[i]==true) {
//    		flag=true;
//    	 }
//    	}
//    	if(flag==false) {
//    		return myList;
//    	}// all stones have been taken
    	
    	if(lastMove == -1) { // initial stone
    	 double num=size/2.0;
    	  for(int x=1;x<num;x++) {
    		  if (x%2!=0) {
    		  myList.add(x); 
    		  }
    	  }
    		
    	}
    	//how to check the stones number and compare?
    	else {
    		for(int i=1;i<=size;i++) { //normal states and the first stone was taken
    	    	if(stones[i]==true) {
    	    		if(i%lastMove==0||lastMove%i==0) {
    	    			 myList.add(i); 
    	    		}
    	    	}
    		}
    	}
    	return myList;
    }


    /**
     * This method is used to generate a list of successors
     * using the getMoves() method
     *
     * @return This is the list of state's successors
     */
    public List<GameState> getSuccessors() {
        return this.getMoves().stream().map(move -> {
            var state = new GameState(this);
            state.removeStone(move);
            return state;
        }).collect(Collectors.toList());
    }


    /**
     * This method is used to evaluate a game state based on
     * the given heuristic function
     *
     * @return int This is the static score of given state
     */
    public double evaluate() {
    	
    	double score = 0.0;
    	int cnt=0;
    	for(int i=1;i<=size;i++) {
        	if(stones[i]==false) {
        		cnt++; //to get whose step
        	}
    	}
    	boolean maxPlayer=true;
    	if(cnt%2==0) {
    		maxPlayer=false;
    	}
    	List <Integer> getMove=getMoves();
    	if(getMove.size() == 0) {
    		if(maxPlayer) 
    		return 1.0;//max get 1 when the end game state
    		else 
    		return -1.0;//min get 1 when the end game state
    		
    	}
    	
    	else {
    		
    		if(stones[1]) {// stone 1 is not token
    			return 0;
    		}
    		if (lastMove==1) { 
    			if((size - cnt)%2!=0)score=0.5;
    			else score=-0.5;
    		}
    	//Helper prime = new Helper();
    	boolean isP=Helper.isPrime(lastMove);
    	int largestPrime=Helper.getLargestPrimeFactor(lastMove);
    	List<GameState>successors=getSuccessors();
    	if(isP) {
    		int helper=0;
    		for(int i=0; i<successors.size();i++) {
    			if(successors.get(i).getLastMove()%lastMove==0) {
    				helper++;
    			}
    		}
    		if(helper%2==1) {
    			score=0.7;
    		}
    	    else { 
    	    	score=-0.7;
    	    }
    	}
    	else {
    	int helper2=0;
    	for(int i=0; i<successors.size();i++) {
			if(successors.get(i).getLastMove()%largestPrime==0) {
				helper2++;
			}
		 }
    	if(helper2%2==1) {
    		score=0.6;
    	 }
    	else {
    		score=-0.6;
    	  }
    	}
    	
    	if(maxPlayer) {
    		return score;
    	}
    	else {
    		score=score*(-1.0);
    		return score;
    	}
      
    	
      }
    }

    /**
     * This method is used to take a stone out
     *
     * @param idx Index of the taken stone
     */
    public void removeStone(int idx) {
        this.stones[idx] = false;
        this.lastMove = idx;
    }

    /**
     * These are get/set methods for a stone
     *
     * @param idx Index of the taken stone
     */
    public void setStone(int idx) {
        this.stones[idx] = true;
    }

    public boolean getStone(int idx) {
        return this.stones[idx];
    }

    /**
     * These are get/set methods for lastMove variable
     *
     * @param move Index of the taken stone
     */
    public void setLastMove(int move) {
        this.lastMove = move;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    /**
     * This is get method for game size
     *
     * @return int the number of stones
     */
    public int getSize() {
        return this.size;
    }
    
    public int getIniStonAmt() {
    	return this.stones.length-1;
    }

}	
