package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public interface IState<T> {
    public Transition<T> nextTransition(T context);
}
