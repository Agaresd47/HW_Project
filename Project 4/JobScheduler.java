import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * The class that help people schedule jobs
 *
 * @author Bruce Dong
 */
public class JobScheduler {
  
  /**
   * Schedule all jobs and return it as a schedule varaible
   * @param metric  the mertic manner that we want 
   * @param jobComparator       the comparator format that we want
   * @param subJobs       all the possible job that we want to add
   * @return a schedule varaible that contains all the shcedulable job
   */
  public static Schedule  scheduleJobs(ScheduleMetric metric, Comparator<Job> jobComparator, Job... subJobs){
    Arrays.sort(subJobs, jobComparator);
    Schedule schedule = new Schedule();
    // add each job
    for(Job eachJob: subJobs)
      metric.scheduleJob(schedule, eachJob);
    return schedule;
  }
  
  /** 
   * The main method that takes a jobfile as an input and schedule it in start as early/late as possible manner
   * @param args no usage of it 
   */
  public static void main(String[] args) throws IOException{
    //System.out.println("type in the file that you want to open");
    // get the input job
    File importJob = new File ("E:/zjoblist.txt");
    if(!importJob.isFile()) {
      System.out.println("You need to type a correct file address!");
      return;
    }
    //Creating Scanner instance to read File in Java
    Scanner scnr = new Scanner(importJob);
    // see how many jobs are in the file
    int count =0;
    while(scnr.hasNextLine()){
      count++;
      scnr.nextLine();
    }
    // Reset the scanner 
    scnr = new Scanner(importJob);
    // Create the array that contains all the job
    Job[] jobArr = new Job[count];
    // add them to the array
    for( int index =0; index <jobArr.length ; ++index)
      jobArr[index] = new Job(scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt());

    // create the metric
    ScheduleAsEarlyAsPossible sAEAP = new ScheduleAsEarlyAsPossible();
    // create the metric
    ScheduleAsLateAsPossible sALAP = new ScheduleAsLateAsPossible();
    // create the latest start schedule
    Schedule lateStart= JobScheduler.scheduleJobs(sALAP, Job.getDDLComparator(), jobArr);
    ScheduleSlot.resetLastId();
    // create the early start schedule
    Schedule earlyStart= JobScheduler.scheduleJobs(sAEAP, Job.getStartComparator(), jobArr);

    
    if(earlyStart.getTotalProfit() > lateStart.getTotalProfit()) {
      System.out.println("Using the start early schedule would gain more profit\n");
      System.out.println("Here is the start as early as possible schedule:");
      System.out.println(earlyStart.toString());
      System.out.println("Here is the start as late as possible schedule:");
      System.out.println(lateStart.toString());
    }
    else {
      System.out.println("Using the start late schedule would gain more profit\n");
      System.out.println("Here is the start as late as possible schedule:");
      System.out.println(lateStart.toString());
      System.out.println("Here is the start as early as possible schedule:");
      System.out.println(earlyStart.toString());
    }
  }
}
