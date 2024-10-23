package gameplay;

import java.util.ArrayList;


public class SimpleTimer extends Thread implements Timer {

  ArrayList<TimerObserver> obs = new ArrayList<TimerObserver>();

  public int myTime;
  private int sleepT;
  public int round;



  /**
   * Create Timer
   */

  public SimpleTimer() {
    myTime = 0;
    sleepT = 0;
    round = 0;

  }

  /**
   * Update the round
   * @param sleep adds sleep
   */

  public SimpleTimer(int sleep) {
    myTime = 0;
    round = 0;
    sleepT = sleep;
  }

  /**
   * Update the round
   * @param observer adds the obs
   */


  public void addTimeObserver(TimerObserver observer) {
    obs.add(observer);

  }

  /**
   * Update the round
   * @param observer removes the obs
   */

  public void removeTimeObserver(TimerObserver observer) {
    obs.remove(observer);
  }

  /**
   * Update the round
   *
   */
  public void timeChanged() {
    round += 1;
    for (int i = 0; i < obs.size(); i++) {
      obs.get(i).updateTime(round);
    }




  }

  /**
   * Run the Thread
   */

  public void run() {
    for (int x = 0; x < 100; x++) {
      try {
        Thread.sleep(sleepT);
        round += 1;
      } catch (InterruptedException e) {
        System.out.println("Something bad happened.");
      }
    }
  }

  /**
   * Returns the num of obs
   */

  public int getNumObservers() {
    //obs.forEach(ob->count+=1);
    return obs.size(); //forEach.obs
  }


  /**
   * Return the round
   */

  public int getRound() {

    return round;


  }
}
