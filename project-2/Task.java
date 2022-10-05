public class Task implements TaskInterface, Comparable<Task> {
    private int priority;
    private Task.TaskType taskType;
    private int waitingTime;
    private int hourCreated;
    private String description;

    /**
     * 
     * @param priority
     * @param taskType
     * @param waitingTime
     * @param hourCreated
     * @param description
     */
    public Task(int priority, Task.TaskType taskType, int waitingTime, int hourCreated, String description) {
        this.taskType = taskType;
        this.priority = priority;
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

    public int getHourCreated() {
        return hourCreated;
    }

    public String getTaskDescription() {
        return description;
    }

    @Override
    public int compareTo(Task object) {
        if (priority != object.getPriority()) {
            return Integer.compare(priority, object.getPriority());
        } else {
            return Integer.compare(hourCreated, object.getHourCreated());
        }
    }
    
    @Override
    public String toString() {
        return String.format("Type: %s %s at Hour: %d:00", taskType.name(), description, hourCreated, priority);
    }
}
