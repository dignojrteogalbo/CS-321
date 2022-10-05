import java.util.Random;

public class TaskGenerator implements TaskGeneratorInterface {
    private double probability;
    private long randomSeed;
    private int currentEnergyStorage;
    private Random random;

    private static final int DEFAULT_ENERGY_STORAGE = 200;

    public TaskGenerator(double probability) {
        this.probability = probability;
        random = new Random();
    }

    public TaskGenerator(double probability, long randomSeed) {
        this.probability = probability;
        this.randomSeed = randomSeed;
        random = new Random(randomSeed);
    }

    @Override
    public void decrementEnergyStorage(TaskInterface.TaskType taskType) {
        currentEnergyStorage -= taskType.getEnergyPerHour();
    }

    @Override
    public boolean generateTask() {
        return random.nextBoolean();
    }

    @Override
    public int getCurrentEnergyStorage() {
        return currentEnergyStorage;
    }

    @Override
    public Task getNewTask(int hourCreated, Task.TaskType taskType, String taskDescription) {
        int priority = 0;
        int waitingTime = 0;

        return new Task(priority, taskType, waitingTime, hourCreated, taskDescription);
    }

    @Override
    public int getUnlucky(Task task, double unluckyProbability) {
        if (unluckyProbability <= task.getTaskType().getPassingOutProbability()) {
            if (unluckyProbability <= task.getTaskType().getDyingProbabilityProbability()) {
                currentEnergyStorage *= 3 / 4;
                task.setPriority(0);
                return 2;
            } else {
                currentEnergyStorage /= 2;
                return 1;
            }
        } else {
            return 0;
        }
    }

    @Override
    public void resetCurrentEnergyStorage() {
        currentEnergyStorage = DEFAULT_ENERGY_STORAGE;
        
    }

    @Override
    public void setCurrentEnergyStorage(int newEnergyNum) {
        currentEnergyStorage = newEnergyNum;
    }

    /**
     * Create a String containing the Task's information.
     *
     * @param task - the Task
     * @param taskType - the Task's type
     */
    @Override
    public String toString(Task task, Task.TaskType taskType) {
            if(taskType == Task.TaskType.MINING) {
                return "     Mining " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
            }
            if(taskType == Task.TaskType.FISHING) {
                return "     Fishing " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
            }
            if(taskType == Task.TaskType.FARM_MAINTENANCE) {
                return "     Farm Maintenance " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
            }
            if(taskType == Task.TaskType.FORAGING) {
                return "     Foraging " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")" ;
            }
            if(taskType == Task.TaskType.FEEDING) {
                return "     Feeding " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
            }
            if(taskType == Task.TaskType.SOCIALIZING) {
                return "     Socializing " + task.getTaskDescription() + " at " + currentEnergyStorage + " energy points (Priority:" + task.getPriority() +")";
            }
            else { return "nothing to see here..."; }
    }
}
