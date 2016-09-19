import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.sql2o.*;

public class CategoryTest {
  private Category firstCategory;
  private Category secondCategory;
  private Task testTask;
  private Task secondTask;

  @Before
  public void initialize() {
    firstCategory = new Category("Inside");
    firstCategory.save();
    secondCategory = new Category("Outside");
    secondCategory.save();
    testTask = new Task("Make bed", 1);
    testTask.save();
    secondTask = new Task("Do the dishes", 1);
    secondTask.save();
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do_test", null, null);
  }

  @After
  public void tearDown() {
    try(Connection con = DB.sql2o.open()) {
      String deleteTasksQuery = "DELETE FROM tasks *;";
      String deleteCategoriesQuery = "DELETE FROM categories *;";
      con.createQuery(deleteTasksQuery).executeUpdate();
      con.createQuery(deleteCategoriesQuery).executeUpdate();
    }
  }

  @Test
  public void Category_instantiatesCorrectly_true() {
    assertTrue(firstCategory instanceof Category);
  }

  @Test
  public void getName_categoryInstantiatesWithName_Inside() {
    assertEquals("Inside", firstCategory.getName());
  }

  @Test
  public void all_returnsAllInstancesOfCategory_true() {
    assertTrue(Category.all().get(0).equals(firstCategory));
    assertTrue(Category.all().get(1).equals(secondCategory));
   }

  @Test
  public void getId_returnsIdNumber_int() {
    assertTrue(firstCategory.getId() > 0);
  }

  @Test
  public void findCategory_returnsCategoryWithSameId_secondCategory() {
    assertEquals(Category.find(secondCategory.getId()), secondCategory);
  }

  @Test
  public void find_returnsNullWhenNoTaskFound_null() {
    assertTrue(Category.find(999) == null);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Category thirdCategory = new Category("Household chores");
    Category fourthCategory = new Category("Household chores");
    assertTrue(thirdCategory.equals(fourthCategory));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    assertTrue(Category.all().get(0).equals(firstCategory));
  }

  @Test
  public void save_assignsIdToObject() {
    Category savedCategory = Category.all().get(0);
    assertEquals(firstCategory.getId(), savedCategory.getId());
  }

  // @Test
  // public void save_savesCategoryIdIntoDB_true() {
  //   Task savedTask = Task.find(testTask.getId());
  //   assertEquals(savedTask.getCategoryId(), firstCategory.getId());
  // }

  @Test
  public void getTasks_retrievesAllTasksFromDatabase_tasksList() {
    Task fifthTask = new Task("Steal Groceries", firstCategory.getId());
    fifthTask.save();
    Task sixthTask = new Task("Cook Stolen Goods", firstCategory.getId());
    sixthTask.save();
    Task[] tasks = new Task[] { fifthTask, sixthTask };
    assertTrue(firstCategory.getTasks().containsAll(Arrays.asList(tasks)));
  }
}
