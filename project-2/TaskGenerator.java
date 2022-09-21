import java.util.Random;

public class TaskGenerator implements TaskGeneratorInterface {
    private int probability;
    private int randomSeed;
    private int energyStorage;
    private Random random;

    private int final DEFAULT_ENERGY_STORAGE = 200;

    public TaskGenerator() {
        random = new Random();
    }

    public TaskGenerator(int probability, int randomSeed) {
        this.probability = probability;
        this.randomSeed = randomSeed;
        random = new Random(randomSeed);
    }

    @Override
    public void decrementEnergyStorage(TaskInterface.TaskType taskType) {
        energyStorage -= taskType.getEnergyPerHour();
    }

    @Override
    public boolean generateTask() {
        return random.nextBoolean();
    }

    @Override
    public int getCurrentEnergyStorage() {
        return energyStorage;
    }

    @Override
    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {
        int priority = 0;
        int waitingTime = 0;

        return new Task(priority, taskType, waitingTime, hourCreated, taskDescription)
    }

    @Override
    public int getUnlucky(Task task, double unluckyProbability) {
        boolean isUnlucky = random.nextDouble(1f) < unluckyProbability;

        return 0;
    }

    @Override
    public void resetCurrentEnergyStorage() {
        energyStorage = DEFAULT_ENERGY_STORAGE;
        
    }

    @Override
    public void setCurrentEnergyStorage(int newEnergyNum) {
        energyStorage = newEnergyNum;
    }

    /**
     * Create a String containing the Task's information.
     *
     * @param task     - the Task
     * @param taskType - the Task's type
     */
    @Override
    public String toString(Task task, Task.TaskType taskType) {
        if (taskType == Task.TaskType.MINING) {
            return "     Mining " + task.getTaskDescription() + " at " + currentEnergyStorage
                    + " energy points (Priority:" + task.getPriority() + ")";
        }
        if (taskType == Task.TaskType.FISHING) {
            return "     Fishing " + task.getTaskDescription() + " at " + currentEnergyStorage
                    + " energy points (Priority:" + task.getPriority() + ")";
        }
        if (taskType == Task.TaskType.FARM_MAINTENANCE) {
            return "     Farm Maintenance " + task.getTaskDescription() + " at " + currentEnergyStorage
                    + " energy points (Priority:" + task.getPriority() + ")";
        }
        if (taskType == Task.TaskType.FORAGING) {
            return "     Foraging " + task.getTaskDescription() + " at " + currentEnergyStorage
                    + " energy points (Priority:" + task.getPriority() + ")";
        }
        if (taskType == Task.TaskType.FEEDING) {
            return "     Feeding " + task.getTaskDescription() + " at " + currentEnergyStorage
                    + " energy points (Priority:" + task.getPriority() + ")";
        }
        if (taskType == Task.TaskType.SOCIALIZING) {
            return "     Socializing " + task.getTaskDescription() + " at " + currentEnergyStorage
                    + " energy points (Priority:" + task.getPriority() + ")";
        } else {
            return "nothing to see here...";
        }
    }
}
