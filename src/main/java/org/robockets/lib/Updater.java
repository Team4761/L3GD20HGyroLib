package org.robockets.lib;

/**
 * Used to Update/accumulate gyro values
 */
class Updater implements Runnable{

    private int deltaX = 0;
    private int deltaY = 0;
    private int deltaZ = 0;

    private byte[] hOrder = new byte[1];
    private byte[] lOrder = new byte[1];

    @Override
    public void run() {

        // TODO: Mess with the values to make it have negative numbers again

        // This is for X
        AdaGyro.gyro.read(0x28, 1, lOrder);
        AdaGyro.gyro.read(0x29, 1, hOrder);

        deltaX = AdaGyro.getDegrees(hOrder[0], lOrder[0]);

        // Y
        AdaGyro.gyro.read(0x2A, 1, lOrder);
        AdaGyro.gyro.read(0x2B, 1, hOrder);

        deltaY = AdaGyro.getDegrees(hOrder[0], lOrder[0]);

        // Z
        AdaGyro.gyro.read(0x2C, 1, lOrder);
        AdaGyro.gyro.read(0x2D, 1, hOrder);

        deltaZ = AdaGyro.getDegrees(hOrder[0], lOrder[0]);


        AdaGyro.accumX += deltaX;
        AdaGyro.accumY += deltaY;
        AdaGyro.accumZ += deltaZ;

    }
}
