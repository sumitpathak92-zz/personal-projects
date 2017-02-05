public class SearchForRange {
	// DO NOT MODIFY THE LIST
	public ArrayList<Integer> searchRange(final List<Integer> a, int b) {
	        ArrayList<Integer> alist = new ArrayList<Integer>();
	        
	        int firstIndex = returnCount(a, b, true);
    	    int lastIndex = returnCount(a, b, false);
    	    if(firstIndex == -1 || lastIndex == -1) {
    	        alist.add(-1);
    	        alist.add(-1);
    	        return alist;
    	    }
    	    else {
    	        alist.add(firstIndex);
    	        alist.add(lastIndex);
    	        return alist;
    	        }
    	    }
	
	public int returnCount(final List<Integer> a, int b, boolean searchFirst) {
	    int lo=0, hi=a.size()-1;
        int count = 0, result=-1;
        while(lo<=hi) {
            int mid = lo + (hi-lo)/2;
            if(a.get(mid)==b) {
                result = mid;
                if(searchFirst) {
                    hi = mid-1;
                }
                else 
                    lo = mid + 1;
            }
            else if(b<a.get(mid)) 
                hi = mid-1;
            else 
                lo = mid + 1;
        }
        return result;
	}
}
