import java.util.*;


// Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.

// Example 1:

// Intervals: [[1,4], [2,5], [7,9]]
// Output: [[1,5], [7,9]]
// Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into 
// one [1,5].

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

class Main {

  public static List<Interval> merge(List<Interval> intervals) {
    if (intervals.size() <= 1)
      return intervals;

    // Sort by ascending starting point using an anonymous Comparator
    intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

    List<Interval> result = new LinkedList<Interval>();
    int start = intervals.get(0).start;
    int end = intervals.get(0).end;

    for (Interval interval : intervals) {
      if (interval.start <= end) // Overlapping intervals, move the end if needed
        end = Math.max(end, interval.end);
      else { // Disjoint intervals, add the previous one and reset bounds
        result.add(new Interval(start, end));
        start = interval.start;
        end = interval.end;
      }
    }

    // Add the last interval
    result.add(new Interval(start, end));
    return result;
  }

  public static void main(String[] args) {
    List<Interval> interval = new ArrayList<>();
    interval.add(new Interval(1, 4));

    interval.add(new Interval(2, 5));

    interval.add(new Interval(7, 9));
    List<Interval> result = Main.merge(interval);
    System.out.println("Merging Intervals");
    
    for (Interval interval2 : result) {
      System.out.println("[" + interval2.start + ", " + interval2.end + " ] ");
      System.out.println();
    }
  }
}