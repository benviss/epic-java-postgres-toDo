import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {
  private Category firstCategory;
  private Task testTask;
  @Before
  public void initialize() {
    Category.clear();
    firstCategory = new Category("Inside");
    testTask = new Task("Make bed");
  }

  @Test
  public void Category_instantiatesCorrectly_true() {
    assertTrue(firstCategory instanceof Category);
  }

  @Test
  public void getName_returnsCategoryName_String() {
    assertEquals("Inside", firstCategory.getName());
  }

  @Test
  public void all_checksIfCategoryListContainsInstance_true() {
    assertTrue(Category.all().contains(firstCategory));
  }

  @Test
  public void getID_returnsIDNumber_int() {
    assertEquals(1, firstCategory.getID());
  }

  @Test
  public void clear_clearsCategoryList_0() {
    Category.clear();
    assertEquals(Category.all().size(), 0);
  }

  @Test
  public void addTask_addsTasksToCategoryList_true() {
    firstCategory.addTask(testTask);
    assertTrue(firstCategory.getTasks().contains(testTask));
  }
}
