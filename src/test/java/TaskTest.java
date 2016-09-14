import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class TaskTest {
  private Task myTask;

  @Before
  public void initialize() {
    myTask = new Task("Mow the lawn");
  }

  @Test
   public void Task_instantiatesCorrectly_true() {
    assertEquals(true, myTask instanceof Task);
  }

  @Test
  public void Task_instantiatesWithDescription_String() {
    assertEquals("Mow the lawn", myTask.getDescription());
  }

  @Test
  public void isCompleted_isFalseAfterInstantiation_false() {
    assertFalse(myTask.isCompleted());
  }

  @Test
  public void getCreatedAt_instantiateWithCurrentTime_today() {
    assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
  }

}
