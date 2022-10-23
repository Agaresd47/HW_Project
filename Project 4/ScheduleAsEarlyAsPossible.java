/** interface that contains the method of schedule a job as early as possible
 *
 * @author Bruce Dong
 */
public class ScheduleAsEarlyAsPossible implements ScheduleMetric{
  
  /**
   * see whether we can add new job (start as early as possible) to the schedule
   * If we can then, add it
   * @param schedule the schedule that we try to add new job
   * @param job the job that we want to add
   * @return true if we can add the job
   */
  public  boolean scheduleJob (Schedule schedule, Job job){
    // automatically add if nothing is in the schedule
    if(schedule.getFront() == null && schedule.getBack() == null){
      if(job instanceof CompoundJob)
        ((CompoundJob) job).sortAllJob(job.getEarliestStart());
      schedule.addToFront(new ScheduleSlot ( job, job.getEarliestStart()));
      return true;
    }
    // field that record the latest job's start time
    int cStart;
    // field that record the latest job's duration
    int cDuration;
    // record the job's duration
    int duration = job.getDuration();
    if(schedule.getBack() == null){
      cStart = schedule.getFront().getElement().getStartTime();
      cDuration = schedule.getFront().getElement().getJob().getDuration();
    }
    else{
      cStart = schedule.getBack().getElement().getStartTime();;
      cDuration = schedule.getBack().getElement().getJob().getDuration();
    }
    
    int possiblyEarliestStartTime = cStart + cDuration;
    // if it can start before the earliest start time of the job, then add it. 
    if(possiblyEarliestStartTime < job.getEarliestStart()){
      if(job instanceof CompoundJob)
        ((CompoundJob) job).sortAllJob(job.getEarliestStart());
      schedule.addToBack(new ScheduleSlot ( job, job.getEarliestStart()));
      return true;
    }
    
    // If we cannot finish the job before the ddl then return false
    if(possiblyEarliestStartTime + duration  >= job.getDeadline())
      return false;
    
    if(job.getDeadline() - job.getEarliestStart() == duration)
      possiblyEarliestStartTime -=1;
    
    // Add the job after the previous job is finished
    if(job instanceof CompoundJob)
      ((CompoundJob) job).sortAllJob(possiblyEarliestStartTime);
    schedule.addToBack(new ScheduleSlot ( job, possiblyEarliestStartTime));
    return true;
  }
}
  