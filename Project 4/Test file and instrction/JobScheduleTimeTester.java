import org.junit.*;
import static org.junit.Assert.*;

 /**
 * A class that tests the methods of scheduling job according to time classes.
 * @author Bruce Dong
 */
public class JobScheduleTimeTester {
  
  /**
   * Tests the ability to schedule job as late as possible method.
   */
  @Test
  public void startLateTester() {
    ScheduleAsLateAsPossible sALP = new ScheduleAsLateAsPossible();
    Schedule test = new Schedule();
    Job job1 =new Job(15, 25, 5, 800);
    Job job2 =new Job(20, 24, 3, 800);
    Job job3 =new Job(10, 19, 4, 800);
    // Testing that without adding any job, it allows adding job
    assertTrue("see above", sALP.scheduleJob(test,job2));
    assertTrue("One job is successfully added to back", job2.equals(test.getBack().getElement().getJob()));
    System.out.println("The situation of the first job setting: \n");
    System.out.println(test.getBack().getElement().toString() + "\n");
    
    // Testing method works functionally when there is only one job that is added to back
    test = new Schedule();
    ScheduleSlot.resetLastId();
    test.addToBack(new ScheduleSlot(job1, 20));
    // Testing it cannot add job that need to finish after the latest start time of latest job
    assertFalse("see above", sALP.scheduleJob(test,job2));
    // Testing it can add job that need to finish before the latest start time of latest job
    assertTrue("see above", sALP.scheduleJob(test,job3));
    assertTrue("One job is successfully added to back", job1.equals(test.getBack().getElement().getJob()));
    assertTrue("One job is successfully added to front", job3.equals(test.getFront().getElement().getJob()));
    System.out.println("----------------------------Break Line ---------------------------");
    System.out.println("The situation of the second job setting: \n");
    for( ScheduleSlot x: test)
      System.out.println(x.toString() + "\n");
    
    // Testing method works functionally when there is only one job that is added to front
    test = new Schedule();
    ScheduleSlot.resetLastId();
    test.addToFront(new ScheduleSlot(job1, 20));
    // Testing it cannot add job that need to finish after the latest start time of latest job
    assertFalse("see above", sALP.scheduleJob(test,job2));
    // Testing it can add job that need to finish before the latest start time of latest job
    assertTrue("see above", sALP.scheduleJob(test,job3));
    assertTrue("One job is successfully added to back", job1.equals(test.getBack().getElement().getJob()));
    assertTrue("One job is successfully added to front", job3.equals(test.getFront().getElement().getJob()));
    System.out.println("----------------------------Break Line ---------------------------");
    System.out.println("The situation of the third job setting: \n");
    for( ScheduleSlot x: test)
      System.out.println(x.toString() + "\n");
    
    // Testing method works functionally in two situation 
    test = new Schedule();
    ScheduleSlot.resetLastId();
    sALP.scheduleJob(test,job1);
    // 1, the job cannot finish before the time that the current earliest job need to start 
    Job job4 =new Job(21, 24, 3, 800);
    assertFalse("see above", sALP.scheduleJob(test,job4));
    Job job5 =new Job(15, 20, 5, 800);
    sALP.scheduleJob(test,job5);
    // 2, the job can finish before the time that the current earliest job need to start 
    // when there are both a front and a back node in the DLList 
    Job job6 =new Job(12, 14, 2, 800);
    assertTrue("see above", sALP.scheduleJob(test,job6));
    //Testing setting up start and finish time correctly 
    assertEquals("the ddl - start time should equal to duration " ,
                test.getFront().getElement().getJob().getDuration() , 
                test.getFront().getElement().getJob().getDeadline() - 
                test.getFront().getElement().getJob().getEarliestStart());
    assertTrue("the ddl - start time should equal to duration +1 if the duration fills from the start time till the end" ,
                test.getFront().getNext().getElement().getJob().getDuration() < 
                test.getFront().getNext().getElement().getJob().getDeadline() - 
                test.getFront().getNext().getElement().getJob().getEarliestStart()+1);
    //Checking the job before each one should start after the deadline of the previous job
    assertTrue("see above",test.getFront().getElement().getJob().getDeadline()
                 < test.getFront().getNext().getElement().getStartTime());
    assertTrue("see above",test.getFront().getNext().getElement().getJob().getDeadline()
                 <= test.getFront().getNext().getNext().getElement().getStartTime());
    System.out.println("----------------------------Break Line ---------------------------");
    System.out.println("The situation of the fourth job setting: \n");
    for( ScheduleSlot x: test)
      System.out.println(x.toString() + "\n");
    
    
  }
  
