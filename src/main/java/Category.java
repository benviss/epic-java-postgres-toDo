import java.util.List;
import java.util.ArrayList;

public class Category {
  private String mName;
  private static List<Category> instances = new ArrayList<Category>();
  private int mID;
  private List<Task> mTasks;

  public Category(String name) {
    mName = name;
    instances.add(this);
    mID = instances.size();
    mTasks = new ArrayList<Task>();
  }

  public List<Task> getTasks() {
    return mTasks;
  }

  public String getName() {
    return mName;
  }
}
