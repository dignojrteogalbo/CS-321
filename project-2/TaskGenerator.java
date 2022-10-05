import java.util.Random;

public class TaskGenerator implements TaskGeneratorInterface {
    private double probability;
    private long randomSeed;
    private int energyStorage;
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
    public void decrementEnergyStorage(Task.TaskType taskType) {
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
    public Task getNewTask(int hourCreated, Task.TaskType taskType, String taskDescription) {
        int priority = 0;
        int waitingTime = 0;

        return new Task(priority, taskType, waitingTime, hourCreated, taskDescription);
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
        return String.format("Energy: %s", energyStorage);
    }
}
