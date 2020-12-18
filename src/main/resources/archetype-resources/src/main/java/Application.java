package ${package};
import ${package}.connector.HelloWorldConnector;

public class Application {

  public static void main( String[] args ){

    final HelloWorldConnector helloWorldConnector = new HelloWorldConnector();
    helloWorldConnector.process();

  }
}
