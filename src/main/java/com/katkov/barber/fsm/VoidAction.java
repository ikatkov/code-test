package com.katkov.barber.fsm;

/**
 * @author Igor Katkov
 */
class VoidAction<T> implements IAction<T> {
    @Override
    public boolean isValid(T context) {
        return true;
    }

    @Override
    public void execute(T context) {
        //none
    }
}
