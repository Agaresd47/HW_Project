import java.util.Comparator;

/** components of a job file
 *
 * @author Bruce Dong
 */
public class Job  implements Comparable<Job>{
  // This field contains the unique id of the job
  private int id;
  // This field contains the earliest start time of the job
  private int earliestStart;
  // This field contains the deadline of the job
  private int deadline;
  // This field contains the length of time to finish the job
  private int duration;
  // This field contains the payment of the job
  private int profit;
  // a field to store the last id  used
  private static int lastJobId = 0;


  /**
   * Stores basic information of a job (job id automatically add one)
   * @param earliestStart  the earliest start time of the job
   * @param deadline       deadline of the job
   * @param duration       length of time to finish the job
   * @param profit         payment of the job
   */
  public Job (int earliestStart, int deadline, int duration, int profit) {
    this(Job.lastJobId+1, earliestStart, deadline, duration, profit );
    Job.lastJobId = this.id;
  }


  /**
   * Stores basic information of a job
   * @param id             the order number of the job
   * @param earliestStart  the earliest start time of the job
   * @param deadline       deadline of the job
   * @param duration       length of time to finish the job
   * @param profit         payment of the job
   */
  public Job (int id, int earliestStart, int deadline, int duration, int profit) {
    this.id = id;
    this.earliestStart =earliestStart;
    this.deadline = deadline;
    this.profit = profit;
    this.duration = duration;
  }

  /**
   * Returns the id of the job.
   * @return the id of the job
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the earliest start time of the job.
   * @return the earliest start time of the job
   */
  public int getEarliestStart() {
    return earliestStart;
  }

  /**
   * Returns the deadline of the job.
   * @return the deadline of the job
   */
  public int getDeadline() {
    return deadline;
  }

  /**
   * Returns the profit of the job.
   * @return the profit of the job
   */
  public int getProfit() {
    return profit;
  }

  /**
   * Returns the duration of the job.
   * @return the duration of the job
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Returns all sub jobs of the compound job.
   * @return all sub jobs of the compound job.
   */
  public ScheduleSlot[] getsAllJob() {
    return null;
  }

  /**
   * Changes the id of the job
   * @param newId the new rules for the job
   */
  public void setId(int newId) {
    this.id = newId;
  }

  /**
   * Changes the earliest start time of the job
   * @param newEarliestStart the new earliest start time for the job
   */
  public void setEarliestStart(int newEarliestStart) {
    this.earliestStart = newEarliestStart;
  }

  /**
   * Changes the duration of the job
   * @param newDuration the new duration for the job
   */
  public void setDuration(int newDuration) {
    this.duration = newDuration;
  }

  /**
   * Changes the deadline of the job
   * @param newDeadline the new deadline for the job
   */
  public void setDeadline(int newDeadline) {
    this.deadline = newDeadline;
  }

  /**
   * Changes the profit of the job
   * @param newProfit the new profit for the job
   */
  public void setProfit(int newProfit) {
    this.profit = newProfit;
  }

  /**
   * Compare two job by job id
   * @param job the job that we want to compare
   * @return the result of the compare
   */
  public int compareTo(Job job) {
    return Integer.compare(this.getId(), job.getId());
  }


  
  /**
   * compare two job variable are the same
   * @param receive the Job variable that we want to compare
   * @return true if the "this" and the job received are the same
   */
  public  boolean equals(Job receive) {
    return this.deadline == receive.getDeadline() &&
            this.duration == receive.getDuration() &&
            this.profit == receive.getProfit() &&
            this.id == receive.getId() &&
            this.earliestStart == receive.getEarliestStart();
  }

  /**
   * compare two job variable are the same regardless of their id
   * @param receive the Job variable that we want to compare
   * @return true if the "this" and the job received are the same
   */
  public boolean equalsNoId(Job receive) {
    return this.deadline == receive.getDeadline() &&
            this.duration == receive.getDuration() &&
            this.profit == receive.getProfit() &&
            this.earliestStart == receive.getEarliestStart();
  }

  /**
   * return every information of the job
   * @return every information of the job
   */
  public String toString(){
    return "id: " + id + ", open time: " + earliestStart + 
      ":00, deadline: " + deadline + ":00, duration: " + duration + 
      ", profit: " +profit;
  }

  /**
   * return every information of the job if it is a compound job
   * @return every information of the job if it is a compound job
   */
  public String toCompoundString(){
    return "id: " + id + ", open time: " + earliestStart
             + ":00, duration: " + duration + ", profit: " +profit;
  }

  /**
   * Reset the last id of the job to 0
   */
  public static void resetLastId() {
    Job.lastJobId = 0;
  }

  /**
   * Return a comparator that compares two jobs by profit
   * @return a comparator for the jobs
   */
  public static profitComparator getProfitComparator() {
    return new profitComparator ();
  }

  /** A class that is a Comparator for jobs, comparing them by profit */
  private static class profitComparator implements Comparator<Job> {

    /**
     * Compares two points by profit
     * @param job1 the first job
     * @param job2 the second job
     * @return <0, =0, >0, if the first job has less, equal, or greatest profit than the second job
     */
    public int compare(Job job1, Job job2) {
      return Integer.compare(job1.getProfit(), job2.getProfit());
    }
  }

  /**
   * Return a comparator that compares two jobs by earliest start time
   * @return a comparator for the jobs
   */
  public static startComparator getStartComparator() {
    return new startComparator ();
  }

  /** A class that is a Comparator for jobs, comparing them by earliest start time */
  private static class startComparator implements Comparator<Job> {

    /**
     * Compares two points by earliest start time
     * @param job1 the first job
     * @param job2 the second job
     * @return <0, =0, >0, if the first job has less, equal, or greater earliest start time than the second job
     */
    public int compare(Job job1, Job job2) {
      return Integer.compare(job1.getEarliestStart(), job2.getEarliestStart());
    }
  }

  /**
   * Return a comparator that compares two jobs by deadline
   * @return a comparator for the jobs
   */
  public static ddlComparator getDDLComparator() {
    return new ddlComparator ();
  }

  /** A class that is a Comparator for jobs, comparing them by earliest start time */
  private static class ddlComparator implements Comparator<Job> {

    /**
     * Compares two points by earliest start time
     * @param job1 the first job
     * @param job2 the second job
     * @return <0, =0, >0, if the first job has less, equal, or greater deadline than the second job
     */
    public int compare(Job job1, Job job2) {
      return Integer.compare( job2.getDeadline(), job1.getDeadline());
    }
  }
}