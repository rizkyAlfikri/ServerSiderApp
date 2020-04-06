package server;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class ThreadReadFood implements Runnable {
    private boolean writeStatusFood = true;
    public boolean isWriteStatusFood() {
        return writeStatusFood;
    }

    public void setWriteStatusFood(boolean writeStatusFood) {
        this.writeStatusFood = writeStatusFood;
    }

    @Override
    public void run() {
        File file = new File("C:\\Users\\IPComp\\IdeaProjects\\ServerSiderApp\\src\\client\\Food.txt");
        FileLock lockFood = null;

        try {
            FileChannel fileChannel = new RandomAccessFile(file, "rw").getChannel();
            lockFood = fileChannel.lock();
            lockFood = fileChannel.tryLock();
            System.out.println("Read  Food is available");

            this.writeStatusFood = true;

        } catch (OverlappingFileLockException | IOException e) {
            System.out.println("Read  Food is not available");
            this.writeStatusFood = false;
        } finally {
            if (lockFood != null) {
                try {
                    lockFood.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
