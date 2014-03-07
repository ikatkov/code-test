package com.katkov.barber;

import com.katkov.barber.fsm.FSM;

import java.util.Random;

/**
 * @author Igor Katkov
 */
public class BarberMain {
    private static Random random = new Random();

    public static void main(String... args) {
        Context context = new Context();
        Barber barber = new Barber("1");
        context.setBarber(barber);

        FSM<Context> fsm = new FSM<Context>();
        fsm.add(barber);


        int customerCounter = 0;
        for (int j = 0; j < 100; j++) {
            System.out.println("Tick #" + j);
            if (random.nextInt(100) < 90) {
                Customer customer = new Customer("" + ++customerCounter);
                fsm.add(customer);
            }
            fsm.tick(context);
        }
    }
}
