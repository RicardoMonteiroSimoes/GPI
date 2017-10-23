/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.rs.logiceditor.model.master;

import ch.rs.logiceditor.model.util.ClassData;
import com.google.gson.annotations.Expose;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import javax.management.modelmbean.InvalidTargetObjectTypeException;

/**
 *
 * @author Ricardo
 */
public final class ConnectionPoint<T> extends Observable implements Observer {

    @Expose
    private String pointName;
    @Expose
    private ConnectionType connectionType;
    @Expose
    private ClassData classData;

    private T holdingValue = null;

    public enum ConnectionType {
        INPUT, OUTPUT
    }

    public ConnectionPoint(ConnectionType connectionType, ClassData classData) {
        this.connectionType = connectionType;
        pointName = connectionType.name();
        this.classData = classData;
     
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public Class<T> getType() {
        return classData.getClassType();
    }

    /**
     * This method is used to set a value on the connection Point. If the value
     * has not changed, then there will be no update called. This is needed as
     * else you'd get a StackOverflow when doing a loop.
     *
     * @param value
     */
    public void setValue(T value) {
        if (value != holdingValue) {
            holdingValue = value;
            setChanged();
            notifyObservers(holdingValue);
        }
    }

    
    /**
     * This function is only to be called by pulseValue() as else it might result in
     * a StackOverflow.
     * @param value 
     */
    private void setUncheckedValue(T value) {
        holdingValue = value;
        setChanged();
        notifyObservers(holdingValue);
    }

    /**
     * This function is used to do a short pulse of a value on this CP. It uses
     * does not check the already setValue.
     *
     * @param value
     */
    public void pulseValue(T value) {
        T temporaryValue = holdingValue;
        setUncheckedValue(value);
        setUncheckedValue(temporaryValue);
    }

    /**
     * Returns the current Value being held by this Point. Returns null if the
     * value has not been set.
     *
     * @return
     */
    public T getValue() {
        return holdingValue;
    }

    @Override
    public void update(Observable o, Object arg) {
        setValue((T) arg);
    }

    public void addObserver(ConnectionPoint<T> pointToObserve) throws InvalidTargetObjectTypeException {
        if (pointToObserve.getConnectionType() == this.connectionType) {
            throw new InvalidTargetObjectTypeException("This Point cannot be connected to a "
                    + "point of the same Type!\\n You're trying to connect " + this.connectionType.name()
                    + " to a " + pointToObserve.getConnectionType().name());
        } else if (this.connectionType == ConnectionType.OUTPUT && this.countObservers() == 1) {
            throw new InvalidTargetObjectTypeException("You cannot create more than one connection to an Output!");
        } else if (this.getType() != pointToObserve.getType()) {
            throw new InvalidTargetObjectTypeException("These Points are not compatible. One is " + getType().getName()
                    + " and the other one is " + pointToObserve.getType().getName());
        } else {
            super.addObserver(pointToObserve);
        }
    }

}
