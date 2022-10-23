import java.util.Arrays;

/** components of a compound job file
 *
 * @author Bruce Dong
 */
public class CompoundJob extends Job {
  // This field stores all the job as an array
  private Job[] allJob;
  // This field stores all the sub job schedule slot as an array
  private ScheduleSlot[] sAllJob;
  
  /**
   * Stores basic information of a job
   * @param profit   total payment of all jobs
   * @param subJobs  each sub job
   */
  public CompoundJob (int profit, Job... subJobs) {
    super(0,0,0,0);
    allJob = subJobs;
    setEarliestStart(sortEarly());
    setDeadline(storeDDL());
    setDuration(storeDuration());
    setProfit(storeProfit());
    sAllJob = new ScheduleSlot[allJob.length];
    int index =0;
    //record all the job in array to a schedule slot
    for(Job x: allJob){
      sAllJob[index]= new ScheduleSlot(x, 0);
      ++ index;
    }
  }

  /**
   * Returns all sub jobs of the compound job as an array
   * @return all sub jobs of the compound job as an array
   */
  public Job[] getAllJob() {
    return allJob;
  }

  /**
   * Returns all sub jobs of the compound job as a ScheduleSlot
   * @return all sub jobs of the compound job as a ScheduleSlot
   */
  public ScheduleSlot[] getsAllJob() {
    return sAllJob;
  }
  
  /**
   * Sort the array in order of earliest start and store the earliest start time 
   * @return the earliest start time of the compound job
   */
  public int sortEarly() {
    Arrays.sort(allJob, Job.getStartComparator());
    return allJob[0].getEarliestStart();
  }
  
  /**
   * Find the latest deadline and store it 
   * @return the latest deadline of the compound job
   */
  public int storeDDL() {
    // the deadline of the compound job
    int deadlineC = allJob[0].getDeadline();
    // find the latest deadline and store it for each job
    for (Job job : allJob) {
      if (deadlineC < job.getDeadline())
        deadlineC = job.getDeadline();
    }
    return deadlineC;
  }
  
  /**
   * calculate the total profit and store it 
   * @return the total profit
   */
  public int storeProfit() {
    int subProfitC =0;
    //calculate the total profit and store it for each job
    for (Job job : allJob)
      subProfitC += job.getProfit();
    return subProfitC;
  }
  
  /**
   * Find the total duration and store it
   * @return the total duration 
   */
  public int storeDuration() {
    int durationC = 0;
    //calculate the total duration and store it for each job
    for (Job job : allJob)
      durationC += job.getDuration();
    return durationC;
  }

  /**
   * print all the sub job of this compound job
   * @return the compound job as a string
   */
  public String toString(){
    // We need a string to record these sentence right?
    String s = new String();
    s += "The jobs information below are from a compound job:\n";
    s += "The slot id of each sub job is the ScheduleSlot that contains the information of each subJob\n\n";
    // for each job, record its information
    for( ScheduleSlot x: sAllJob)
      s += x.toCompoundString() + "\n\n";
    s+= "The above are all information of the compound job\n";
    s+= "The slot id below is the slot id for the ScheduleSlot that contains the CompoundJob , and here is the gathered information of the compoumd job";
    return s;
  }
  


  /**
   * set each sub job's start time in schedule according to the compound job's start time
   * @param startTime the start time of the first job
   */
  public void sortAllJob(int startTime){
    // find each schedule slot
    for(ScheduleSlot slotJob:sAllJob){
      if(startTime < slotJob.getJob().getEarliestStart())
        // if its possible start time is earlier than the open time, store the schedule start time as its open time
        startTime = slotJob.getJob().getEarliestStart() + slotJob.getJob().getDuration();
      else {
        // Store the start time as finishing the previous job
        slotJob.setCompoundJobStartTime(startTime);
        startTime += slotJob.getJob().getDuration();
      }
    }
  }

}