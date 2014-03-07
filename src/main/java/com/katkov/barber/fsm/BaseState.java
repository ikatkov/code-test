package com.katkov.barber.fsm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Igor Katkov
 */
public abstract class BaseState<T> implements IState<T> {
    protected Set<Transition<T>> transitions = new HashSet<Transition<T>>();

    public BaseState(Transition<T>... transitions) {
        this.transitions = new HashSet<Transition<T>>(Arrays.asList(transitions));
    }

    @Override
    public Transition<T> nextTransition(T context) {
        for (Transition<T> transition : transitions) {
            if (transition.getAction().isValid(context)) { return transition; }
        }
        return Transition.VOID;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }


}
