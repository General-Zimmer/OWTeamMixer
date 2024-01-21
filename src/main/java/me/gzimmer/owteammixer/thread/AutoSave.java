package me.gzimmer.owteammixer.thread;

public class AutoSave extends Thread {

    private final int sleepTime;
    private boolean shouldStop = false;

    /**
     * Constructor for AutoSave
     * <p>
     *     Throws a IllegalArgumentException if sleepTime is less than 0
     *     <p>
     *         To shut down this thread, call Interrupt()
     * @param sleepTime The time between each autosave in minutes
     */
    public AutoSave(int sleepTime) {
        this.sleepTime = sleepTime*1000*60;
    }

    @Override
    public synchronized void run() {
        while (!shouldStop) {
            try {
                wait(sleepTime);
                // todo ADD SAVE MECHANIC SOMEWHERE!!!
                System.out.println("AutoSave");
            } catch (InterruptedException e) {
                System.out.println("Auto save stopped");
                shouldStop = true;
            }
        }
    }
}
