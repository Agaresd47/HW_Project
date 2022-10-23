import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.*;

 /**
 * A class that tests the methods of the Job class.
 * @author Bruce Dong
 */
public class JobTester {

   /**
    * Helper method to compare two job arrays.
    */
   private void equalsJobArrays(String string, Job[] expected, Job[] received) {
     assertEquals(expected.length, received.length);
     for (int i = 0; i < expected.length; i++) {
       assertTrue(expected[i].equals(received[i]));
     }
   }

  /**
   * Tests the getter setter constructor of Job.
   */
  @Test
  public void testGetSetConstructor() {
    Job.resetLastId();
    Job test = new Job(2,3,4,5);
    assertEquals("Correctly setting the initial id of the job", 1, test.getId());
    assertEquals("Correctly setting the initial earliest start time of the job", 2, test.getEarliestStart());
    assertEquals("Correctly setting the initial deadline of the job", 3, test.getDeadline());
    assertEquals("Correctly setting the initial duration of the job", 4, test.getDuration());
    assertEquals("Correctly setting the initial profit of the job", 5, test.getProfit());
    test.setId(2);
    test.setEarliestStart(3);
    test.setDeadline(4);
    test.setDuration(5);
    test.setProfit(6);
    assertEquals("Correctly setting the new id of the job", 2, test.getId());
    assertEquals("Correctly setting the new earliest start time of the job", 3, test.getEarliestStart());
    assertEquals("Correctly setting the new deadline of the job", 4, test.getDeadline());
    assertEquals("Correctly setting the new duration of the job", 5, test.getDuration());
    assertEquals("Correctly setting the new profit of the job", 6, test.getProfit());
  }

  /**
   * Tests the three comparable methods of Job.
   */
  @Test
  public void testComparable() {
    Job.resetLastId();
    //  Job (int id, int earliestStart, int deadline, int duration, int profit)
    Job job1 = new Job(2,4,4,5);
    Job job2 = new Job(3,3,5,6);
    // Test all three comparator work functionally when the variable are the same
    assertEquals("Correctly compare the same id", 0, job1.compareTo(job1));
    assertEquals("Correctly compare the smaller input id", 1, job2.compareTo(job1));
    assertEquals("Correctly compare the greater input id", -1, job1.compareTo(job2));
    // Test compare two jobs method works functionally
    assertTrue("correctly compare the same job variable", job1.equals(job1));
    assertFalse("correctly compare the same job variable", job1.equals(job2));
    Job[] jobArr = new Job[] {job2, job1};
    Job[] jobTarget = new Job[] {job1, job2};
    Arrays.sort(jobArr, Job.getProfitComparator());
    equalsJobArrays("Test that the array is sorted by profit",jobTarget, jobArr );
    jobArr = new Job[] {job2, job1};
    Arrays.sort(jobArr, Job.getStartComparator());
    equalsJobArrays("Test that the array is sorted by start time",jobTarget, jobArr );
    jobArr = new Job[] {job2, job1};
    Arrays.sort(jobArr, Job.getDDLComparator());
    equalsJobArrays("Test that the array is sorted by deadline (latter deadline put at first",jobTarget, jobArr );
  }
  
}