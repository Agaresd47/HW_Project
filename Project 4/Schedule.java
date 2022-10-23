/** components of a schedule  type
 *
 * @author Bruce Dong
 */
public class Schedule extends DoubleLinkedList<ScheduleSlot>{
  
  /**
   * return every information of the Schedule
   * @return every information of the Schedule
   */
  public String toString(){
    String s = new String();
    s += "The total profit is: " + getTotalProfit() +"\n";
    // record each job's information
    for(ScheduleSlot eachSlot : this)
      s += eachSlot.toString() + "\n";
    return s;
  }
  
  /**
   * return total profit
   * @return total profit
   */
  public int getTotalProfit(){
    int totalProfit =0;
    // accumulate total profit
    for(ScheduleSlot eachSlot : this)
      totalProfit += eachSlot.getJob().getProfit();
    return totalProfit;
  }
  
}
