package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public interface Entity<T> {
    public IState<T> getState();

    public void setState(IState<T> state);

    public String getSimpleName();
}
