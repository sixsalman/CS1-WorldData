/**
 *  Author: Salman
 *
 *  This class serves as a blueprint for an object which stores data related to a country
 */
public class Entry {

    // FIELDS
    private String code; //holds country's code
    private String name; //holds country's name
    private String cont; //holds country's continent
    private String reg; //holds country's region
    private int size; //holds country's land size
    private int pop; //holds country's population
    private double lifeExp; //holds country's average life expectancy

    // CONSTRUCTORS

    /**
     *This constructor gets and assigns values to all of the objects' fields
     * @param tokens receive an array of strings which contain information to be stored in the object's fields
     */
    public Entry(String[] tokens) {
        code = tokens[0];
        name = tokens[2];
        cont = tokens[3];
        reg = tokens[4];
        size = (int)((Integer.parseInt(tokens[5])) * 0.3861);
        pop = Integer.parseInt(tokens[6]);
        lifeExp = Double.parseDouble(tokens[7]);
    }

    // METHODS

    /**
     * gets country's code
     * @return country's code
     */
    public String getCode() {
        return code;
    }

    /**
     * sets country's code
     * @param code receives country's code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * gets country's name
     * @return country's name
     */
    public String getName() {
        return name;
    }

    /**
     * sets country's name
     * @param name receives country's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets country's continent
     * @return country's continent
     */
    public String getCont() {
        return cont;
    }

    /**
     * sets country's continent
     * @param cont receives country's continent
     */
    public void setCont(String cont) {
        this.cont = cont;
    }

    /**
     * gets country's region
     * @return country's region
     */
    public String getReg() {
        return reg;
    }

    /**
     * sets country's region
     * @param reg receives country's region
     */
    public void setReg(String reg) {
        this.reg = reg;
    }

    /**
     * gets country's land size
     * @return country's land size
     */
    public int getSize() {
        return size;
    }

    /**
     * sets country's land size
     * @param size receives country's land size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * gets country's population
     * @return country's population
     */
    public int getPop() {
        return pop;
    }

    /**
     * sets country's population
     * @param pop receives country's population
     */
    public void setPop(int pop) {
        this.pop = pop;
    }

    /**
     * gets average life expectancy
     * @return country's average life expectancy
     */
    public double getLifeExp() {
        return lifeExp;
    }

    /**
     * sets country's average life expectancy
     * @param lifeExp receives country's average life expectancy
     */
    public void setLifeExp(double lifeExp) {
        this.lifeExp = lifeExp;
    }

    /**
     * gets the country's data, with id, in a printable format
     * @param i receives country's id
     * @return the object's (country's) data in displayable form
     */
    public String toString(int i) {
        return String.format("%3d %3s - %-15s (%-13s) %,10d sq.miles – pop %,14d - lifeExp\t%.1f years", i, code,
                (name.length() > 15 ? name.substring(0, 15) : name), cont, size, pop, lifeExp);
    }

    /**
     * gets the country's data, without id, in a printable format
     * @return the object's (country's) data in displayable form
     */
    @Override
    public String toString() {
        return String.format("%3s - %-15s (%-13s) %,10d sq.miles – pop %,14d - lifeExp\t%.1f years", code,
                (name.length() > 15 ? name.substring(0, 15) : name), cont, size, pop, lifeExp);
    }
}
