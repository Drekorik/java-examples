package org.fireplume.patterns.structural.Facade;

/**
 * Created by cloudjumper on 8/15/16.
 */
public class FacadeExample {
    public static void main(String... args) {
        Computer computer = new Computer();
        computer.startComputer();
    }
}

class CPU {
    public void freeze() {
    }

    public void jump(long position) {
    }

    public void execute() {
    }
}

class Memory {
    public void load(long position, byte[] data) {
    }
}

class HDD {
    public byte[] read(long lba, int size) {
        return null;
    }
}

class Computer {
    private CPU cpu;
    private Memory memory;
    private HDD hardDrive;

    private long BOOT_ADDRESS = 123;
    private long BOOT_SECTOR = 345;
    private int SECTOR_SIZE = 64;

    public Computer() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HDD();
    }

    public void startComputer() {
        cpu.freeze();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }
}