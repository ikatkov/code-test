package com.katkov.barber;

/**
 * @author Igor Katkov
 */
class Context {
    private WaitingRoom waitingRoom = new WaitingRoom();
    private Barber barber;


    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Barber getBarber() {
        return barber;
    }

    public WaitingRoom getWaitingRoom() {
        return waitingRoom;
    }

    @Override
    public String toString() {
        return "Context{" +
                "waitingRoom=" + waitingRoom +
                ", barber=" + barber +
                '}';
    }
}
