import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {
  private Category firstCategory;
  private Category secondCategory;
  private Task testTask;

  @Before
  public void initialize() {
    firstCategory = new Category("Inside");
    secondCategory = new Category("Outside");
    testTask = new Task("Make bed");
  }

  @After
  public void tearDown() {
    Category.clear();
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
  public void all_checksIfCategoryListContainsInstance_true() {
    assertTrue(Category.all().contains(firstCategory));
    assertTrue(Category.all().contains(secondCategory));
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

  @Test
  public void findCategory_returnsCategoryWithSameID_secondCategory() {
    assertEquals(Category.find(secondCategory.getID()), secondCategory);
  }

  @Test
  public void find_returnsNullWhenNoTaskFound_null() {
    assertTrue(Category.find(999) == null);
  }
}
