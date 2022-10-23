/** components of a schedule slot file
 *
 * @author Bruce Dong
 */
public class ScheduleSlot  {
  // This field contains the job that is stored
  private Job job;
  // This field contains the start time of this job
  private int startTime;
  // a field to store the last id  used
  private static int lastJobId = 0;
  // This field contains the unique id of the job
  private int id;
  
  /**
   * Stores basic information of a ScheduleSlot
   * @param job The job we want to store
   * @param startTime  the scheduled start time
   */
  public ScheduleSlot( Job job, int startTime ){
    this.job =job;
    if(startTime >= job.getEarliestStart() && startTime <= job.getDeadline() )
      this.startTime = startTime;
    else
      this.startTime = job.getEarliestStart();
    id = ScheduleSlot.lastJobId +1;
    ScheduleSlot.lastJobId =id;
  }

  /**
   * Returns the job.
   * @return the job
   */
  public Job getJob() {
    return job;
  }

  /**
   * Returns the  start time of the job.
   * @return the  start time of the job
   */
  public int getStartTime() {
    return startTime;
  }

  /**
   * Changes the  start time of the job
   * @param newStartTime the new  start time for the job
   */
  public void setStartTime(int newStartTime) {
    if(newStartTime >= job.getEarliestStart() && newStartTime <= job.getDeadline())
      this.startTime = newStartTime;
  }
  
  /**
   * Changes the  start time of the job
   * @param newStartTime the new  start time for the job
   */
  public void setCompoundJobStartTime(int newStartTime) {
    this.startTime = newStartTime;
  }
  
  /**
   * return every information of the schedule slot
   * @return  every information of the schedule slot
   */
  public String toString(){
    return job.toString() + "\n" + "Slot id:  "+ id + ", scheduled start time: " +startTime
       + ":00, scheduled finish time: " + (startTime+job.getDuration() -1) +":59"+"\n";
  }
  
  /**
   * return every information of the schedule slot
   * @return  every information of the schedule slot
   */
  public String toCompoundString(){
    return job.toCompoundString() + "\n" + "Slot id:  "+ id + ", scheduled start time: " +startTime
       + ":00, scheduled finish time: " + (startTime+job.getDuration() -1) +":59"+"\n";
  }

  /**
   * Reset the last id of the job to 0
   */
  public static void resetLastId() {
    ScheduleSlot.lastJobId = 0;
  }
}