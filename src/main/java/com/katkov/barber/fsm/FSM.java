package com.katkov.barber.fsm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Igor Katkov
 */
public class FSM<T> {
    private Set<Entity<T>> entities = new HashSet<Entity<T>>();

    public void add(Entity<T> entity) {
        System.out.println("Entity added:" + entity);
        entities.add(entity);
    }

    public void tick(T context) {
        Set<Entity<T>> entitiesToRemove = new HashSet<Entity<T>>();
        for (Entity<T> entity : entities) {
            IState<T> fromState = entity.getState();
            IState<T> toState = null;
            if (fromState instanceof FinalState) {
                System.out.println("Entity removed:" + entity);
                entitiesToRemove.add(entity);
            } else {
                Transition<T> transition = fromState.nextTransition(context);
                if (transition != Transition.VOID) {
                    transition.getAction().execute(context);
                    toState = transition.getState();
                    entity.setState(toState);
                }
            }
            System.out.println(String.format("%s %s->%s", entity.getSimpleName(), fromState, toState));
        }

        entities.removeAll(entitiesToRemove);
        System.out.println(context);
    }

}
