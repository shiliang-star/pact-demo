package kl.v2x.test;

import au.com.dius.pact.core.model.Interaction;
import au.com.dius.pact.core.model.ProviderState;
import au.com.dius.pact.core.model.RequestResponseInteraction;
import au.com.dius.pact.core.model.Response;
import au.com.dius.pact.provider.*;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import au.com.dius.pact.provider.reporters.VerifierReporter;
import kl.v2x.provider.ProviderApplication;
import kl.v2x.provider.bean.Customer;
import kl.v2x.provider.service.CertApplyService;
import org.apache.http.HttpRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.mockito.Mockito.when;

/**
 * @author shil
 * @date 2022/6/1
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProviderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("V2X-PCA-Service")
@PactBroker(scheme = "https",host = "ariman.pactflow.io",authentication = @PactBrokerAuth(username = "bdYdtbx2qrRHsHfmYDOar8ecaXX0haE",password = "wuKYeBiLtmhKqR5ODqpmzZ7iVcDVt"))
public class CertApplyPactTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setupTestTarget(PactVerificationContext context) {
//        when(certApplyService.applyCert(new byte[]{1,2,3,4})).thenReturn(new byte[]{12, 32, 45, 23});
        context.setTarget(new HttpTestTarget("localhost", port));
        context.setVerifier(new MyVerifier());

        System.setProperty("pact.verifier.publishResults", "true");
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    public void pactVerificationTestTemplate(PactVerificationContext context, HttpRequest request) {
        context.verifyInteraction();

    }


    @State("Cert apply success")
    public void setupCertApplySuccess(Map<String, Object> data) throws IOException {
        System.out.println(data);
    }

}

class MyVerifier extends ProviderVerifier implements IProviderVerifier {

    @NotNull
    @Override
    public VerificationResult verifyRequestResponsePact(@NotNull Response expectedResponse, @NotNull ProviderResponse actualResponse, @NotNull String interactionMessage, @NotNull Map<String, Object> failures, @NotNull String interactionId, boolean pending) {
        System.out.println("开始校验拉");
        byte[] bytes = actualResponse.getBody().getBytes();
        System.out.println(Arrays.toString(bytes));
//        VerificationResult.Ok instance = VerificationResult.Ok.INSTANCE;
        VerificationResult verificationResult = new VerificationResult.Failed(null, "asn not match", "asn not macth V2", null, true, null);
        return verificationResult;
    }
}
