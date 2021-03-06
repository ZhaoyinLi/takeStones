

public class Helper {
	
	/** 
    * Class constructor.
    */
	

	/**
	* This method is used to check if a number is prime or not
	* @param x A positive integer number
	* @return boolean True if x is prime; Otherwise, false
	*/
	public static boolean isPrime(int x) {
		
		// TODO Add your code here
		boolean flag = false;
        for(int i = 2; i <= x/2; ++i)
        {
            // condition for nonprime number
            if(x % i == 0)
            {
                flag = true;
                break;
            }
            return flag;
        }

		return false;
	}

	/**
	* This method is used to get the largest prime factor 
	* @param x A positive integer number
	* @return int The largest prime factor of x
	*/
	public static int getLargestPrimeFactor(int x) {
          
    	// TODO Add your code here
		if(x>0) {
		int i = 2;
		int res = 1;
		while (x > 2) {
			if (x%i == 0) {
				x /= i;
				res = i;
			}	
			else
				i++;
		}
		return res;
		}
		else 
			return -1;
	}
	
}