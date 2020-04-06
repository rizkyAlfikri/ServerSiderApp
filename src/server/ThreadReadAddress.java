package server;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class ThreadReadAddress implements Runnable {
    private boolean writeStatusAddress = true;

    public boolean isWriteStatusAddress() {
        return writeStatusAddress;
    }

    public void setWriteStatusAddress(boolean writeStatusAddress) {
        this.writeStatusAddress = writeStatusAddress;
    }


    @Override
    public void run() {
        File file = new File("C:\\Users\\IPComp\\IdeaProjects\\ServerSiderApp\\src\\client\\Address.txt");
        FileLock lockAddress = null;

        try {
            FileChannel channel = new RandomAccessFile(file, "rw").getChannel();
            lockAddress = channel.lock();
            lockAddress = channel.tryLock();
            System.out.println("Read  Address is available");

            this.writeStatusAddress = true;
        } catch (OverlappingFileLockException | IOException e) {
            System.out.println("Read  Address is not available");
            this.writeStatusAddress = false;
        } finally {
            if (lockAddress != null) {
                try {
                    lockAddress.release();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
