import org.junit.*;
import static org.junit.Assert.*;

 /**
 * A class that tests the methods of the ScheduleSlot class.
 * @author Bruce Dong
 */
public class ScheduleSlotTester {
  /**
   * Tests the getter setter constructor of Job.
   */
  @Test
  public void testGetSetConstructor() {
    Job job1 = new Job(10,15,4,5);
    ScheduleSlot sSlot = new ScheduleSlot(job1 , 11);
    // Test getter methods works well
    assertTrue("the job it get is the same as the input", job1.equals(sSlot.getJob()));
    assertEquals("the start time it get is the same as inpute",11, sSlot.getStartTime());
    //check that people cannot set the start time earlier than the possible earliest start timne
    sSlot.setStartTime(1);
    assertEquals("nothing should be changed",11, sSlot.getStartTime());
    //check that people cannot set the start time latter than the deadline
    sSlot.setStartTime(133);
    assertEquals("nothing should be changed",11, sSlot.getStartTime());
    // Check correct set the time.
    sSlot.setStartTime(12);
    assertEquals("nothing should be changed",12, sSlot.getStartTime());
  }
    
}