import java.util.Random;

/**
 * Task Generator that keeps track of the current energy storage and creates
 * tasks from a given probability.
 * 
 * @author Digno JR Teogalbo
 * @version CS321 Fall 2022
 */
public class TaskGenerator implements TaskGeneratorInterface {
    private double probability;
    private int currentEnergyStorage = DEFAULT_ENERGY;
    private Random random = new Random();

    /**
     * The task generator will create tasks based on the given probability.
     * 
     * @param probability chance for tasks to be created
     */
    public TaskGenerator(double probability) {
        this.probability = probability;
    }

    /**
     * The task generator will generate tasks based on the given probability with
     * the random seed provided.
     * 
     * @param probability chance for tasks to be created
     * @param randomSeed  seed for the randomizer
     */
    public TaskGenerator(double probability, long randomSeed) {
        this.probability = probability;
        random.setSeed(randomSeed);
    }

    @Override
    public void decrementEnergyStorage(Task.TaskType taskType) {
        currentEnergyStorage -= taskType.getEnergyPerHour();
    }

    @Override
    public boolean generateTask() {
        return random.nextDouble() < probability;
    }

    @Override
    public int getCurrentEnergyStorage() {
        return currentEnergyStorage;
    }

    @Override
    public Task getNewTask(int hourCreated, TaskInterface.TaskType taskType, String taskDescription) {
        return new Task(0, taskType, 0, hourCreated, taskDescription);
    }

    @Override
    public int getUnlucky(Task task, double unluckyProbability) {
        if (unluckyProbability <= task.getTaskType().getPassingOutProbability()) {
            if (unluckyProbability <= task.getTaskType().getDyingProbabilityProbability() && task.getTaskType() == Task.TaskType.MINING) {
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
        currentEnergyStorage = DEFAULT_ENERGY;
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
