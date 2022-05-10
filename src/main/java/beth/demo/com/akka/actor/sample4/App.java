package beth.demo.com.akka.actor.sample4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import beth.demo.com.akka.StdIn;
import beth.demo.com.akka.actor.sample3.ActorEx3;


// let's combine a bunch of concepts
public class App {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create();

        ActorRef db = system.actorOf(DbSupervisor.props(), "db");

        system.actorOf(WebServer.props(db, "localhost", 8080), "webserver");

        System.out.println("ENTER to terminate");
        StdIn.readLine();
        system.terminate();
    }
}