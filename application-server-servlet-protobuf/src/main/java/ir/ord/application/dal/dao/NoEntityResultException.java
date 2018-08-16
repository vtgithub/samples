package ir.ord.application.dal.dao;

/**
 * Created by vahid on 4/24/17.
 */
public class NoEntityResultException extends Exception {
    public NoEntityResultException(Throwable e)  {
        super(e);
    }

    public NoEntityResultException() {
        super();
    }
}
