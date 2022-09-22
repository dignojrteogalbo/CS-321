public class Task implements TaskInterface, Comparable<Task> {
    private int priority;
    private TaskInterface.TaskType taskType;
    private int waitingTime;
    private int hourCreated;
    private String description;

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
    public TaskInterface.TaskType getTaskType() {
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
        String taskDetails = "================== TASK DETAILS ==================\n";
        String priorityString = String.format("PRIORITY: %d\n", getPriority());
        String waitingTimeString = String.format("WAITING TIME: %d\n", getWaitingTime());
        String taskTypeString = String.format("TASK TYPE: %s\n", getTaskType().name());
        String taskInfo = "================== TASK INFO ==================\n";
        String moneyPerHour = String.format("MONEY PER HOUR: %d\n", taskType.getMoneyPerHour());
        String energyPerHour = String.format("ENERGY PER HOUR: %d\n", taskType.getEnergyPerHour());
        String passingOutProbability = String.format("PASSING OUT PROBABILITY: %.2f\n",
                taskType.getPassingOutProbability());
        String dyingProbability = String.format("DYING PROBABILITY: %.2f\n", taskType.getPassingOutProbability());

        StringBuilder output = new StringBuilder()
            .append(taskDetails)
            .append(priorityString)
            .append(waitingTimeString)
            .append(taskTypeString)
            .append(taskInfo)
            .append(moneyPerHour)
            .append(energyPerHour)
            .append(passingOutProbability)
            .append(dyingProbability);

        return output.toString();
    }
}
