package itu.mmad.dttn.tingle.model;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a physical thing and its location
 * It contains information and location of the item + a unique ID
 */
public class Thing extends Entity implements Serializable {

    private String mWhat;
    private String mWhere;
    private String mDescription;
    private Date mDate;

    public Thing(String what, String where) {
        super();
        mWhat = what;
        mWhere = where;
        mDate = new Date();
    }


    /**
     * Gets description of thing
     * @return description
     */
    public String getDescription() {
        return mDescription;
    }

    /**
     * Sets description of thing
     * @return description
     */
    public void setDescription(String description) {
        mDescription = description;
    }

    /**
     * Returns the date
     * @return date
     */
    public Date getDate() {
        return mDate;
    }

    /**
     * Sets the date
     * @param date
     */
    public void setDate(Date date) {
        mDate = date;
    }

    /**
     * Returns a text of the item and its location
     *
     * @return String
     */
    @Override
    public String toString() {
        return oneLine("Item: ", "\nLocation: ");
    }

    /**
     * returns the name of the item
     *
     * @return String
     */
    public String getWhat() {
        return mWhat;
    }

    /**
     * sets the name of the item
     *
     * @param what string
     */
    public void setWhat(String what) {
        mWhat = what;
    }

    /**
     * returns the location of the item
     *
     * @return string
     */
    public String getWhere() {
        return mWhere;
    }

    /**
     * sets the location of the item
     *
     * @param where string
     */
    public void setWhere(String where) {
        mWhere = where;
    }

    private String oneLine(String pre, String post) {
        return pre + mWhat + " " + post + mWhere;

    }
}