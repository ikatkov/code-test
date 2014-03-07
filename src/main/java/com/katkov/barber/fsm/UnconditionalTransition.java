package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public class UnconditionalTransition<T> extends Transition<T> {

    public UnconditionalTransition(IState<T> state) {
        super(state);
    }


}
