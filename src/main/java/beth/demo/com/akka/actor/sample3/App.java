package beth.demo.com.akka.actor.sample3;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import beth.demo.com.akka.StdIn;

public class App {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create();

        final ActorRef supervisor = actorSystem.actorOf(Supervisor.props(), "supervisor");

        for (int i =0; i<10 ; i++) {
            supervisor.tell(new ActorEx3.NonTrustWorthyChild.Command(), ActorRef.noSender());
        }

        System.out.println("ENTER to terminate");
        StdIn.readLine();
        actorSystem.terminate();
    }
}
