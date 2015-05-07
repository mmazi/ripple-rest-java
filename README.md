ripple-rest-java
================

A Java implementation for the Ripple REST API (https://ripple.com/build/ripple-rest/).

## Usage

### Prerequisites

Install Java 7, git and Maven if they're not yet installed.

Clone the ripple-rest-java code:

    git clone git@github.com:mmazi/ripple-rest-java.git

Install the code in your local maven repo:

    mvn install -DskipTests=true install

Use it in your Maven project:

    <dependency>
        <groupId>com.github.mmazi.ripple-rest</groupId>
        <artifactId>ripple-rest</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>


### Code example

    // Create the client object.
    Ripple ripple = RippleClientFactory.createClient("http://localhost:5990/");

    final String address = "rDiWNyxZqEfRrdsNbPPwgBUZFb4Xf17cNJ";

    // Query the server using the client object.
    final BalancesResponse balancesResponse = ripple.getBalances(address);

    // Print the results.
    final List<Amount> balances = balancesResponse.getBalances();
    System.out.printf("Balances for %s:%n", address);
    for (Amount balance : balances) {
        final String counterparty = balance.getCounterparty() == null
                ? ""
                : String.format(" (counterparty: %s)", balance.getCounterparty());
        System.out.printf("   %s %s%s%n", balance.getValue(), balance.getCurrency(), counterparty);
    }

Output of the above code will be something like:

    Balances for rDiWNyxZqEfRrdsNbPPwgBUZFb4Xf17cNJ:
       149.99933 XRP
       0 USD (counterparty: rhiAtoMmU2hFuzS6eWdtix29doajBLwatM)
       0.09563782948739057 BTC (counterparty: rpvfJ4mR6QQAeogpXEKnuyGBx8mYCSnYZi)
       -1.9 USD (counterparty: rfitr7nL7MX85LLKJce7E3ATQjSiyUPDfj)
       1.3 USD (counterparty: rwNqomndnosPyZzmw2kbrsQZFbnksMyDQ2)
       0 USD (counterparty: reQdXsS6ZchUsuu6HPorxCKq2LDzWQuVA)


See also `src/test/java/com/github/mmazi/ripplerest/RippleTest.java` for more examples of usage.

## Tests

The tests are in `src/test/java/com/github/mmazi/ripplerest/RippleTest.java`. All test are executed against the `ripple-rest` server that must be running on `localhost:5990`.

### Running the tests

0. Make sure the [ripple-rest](https://github.com/ripple/ripple-rest) server is running on `localhost:5990` (the default port).
0. Run `mvn clean test`.

## Notes

* The JSR-303 (Bean Validation) annotations are included on the model classes, but the constraints are currently not enforced.