  /**
   * Tests the ability to schedule job as early as possible method.
   */
  @Test
  public void startEarlyTester() {
    ScheduleAsEarlyAsPossible sAEP = new ScheduleAsEarlyAsPossible();
    Schedule test = new Schedule();
    Job job1 =new Job(11, 14, 2, 800);
    Job job2 =new Job(0, 13, 3, 800);
    Job job3 =new Job(13, 15, 1, 800);
    // Testing that without adding any job, it allows adding job
    assertTrue("see above", sAEP.scheduleJob(test,job2));
    assertTrue("One job is successfully added to front", job2.equals(test.getFront().getElement().getJob()));
    System.out.println("----------------------------Now is early start trail ---------------------------");
    System.out.println("The situation of the fifth job setting: \n");
    System.out.println(test.getFront().getElement().toString() + "\n");
    
    // Testing method works functionally when there is only one job that is added
    test = new Schedule();
    ScheduleSlot.resetLastId();
    test.addToBack(new ScheduleSlot(job1, 11));
    // Testing it cannot add job that need to start before the earliest deadline of latest job
    assertFalse("see above", sAEP.scheduleJob(test,job2));
    // Testing it can add job that can start before finishing previous job 
    assertTrue("see above", sAEP.scheduleJob(test,job3));
    assertTrue("One job is successfully added to front", job1.equals(test.getFront().getElement().getJob()));
    assertTrue("One job is successfully added to front", job1.equalsNoId(test.getBack().getPrevious().getElement().getJob()));
    assertTrue("One job is successfully added to back", job3.equalsNoId(test.getFront().getNext().getElement().getJob()));
    assertTrue("One job is successfully added to back", job3.equalsNoId(test.getBack().getElement().getJob()));
    System.out.println("----------------------------Break Line ---------------------------");
    System.out.println("The situation of the sixth job setting: \n");
    for( ScheduleSlot x: test)
      System.out.println(x.toString() + "\n");
    
    // Testing method works functionally when there is only one job that is added to front
    test = new Schedule();
    ScheduleSlot.resetLastId();
    test.addToFront(new ScheduleSlot(job1, 11));
    // Testing it cannot add job that need to start before the earliest deadline of latest job
    assertFalse("see above", sAEP.scheduleJob(test,job2));
    // Testing it can add job that can start before finishing previous job 
    assertTrue("see above", sAEP.scheduleJob(test,job3));
    assertTrue("One job is successfully added to back", job3.equals(test.getBack().getElement().getJob()));
    assertTrue("One job is successfully added to front", job1.equals(test.getFront().getElement().getJob()));
    System.out.println("----------------------------Break Line ---------------------------");
    System.out.println("The situation of the seventh job setting: \n");
    for( ScheduleSlot x: test)
      System.out.println(x.toString() + "\n");
    
    // Testing method works functionally in two situation 
    test = new Schedule();
    ScheduleSlot.resetLastId();
    sAEP.scheduleJob(test,job1);
    // 1, the job must start before the current job is finished 
    assertFalse("see above", sAEP.scheduleJob(test,job2));
    Job job5 =new Job(14, 17, 3, 800);
    sAEP.scheduleJob(test,job5);
    // 2, the job can start right after the previous job is finished 
    // when there are both a front and a back node in the DLList 
    Job job6 =new Job(17, 19, 1, 800);
    assertTrue("see above", sAEP.scheduleJob(test,job6));
    Job job7 =new Job(30, 32, 2, 800);
    sAEP.scheduleJob(test,job7);
    
    //Testing setting up start and finish time correctly 
    assertTrue("the scheduled start time + duration should be before the deadline " ,
               test.getFront().getElement().getJob().getDuration() + 
               test.getFront().getElement().getStartTime() < 
               test.getFront().getElement().getJob().getDeadline());
    assertTrue("the scheduled start time + duration should be before the deadline ", 
               test.getFront().getNext().getNext().getNext().getElement().getJob().getDuration() +
               test.getFront().getNext().getNext().getNext().getElement().getStartTime()< 
               test.getFront().getNext().getNext().getNext().getElement().getJob().getDeadline() +
               test.getFront().getNext().getNext().getNext().getElement().getJob().getDuration() - 1);
    //Checking the job before each one should start after the finishing previous job
    assertTrue("see above",test.getFront().getElement().getJob().getEarliestStart() +
               test.getFront().getElement().getJob().getDuration()
               < test.getFront().getNext().getElement().getStartTime());
    assertTrue("see above",test.getFront().getNext().getElement().getJob().getEarliestStart() +
               test.getFront().getNext().getElement().getJob().getDuration() 
               <= test.getFront().getNext().getNext().getElement().getStartTime());
    System.out.println("----------------------------Break Line ---------------------------");
    System.out.println("The situation of the eighth job setting: \n");
    for( ScheduleSlot x: test)
      System.out.println(x.toString() + "\n");
  }
  
  /**
   * Tests the ability to schedule job as the input
   */
   @Test
   public void scheduleTester() {
     Job job1 =new Job(11, 14, 2, 800);
     Job job5 =new Job(14, 17, 3, 800);
     Job job6 =new Job(17, 19, 1, 800);
     Job job7 =new Job(30, 32, 2, 800);
     Job[] job = new Job[] {job5,job1,job7,job6};
     ScheduleAsEarlyAsPossible sAEP = new ScheduleAsEarlyAsPossible();
     Schedule test = JobScheduler.scheduleJobs(sAEP, Job.getStartComparator(), job);
     // Testing all job is added to the schedule class in order
     assertTrue("The first job is job 1",job1.equals(test.getFront().getElement().getJob()));
     assertTrue("The second job is job 5",job5.equals(test.getFront().getNext().getElement().getJob()));
     assertTrue("The third job is job 6",job6.equals(test.getFront().getNext().getNext().getElement().getJob()));
     assertTrue("The last job is job 7",job7.equals(test.getBack().getElement().getJob()));
     assertEquals("The profit is calculated correctly",3200, test.getTotalProfit());
   }
}