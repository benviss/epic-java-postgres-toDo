import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Task {
  private String description;
  private boolean completed;
  private LocalDateTime created;
  private int id;
  private int categoryId;

  public Task(String description, int categoryId) {
    this.description = description;
    completed = false;
    created = LocalDateTime.now();
    this.categoryId = categoryId;
  }

  public String getDescription() {
    return description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public static List<Task> all() {
    String sql = "SELECT id, description, categoryid FROM tasks";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Task.class);
    }
  }

  public int getId() {
    return id;
  }


  public static Task find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM tasks where id=:id";
      Task task = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Task.class);
      return task;
    }
  }

  @Override
  public boolean equals(Object otherTask) {
    if (!(otherTask instanceof Task)) {
      return false;
    } else {
      Task newTask = (Task) otherTask;
      return this.getDescription().equals(newTask.getDescription()) && this.getId() == newTask.getId() && this.getCategoryId() == newTask.getCategoryId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO tasks (description, categoryid) VALUES (:description, :categoryid)";
      this.id = (int) con.createQuery(sql, true).addParameter("description", this.description).addParameter("categoryid", this.categoryId).executeUpdate().getKey();
    }
  }

  public void update(String description) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE tasks SET description = :description Where id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

}
