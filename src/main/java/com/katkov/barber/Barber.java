package com.katkov.barber;

import com.katkov.barber.fsm.BaseState;
import com.katkov.barber.fsm.Entity;
import com.katkov.barber.fsm.IAction;
import com.katkov.barber.fsm.IState;
import com.katkov.barber.fsm.StartState;
import com.katkov.barber.fsm.UnconditionalTransition;

/**
 * @author Igor Katkov
 */
class Barber implements Entity<Context> {
    private final SleepState sleepState = new SleepState();
    private final StartState startState = new StartState<Context>(new UnconditionalTransition<Context>(sleepState));
    /*    private final InvitingState invitingState = new InvitingState(new UnconditionalTransition(cutHairState));
        private final CutHairState cutHairState = new CutHairState(
                new Transition<Context>(sleepState, new WaitingRoomIsEmpty()),
                new Transition<Context>(invitingState, new WaitingRoomHasCustomers()));*/
    private IState<Context> currentState;
    private Customer currentCustomer;
    private String name;
    private boolean wokenUp;

    public Barber(String name) {
        this.name = name;
        currentState = startState;
    }

    public void wakeUp(Customer customer) {
        wokenUp = true;
        currentCustomer = customer;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }


    public boolean isAsleep() {
        return currentState == sleepState;
    }

    @Override
    public IState<Context> getState() {
        return currentState;
    }

    @Override
    public void setState(IState<Context> state) {
        if (state == null) { throw new IllegalArgumentException(); }
        currentState = state;
    }

    @Override
    public String getSimpleName() {
        return "Barber: " + name;
    }



    @Override
    public String toString() {
        return "Barber{" +
                "name=" + name + ", " +
                "currentState=" + currentState +
                '}';
    }

    private class CutHairState extends BaseState<Context> {
    }

    private class InvitingState extends BaseState<Context> {
    }

    private class WaitingRoomIsEmpty implements IAction<Context> {

        @Override
        public boolean isValid(Context context) {
            return context.getWaitingRoom().isEmpty();
        }

        @Override
        public void execute(Context context) {
            Customer currentCustomer = context.getBarber().getCurrentCustomer();
            currentCustomer.dismiss();
        }
    }

    private class WaitingRoomHasCustomers implements IAction<Context> {

        @Override
        public boolean isValid(Context context) {
            return !context.getWaitingRoom().isEmpty();
        }

        @Override
        public void execute(Context context) {
            Customer currentCustomer = context.getBarber().getCurrentCustomer();
            currentCustomer.dismiss();
            Customer newCustomer = context.getWaitingRoom().inviteNextCustomer();
            newCustomer.invite();
        }
    }


    private class SleepState extends BaseState<Context> {
    }
}
