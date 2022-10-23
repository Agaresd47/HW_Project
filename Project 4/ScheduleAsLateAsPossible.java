/** interface that contains the method of schedule a job as late as possible
 *
 * @author Bruce Dong
 */
public class ScheduleAsLateAsPossible implements ScheduleMetric{
  
  /**
   * see whether we can add new job (start as late as possible) to the seclude
   * If we can then, add it
   * @param schedule the schedule that we try to add new job
   * @param job the job that we want to add
   * @return true if we can add the job
   */
  public  boolean scheduleJob (Schedule schedule, Job job){
    // automatically add if nothing is in the schedule
    if(schedule.getFront() == null && schedule.getBack() == null){
      if(job instanceof CompoundJob)
        ((CompoundJob) job).sortAllJob(job.getDeadline() - job.getDuration());
      schedule.addToBack(new ScheduleSlot ( job, job.getDeadline() - job.getDuration()));
      return true;
    }

    // record the duration of the  job
    int duration = job.getDuration();
    // record the open time of this job
    int startTime = job.getEarliestStart();
    // record the last job's start time
    int cStartTime;

    if(schedule.getFront() == null)
      cStartTime = schedule.getBack().getElement().getStartTime();
    else
      cStartTime = schedule.getFront().getElement().getStartTime();

    // calculate the possible start time of the current job
    int possibleLatestStartTime = cStartTime - duration;
    
     if(possibleLatestStartTime < startTime)
      return false;
    
    if( possibleLatestStartTime + job.getDuration() >= job.getDeadline())
      possibleLatestStartTime = job.getDeadline() - job.getDuration();
    
    if(job.getDeadline() - job.getEarliestStart() +1 == duration)
      possibleLatestStartTime -=1;

    if(job instanceof CompoundJob)
      ((CompoundJob) job).sortAllJob(possibleLatestStartTime);
    schedule.addToFront(new ScheduleSlot ( job, possibleLatestStartTime));
    return true;
  }
}
  