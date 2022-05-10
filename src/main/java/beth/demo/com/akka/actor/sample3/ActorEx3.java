package beth.demo.com.akka.actor.sample3;

import akka.actor.*;
import akka.japi.pf.ReceiveBuilder;

public class ActorEx3 {
    public static class NonTrustWorthyChild extends AbstractLoggingActor {

        public static class Command {}

        private long messages = 0L;

        {
            receive(ReceiveBuilder
            .match(Command.class, this::onCommand)
            .build()
            );
        }

        private  void onCommand(Command c)  {
            messages++;
            if (messages % 4 == 0) {
                throw new RuntimeException("Oh no, I got four commands, I can't handle any more");
            } else log().info("Got a command" + messages);
        }

        public static Props props() {
            return Props.create(NonTrustWorthyChild.class);
        }
    }
}
