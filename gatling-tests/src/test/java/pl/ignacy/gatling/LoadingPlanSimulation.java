package pl.ignacy.gatling;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class LoadingPlanSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8081")
            .contentTypeHeader("application/json")
            .acceptHeader("application/json");

    String requestBody = """
            {
                "vehicleIds": [1, 2, 52, 53, 54, 102],
                "packagesIds": [102, 4, 59, 60],
                "strategyType": "SMART"
            }
            """;

    ScenarioBuilder scenario = scenario("Loading Plan Throughput Test")
            .exec(http("POST /api/v1/plans")
                    .post("/api/v1/plans")
                    .body(StringBody(requestBody))
                    .check(status().is(200)));

    {
        setUp(
                scenario.injectOpen(
                        rampUsers(100).during(10),
                        constantUsersPerSec(500).during(30),
                        constantUsersPerSec(1000).during(30),
                        constantUsersPerSec(2000).during(450)
                )
        ).protocols(httpProtocol);
    }

}
