/**
 * A task object that contains a priority, task type, waiting time, hour
 * created, and description. Tasks can be compared to other tasks based on their
 * priority first hour created, with earlier created tasks having a greater
 * value.
 * 
 * @author Digno JR Teogalbo
 * @version CS321 Fall 2022
 */
public class Task implements TaskInterface, Comparable<Task> {
    private int priority;
    private Task.TaskType taskType;
    private int waitingTime;
    private int hourCreated;
    private String description;

    /**
     * Constructor for a task object that takes a priority, task type, waiting time, hour created, and description.
     * 
     * @param priority    integer value where higher priority indicates a higher value
     * @param taskType    enum indicating which of the six task types it is
     * @param waitingTime time task has been waiting
     * @param hourCreated hour the task was created
     * @param description description of the task
     */
    public Task(int priority, Task.TaskType taskType, int waitingTime, int hourCreated, String description) {
        this.taskType    = taskType;
        this.priority    = priority;
        this.waitingTime = waitingTime;
        this.hourCreated = hourCreated;
        this.description = description;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Task.TaskType getTaskType() {
        return taskType;
    }

    @Override
    public int getWaitingTime() {
        return waitingTime;
    }

    @Override
    public void incrementWaitingTime() {
        waitingTime += 1;
    }

    @Override
    public void resetWaitingTime() {
        waitingTime = 0;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the hour the task was created.
     * 
     * @return int hour the task was created
     */
    public int getHourCreated() {
        return hourCreated;
    }

    /**
     * Returns the task description.
     * 
     * @return String task description
     */
    public String getTaskDescription() {
        return description;
    }

    @Override
    public int compareTo(Task object) {
        if (priority != object.getPriority()) {
            return Integer.compare(priority, object.getPriority());
        }
        
        if (hourCreated == object.getHourCreated()) {
            return 0;
        } else if (hourCreated < object.getHourCreated()) {
            return 1;
        } else {
            return -1;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%s %s at Hour: %d:00", taskType.name(), description, hourCreated, priority);
    }
}
