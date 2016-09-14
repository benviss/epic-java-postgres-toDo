import org.junit.*;
import static org.junit.Assert.*;

public class CategoryTest {
  private Category firstCategory;

  @Before
  public void initialize() {
    //Category.clear();
    firstCategory = new Category("Inside");
  }

  @Test
  public void Category_instantiatesCorrectly_true() {
    assertTrue(firstCategory instanceof Category);
  }

  @Test
  public void getName_returnsCategoryName_String() {
    assertEquals("Inside", firstCategory.getName());
  }
}
