package org.robockets.lib;


import edu.wpi.first.wpilibj.I2C;

/**
 * See https://cdn-shop.adafruit.com/datasheets/L3GD20H.pdf for more info
 * @author Jake Backer
 */
public class AdaGyro {

    private I2C gyro;

    /**
     * Create the I2C object based on the address provided
     * @param address The device adress in hex either 0x6A or 0x6B
     */
    public AdaGyro(int address) {
        gyro = new I2C(I2C.Port.kOnboard, address);
    }

    /**
     * Setup the gyro with the recommended settings
     * - Enable normal power mode
     * - Enable all axes
     * - Enable FIFO
     */
    public void autoSetup() {
        gyro.write(0x20, 0b00001111); // Enables normal power mode and axes
        gyro.write(0x24, 0b01000000); // Enable FIFO
    }

    /**
     * Set CTRL values
     * @param ctrlNum The ctrl register to access
     * @param values The values in binary
     * @throws InvalidInputException Thrown when an invalid ctrl number was entered
     */
    public void setCtrl(int ctrlNum, int values) throws InvalidInputException{
        switch (ctrlNum) {
            case 1:
                gyro.write(0x20, values);
                break;
            case 2:
                gyro.write(0x21, values);
                break;
            case 3:
                gyro.write(0x22, values);
                break;
            case 4:
                gyro.write(0x23, values);
                break;
            case 5:
                gyro.write(0x24, values);
                break;
            default:
                throw new InvalidInputException("That ctrl number does not exist!");
        }
    }

    /**
     * Read CTRL values
     * @param ctrlNum The ctrl register to access
     * @throws InvalidInputException Thrown when an invalid ctrl number was entered
     */
    public byte[] readCtrl(int ctrlNum) throws InvalidInputException{

        byte[] overflow = new byte[1];

        switch (ctrlNum) {
            case 1:
                gyro.read(0x20, 1, overflow);
                break;
            case 2:
                gyro.read(0x21, 1, overflow);
                break;
            case 3:
                gyro.read(0x22, 1, overflow);
                break;
            case 4:
                gyro.read(0x23, 1, overflow);
                break;
            case 5:
                gyro.read(0x24, 1, overflow);
                break;
            default:
                throw new InvalidInputException("That ctrl number does not exist!");
        }

        return overflow;
    }


    /**
     * Read temperature values
     * @return The temperature read from the gyro
     */
    public byte[] readTemp() {
        byte[] overflow = new byte[1];

        gyro.read(0x26, 1, overflow);

        return overflow;
    }

    /**
     * Get the X or yaw gyro value
     * @return The X value
     */
    public double readX() {
        byte[] overflow = new byte[1];
        double x = 0;

        // Stuff goes here

        return x;
    }

    /**
     * Get the Y or pitch gyro value
     * @return The Y value
     */
    public double readY() {
        byte[] overflow = new byte[1];
        double y = 0;

        // Stuff goes here

        return y;
    }

    /**
     * Get the Z or roll value
     * @return The Z Value
     */
    public double readZ() {
        byte[] overflow = new byte[1];
        double z = 0;

        // Stuff goes here

        return z;
    }

    /**
     * Write values to a custom address
     * @param address The register address in hex
     * @param values The values to write in binary
     */
    public void customWrite(int address, int values) throws InvalidInputException{

        for (int i = 0x00; i<0x0E; i++) { // I don't know how well this works
            if (address == i) {
                throw new InvalidInputException("This value can cause problems!! Aborting...");
            }
        }

        for (int i = 0x10; i<0x1F; i++) { // I don't know how well this works
            if (address == i) {
                throw new InvalidInputException("This value can cause problems!! Aborting...");
            }
        }

        gyro.write(address, values);

    }

    /**
     * Read values from a custom address
     * @param address The register address in hex
     */
    public byte[] customRead(int address) throws InvalidInputException{

        byte[] overflow = new byte[1];

        for (int i = 0x00; i<0x0E; i++) { // I don't know how well this works
            if (address == i) {
                throw new InvalidInputException("This value can cause problems!! Aborting...");
            }
        }

        for (int i = 0x10; i<0x1F; i++) { // I don't know how well this works
            if (address == i) {
                throw new InvalidInputException("This value can cause problems!! Aborting...");
            }
        }

        gyro.read(address,1, overflow);

        return overflow;
    }

}
