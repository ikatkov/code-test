package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public final class FinalState<T> implements IState<T> {
    @Override
    public Transition<T> nextTransition(T context) {
        //none
        throw new IllegalStateException("No transition out of final state");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
