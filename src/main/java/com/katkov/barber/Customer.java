package com.katkov.barber;

import com.katkov.barber.fsm.BaseState;
import com.katkov.barber.fsm.Entity;
import com.katkov.barber.fsm.FinalState;
import com.katkov.barber.fsm.IAction;
import com.katkov.barber.fsm.IState;
import com.katkov.barber.fsm.StartState;
import com.katkov.barber.fsm.Transition;
import com.katkov.barber.fsm.UnconditionalTransition;

/**
 * @author Igor Katkov
 */
class Customer implements Entity<Context> {
    private final FinalState finalState = new FinalState<Context>();
    private final SitAndWaitState sitAndWaitState = new SitAndWaitState();
    private final DismissedState dismissedState = new DismissedState(new UnconditionalTransition(finalState));
    private final ServicedState servicedState = new ServicedState();
    private final InvitedState invitedState = new InvitedState(new UnconditionalTransition(servicedState));
    private final StartState startState = new StartState(
            new Transition<Context>(finalState, new BarberBusyNoSeatsLeft()),
            new Transition<Context>(sitAndWaitState, new BarberBusySeatsAvailable()),
            new Transition<Context>(servicedState, new BarberAsleep()));
    IState<Context> currentState;
    private String name;

    public Customer(String name) {
        this.name = name;
        currentState = startState;
    }

    public void dismiss() {
        currentState = dismissedState;
    }

    public void invite() {
        currentState = invitedState;
    }

    @Override
    public IState<Context> getState() {
        return currentState;
    }

    @Override
    public void setState(IState<Context> state) {
        currentState = state;
    }

    @Override
    public String getSimpleName() {
        return "Customer: " + name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "currentState=" + currentState +
                ", name='" + name + '\'' +
                '}';
    }


    private class BarberBusyNoSeatsLeft implements IAction<Context> {
        @Override
        public boolean isValid(Context context) {
            return !context.getBarber().isAsleep() && !context.getWaitingRoom().hasEmptySeats();
        }

        @Override
        public void execute(Context context) {
            //none
        }
    }

    private class SitAndWaitState extends BaseState<Context> {}

    private class InvitedState extends BaseState {
        public InvitedState(Transition transition) {
            super(transition);
        }
    }

    private class ServicedState extends BaseState<Context> {}

    private class DismissedState extends BaseState {
        public DismissedState(Transition transition) {
            super(transition);
        }
    }

    private class BarberBusySeatsAvailable implements IAction<Context> {
        @Override
        public boolean isValid(Context context) {
            return !context.getBarber().isAsleep() && context.getWaitingRoom().hasEmptySeats();
        }

        @Override
        public void execute(Context context) {
            context.getWaitingRoom().sit(Customer.this);
        }
    }

    private class BarberAsleep implements IAction<Context> {
        @Override
        public boolean isValid(Context context) {
            return context.getBarber().isAsleep();
        }

        @Override
        public void execute(Context context) {
            context.getBarber().wakeUp(Customer.this);
        }
    }
}
