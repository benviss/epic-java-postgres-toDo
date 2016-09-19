import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;

public class TaskTest {
  private Task firstTask;
  private Task secondTask;

  @Before
  public void initialize() {
    firstTask = new Task("Mow the lawn");
    firstTask.save();
    secondTask = new Task("Buy groceries");
    secondTask.save();
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String sql ="DELETE FROM tasks *;";
      con.createQuery(sql).executeUpdate();
    }
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
    assertTrue(Task.all().get(0).equals(firstTask));
    assertTrue(Task.all().get(1).equals(secondTask));
  }

  @Test
  public void getId_tasksInstantiateWithAnID_1() {
    assertTrue(firstTask.getID() > 0);
  }

  @Test
  public void find_returnsTaskWithSameId_secondTask() {
    assertEquals(Task.find(secondTask.getID()), secondTask);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAreTheSame() {
    Task thirdTask = new Task("Mow the lawn");
    Task fourthTask = new Task("Mow the lawn");
    assertTrue(fourthTask.equals(thirdTask));
  }

  @Test
  public void save_returnsTrueIfDescriptionsAreTheSame() {
    assertTrue(Task.all().get(0).equals(firstTask));
  }

  @Test
  public void save_assignsIdToObject() {
    Task savedTask = Task.all().get(0);
    assertEquals(firstTask.getID(), savedTask.getID());
  }

}
