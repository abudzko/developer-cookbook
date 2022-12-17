cd ../../../../../java-agent
pwd
mvn package
cd ../java-study-cookbook/
pwd
mvn package
java -javaagent:target/java-agent.jar -cp target/classes com.budzko.cookbook.java.agent.JavaAgentApp