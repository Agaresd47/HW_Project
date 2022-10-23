import org.junit.*;
import static org.junit.Assert.*;

 /**
 * A class that tests the methods of the CompoundJob class.
 * @author Bruce Dong
 */
public class CompoundJobTester {
  
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
   * Tests the getter and constructor of Job.
   */
  @Test
  public void testGetAndConstructor() {
    Job job1 = new Job(2,7,5,100);
    Job job2 = new Job(3,9,3,200);
    Job job3 = new Job(4,18,6,300);
    Job job4 = new Job(15,25,5,800);
    Job[] cJobArr = new Job[] {job1, job2, job3, job4};
    CompoundJob cJob1 = new CompoundJob(0, job1);
    CompoundJob cJob2 = new CompoundJob(0, job1, job2);
    CompoundJob cJob3 = new CompoundJob(0, job4, job1, job3, job2);
    // Test the profit calculation works with 1, 2 and many jobs
    assertEquals("Correctly add profit of 1 job", 100, cJob1.getProfit());
    assertEquals("Correctly add profit of 2 job", 300, cJob2.getProfit());
    assertEquals("Correctly add profit of 3 job", 1400, cJob3.getProfit());
    // Test getting sub job method and each sub job is organized in order of its start time
    equalsJobArrays("Correctly get the sub job", cJobArr, cJob3.getAllJob());
    // Checking all other field are settled correctly
    assertEquals("Correctly set earliest start time", 2, cJob3.getEarliestStart());
    assertEquals("Correctly set deadline", 25, cJob3.getDeadline());
    assertEquals("Correctly set duration", 19, cJob3.getDuration());
  }
  
  /**
   * Tests the ability to sort compound job and schedule all of them in to schedule file 
   */
  @Test
  public void sortAllJobTester() {
    // Since it only calls sortAllJob mehod before adding to the schedule list, 
    // I will only verify that each sub job is proparly sechduled start time 
    Job job1 = new Job(2,7,5,100);
    Job job2 = new Job(3,12,3,200);
    Job job3 = new Job(4,18,6,300);
    Job job4 = new Job(15,25,5,800);
    Job job5 = new Job(0,2,2,100);
    CompoundJob cJob3 = new CompoundJob(0, job4, job1, job3, job2);
    CompoundJob cJob4 = new CompoundJob(0, job4, job1, job3, job2);
    Schedule earlyOne = new Schedule();
    Schedule lateOne = new Schedule();
    ScheduleAsLateAsPossible sALP = new ScheduleAsLateAsPossible();
    ScheduleAsEarlyAsPossible sAEP = new ScheduleAsEarlyAsPossible();
    sALP.scheduleJob(lateOne,cJob3);
    sALP.scheduleJob(lateOne,job5);
    sAEP.scheduleJob(earlyOne,job5);
    sAEP.scheduleJob(earlyOne,cJob4);
    // Test the method works functionally in schedule as late as possible
    ScheduleSlot[] lateSchedule= lateOne.getBack().getElement().getJob().getsAllJob();
    assertEquals("The first sub job is successfully scheduled",6,lateSchedule[0].getStartTime());
    assertEquals("The second sub job is successfully scheduled",11,lateSchedule[1].getStartTime());
    assertEquals("The third sub job is successfully scheduled",14,lateSchedule[2].getStartTime());
    assertEquals("The last sub job is successfully scheduled",20,lateSchedule[3].getStartTime());
    // Test the method works functionally in schedule as early as possible
    ScheduleSlot[] earlySchedule= earlyOne.getBack().getElement().getJob().getsAllJob();
    assertEquals("The first sub job is successfully scheduled",2,earlySchedule[0].getStartTime());
    assertEquals("The second sub job is successfully scheduled",7,earlySchedule[1].getStartTime());
    assertEquals("The third sub job is successfully scheduled",10,earlySchedule[2].getStartTime());
    assertEquals("The last sub job is successfully scheduled",16,earlySchedule[3].getStartTime());
  }
 }