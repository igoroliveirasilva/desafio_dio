import java.util.ArrayList;
import java.util.List;

//Projeto - Gerenciamento de tarefas


interface Observer {
    void update(Task task);
}

class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Task task) {
        for (Observer observer : observers) {
            observer.update(task);
        }
    }
}

class Task {
    private String title;
    private String description;
    private boolean complete;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.complete = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}

class TaskList extends Observable {
    List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers(task);
    }

    public void completeTask(Task task) {
        task.setComplete(true);
        notifyObservers(task);
    }
}

class TaskListView implements Observer {
    private TaskList taskList;

    public TaskListView(TaskList taskList) {
        this.taskList = taskList;
        taskList.addObserver(this);
    }

    @Override
    public void update(Task task) {
        System.out.println("Task " + task.getTitle() + " updated!");
    }
}

public class Desafio {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        TaskListView taskListView = new TaskListView(taskList);

        taskList.addTask(new Task("Task 1", "Do something"));
        taskList.addTask(new Task("Task 2", "Do something else"));
        taskList.completeTask(taskList.tasks.get(0));
    }
}