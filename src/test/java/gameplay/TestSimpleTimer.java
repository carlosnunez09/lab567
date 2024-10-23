package gameplay;

import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class TestSimpleTimer {


  /**
   * Init test
   */
  @Test
  public void testSimpleTimerInitObs() {
    SimpleTimer t = new SimpleTimer();

    assertEquals(0, t.getNumObservers());
  }

  @Test
  public void testSimpleTimerInitRound() {
    SimpleTimer t = new SimpleTimer();

    assertEquals(0, t.getRound());
  }

  /**
   * Make sure time updates.
   */
  @Test
  public void testSimpleTimerUpdateTime() {
    SimpleTimer t = new SimpleTimer();

    assertEquals(0, t.getRound());

    t.timeChanged();

    assertEquals(1, t.getRound());
  }


  /**
   * Add observer to simpleTimer
   */
  @Test
  public void testSimpleTimerAddObs() {
    SimpleTimer t = new SimpleTimer();
    MockSimpleTimerObserver mto = new MockSimpleTimerObserver();

    t.addTimeObserver(mto);

    assertEquals(1,t.getNumObservers());
  }

  /**
   * Observer gets updates
   */
  @Test
  public void testSimpleTimerUpdatesObs() {
    SimpleTimer t = new SimpleTimer();
    MockSimpleTimerObserver mto = new MockSimpleTimerObserver();
    MockSimpleTimerObserver mto2 = new MockSimpleTimerObserver();

    t.addTimeObserver(mto);
    t.addTimeObserver(mto2);

    t.timeChanged();

    assertEquals(1, mto.myTime);
    assertEquals(1, mto2.myTime);
  }



  /**
   * Remove observer from simpleTimer
   */
  @Test
  public void testSimpleTimerRemoveObs() {
    SimpleTimer t = new SimpleTimer();
    MockSimpleTimerObserver mto = new MockSimpleTimerObserver();

    t.addTimeObserver(mto);

    t.removeTimeObserver(mto);

    assertEquals(0,t.getNumObservers());
  }

  /**
   * Time changed inrementation
   */
  @Test
  public void testSimpleTimerTimeChange() {
    SimpleTimer t = new SimpleTimer();
    MockSimpleTimerObserver mto = new MockSimpleTimerObserver();

    t.timeChanged();
    assertEquals(1, t.getRound());

    t.addTimeObserver(mto);

    t.timeChanged();

    assertEquals(2,t.getRound());
    assertEquals(2, mto.myTime);
  }


  /**
   * This tests that SimpleTimer will update time once
   * every second.
   */
  @Test
  public void testSimpleTimerAsThread() throws
          InterruptedException{
    SimpleTimer st = new SimpleTimer(1000);
    st.start();
    Thread.sleep(250); // So we are 1/4th a second different
    for (int x=0;x<5;x++){
      assertEquals(x,st.getRound()); //assumes round starts at 0
      Thread.sleep(1000); // wait for the next time change
    }
  }

}
