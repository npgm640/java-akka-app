package beth.demo.com.akka.actor.sample2;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import beth.demo.com.akka.StdIn;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/*
* actor that communicates on messages changes the behaviour
 */


public class ActorEx2 {
    /*
     *created an actor with two different behaviours and can switch between them based on messages.
     */
    static class Alarm extends AbstractLoggingActor {

        private final String password;
        private final PartialFunction<Object, BoxedUnit> enabled;
        private final PartialFunction<Object, BoxedUnit> disabled;


        Alarm(String password) {
            this.password = password;

            enabled = ReceiveBuilder
                    .match(Activity.class, this::onActivity)
                    .match(Disable.class, this::onDisable)
                    .build();

            disabled = ReceiveBuilder
                    .match(Enable.class, this::onEnable)
                    .build();
            //initial will be disabled when we installed the alarm
            receive(disabled);
        }

        private void onEnable(Enable enable) {
            if (password.equals(enable.password)) {
                log().info("Alarm enabled");
                getContext().become(enabled);
            } else
                log().info("Someone failed to enable the alarm");
        }

        private void onDisable(Disable disable) {
            if (password.equals(disable.password)) {
                log().info("Alarm disabled");
                getContext().become(disabled);
            } else
                log().warning("Someone who didn't know the password tried to disabled it");
        }

        private void onActivity(Activity ignored) {
            log().warning("Alarm beep beep ....alarm!!!");
        }

        //protocol messages
        static class Activity {
        }

        static class Disable {
            private final String password;

            public Disable(String password) {
                this.password = password;
            }
        }

        static class Enable {
            private final String password;

            public Enable(String password) {
                this.password = password;
            }
        }

        public static Props props(String password) {
            return Props.create(Alarm.class, password);
        }

    }

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create();
        final ActorRef alarm = actorSystem.actorOf(Alarm.props("bethapudi"), "alarm");

        //pass messages to actor
        alarm.tell(new Alarm.Activity(), ActorRef.noSender());
        alarm.tell(new Alarm.Enable("dogs"), ActorRef.noSender());
        alarm.tell(new Alarm.Enable("bethapudi"), ActorRef.noSender());
        alarm.tell(new Alarm.Activity(), ActorRef.noSender());
        alarm.tell(new Alarm.Disable("dogs"), ActorRef.noSender());
        alarm.tell(new Alarm.Disable("bethapudi"), ActorRef.noSender());
        alarm.tell(new Alarm.Activity(), ActorRef.noSender());

        System.out.println("Enter to terminate");
        StdIn.readLine();
        actorSystem.terminate();


    }
}