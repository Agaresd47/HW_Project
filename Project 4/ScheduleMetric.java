/** the interface of seclude metric
 *
 * @author Bruce Dong
 */
public interface ScheduleMetric{
  /**
   * Returns whether we can add the job or not 
   * @return true if we can add it 
   */
  public boolean scheduleJob(Schedule schedule, Job job);
}