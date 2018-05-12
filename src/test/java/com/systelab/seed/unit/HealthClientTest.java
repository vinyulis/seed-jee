package com.systelab.seed.unit;

import com.systelab.seed.TestUtil;
import com.systelab.seed.client.HealthClient;
import com.systelab.seed.client.RequestException;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@TmsLink("TC0003_HealthManagement_IntegrationTest")
@Feature("Check that the service is healthy by calling a REST endpoint.")
@DisplayName("Health Check Test Suite")
public class HealthClientTest extends BaseClientTest {

    public static HealthClient clientForHealth;

    @BeforeAll
    public static void init() throws RequestException {
        clientForHealth = new HealthClient();
    }

    @DisplayName("Get the Health status")
    @Description("Action: Get the Health status and check that is OK.")
    @Tag("health")
    @Severity(SeverityLevel.BLOCKER)
    @Test
    public void testHealth() throws RequestException {

        Exception caughtException = null;
        try {
            clientForHealth.getHealth();
        } catch (Exception ex) {
            caughtException = ex;
        }
        TestUtil.checkObjectIsNull("Exception", caughtException);
    }

}
