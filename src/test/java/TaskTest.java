import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;

public class TaskTest {
  private Task firstTask;
  private Task secondTask;

  @Before
  public void initialize() {
    firstTask = new Task("Mow the lawn");
    secondTask = new Task("Fly to Mars");
  }

  @After
  public void tearDown() {
    Task.clear();
  }

  @Test
   public void Task_instantiatesCorrectly_true() {
    assertEquals(true, firstTask instanceof Task);
  }

  @Test
  public void Task_instantiatesWithDescription_String() {
    assertEquals("Mow the lawn", firstTask.getDescription());
  }

  @Test
  public void isCompleted_isFalseAfterInstantiation_false() {
    assertFalse(firstTask.isCompleted());
  }

  @Test
  public void getCreatedAt_instantiateWithCurrentTime_today() {
    assertEquals(LocalDateTime.now().getDayOfWeek(), firstTask.getCreatedAt().getDayOfWeek());
  }

  @Test
  public void all_returnsAllInstancesOfTask_true() {
    assertTrue(Task.all().contains(firstTask));
    assertTrue(Task.all().contains(secondTask));
  }

  @Test
  public void clear_emptiesAllTasksFromArrayList_0() {
    Task.clear();
    assertEquals(Task.all().size(), 0);
  }

  @Test
  public void getId_tasksInstantiateWithAnID_1() {
    assertEquals(1, firstTask.getID());
  }

  @Test
  public void find_returnsTaskWithSameId_secondTask() {
    assertEquals(Task.find(secondTask.getID()), secondTask);
  }
}
