package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public interface IAction<T> {
    public boolean isValid(T context);

    public void execute(T context);
}
