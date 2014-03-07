package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public final class StartState<T> extends BaseState<T> {

    public StartState(Transition<T>... transitions) {
        super(transitions);
    }


}
