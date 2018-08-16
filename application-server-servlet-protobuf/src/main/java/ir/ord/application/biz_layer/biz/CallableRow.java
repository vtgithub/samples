package ir.ord.application.biz_layer.biz;

import java.util.concurrent.Callable;

/**
 * Created by vahid on 6/13/17.
 */
public class CallableRow<E> {

    public CallableRow(byte operation, String id, E entityOldValue, Callable<Void> callable) {
        Operation = operation;
        this.id = id;
        if (entityOldValue != null)
            this.entityOldValue = entityOldValue;
        this.callable = callable;
    }


    private byte Operation;
    private String id;
    private E entityOldValue;
    private Callable<Void> callable;

    public byte getOperation() {
        return Operation;
    }

    public void setOperation(byte operation) {
        Operation = operation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public E getEntityOldValue() {
        return entityOldValue;
    }

    public void setEntityOldValue(E entityOldValue) {
        this.entityOldValue = entityOldValue;
    }

    public Callable<Void> getCallable() {
        return callable;
    }

    public void setCallable(Callable<Void> callable) {
        this.callable = callable;
    }

    public String getEntityClassName(){
        return this.entityOldValue.getClass().getName().replace("ir.ord.application.dal.entities.", "");
    }
}
