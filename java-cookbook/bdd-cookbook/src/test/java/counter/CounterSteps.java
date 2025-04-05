package counter;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterSteps {
    private Counter counter;
    private int previousValue;

    @Given("a counter")
    public void aCounter() {
    }

    @Given("the counter has any integral value")
    public void counterHasAnyIntegralValue() {
        counter = new Counter();
        previousValue = counter.getValue();
    }

    @When("the user increases the counter")
    public void increasesTheCounter() {
        counter.add();
    }

    @Then("the value of the counter must be 1 greater than previous value")
    public void theValueOfTheCounterMustBe1Greater() {
        assertEquals(1, counter.getValue() - previousValue);
    }
}
