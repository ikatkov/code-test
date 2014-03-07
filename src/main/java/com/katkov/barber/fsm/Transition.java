package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
public class Transition<T> {
    public static final Transition VOID = new Transition();
    private IState<T> state;
    private IAction<T> action = new VoidAction<T>();

    private Transition() {}

    public Transition(IState<T> state) {
        this.state = state;
    }

    public Transition(IState<T> state, IAction<T> action) {
        this.state = state;
        this.action = action;
    }

    public IState<T> getState() {
        return state;
    }

    public IAction<T> getAction() {
        return action;
    }

}
