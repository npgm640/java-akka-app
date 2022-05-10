package beth.demo.com.akka.actor.sample1;


import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import beth.demo.com.akka.StdIn;

/*
* counter - actor that keeps state
 */
public class ActorEx1 {

    static class Counter extends AbstractLoggingActor {
        //protocol - communicates actor with messages
        static class Message { }

        private  int counter = 0;

        //initilizes blocks - acts like default constructor
        {
            receive(ReceiveBuilder
                .match(Message.class, this::onMessage) //creates message handler
                .build()
            );
        }

        private void onMessage(Message message) {
            counter++;
            log().info("Increased Counter " + counter);
        }

        //define a props factory
        public static Props props() {
            return Props.create(Counter.class);
        }
    }

    public static void main(String[] args) {

        //create the actor using props
       // Props.create(Counter.class);  create props using factory method inside the actor.
        ActorSystem actorSystem = ActorSystem.create("sample1");
        final ActorRef counter = actorSystem.actorOf(Counter.props(), "counter");  //gets actsRef

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0 ; j < 5; j++) {
                        counter.tell(new Counter.Message(), ActorRef.noSender());
                    }
                }
            }).start();
        }

        System.out.println("Enter to terminate");
        StdIn.readLine();
    }
}
