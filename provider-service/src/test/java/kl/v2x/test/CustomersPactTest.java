package kl.v2x.test;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import au.com.dius.pact.provider.junitsupport.loader.PactBrokerAuth;
import kl.v2x.provider.ProviderApplication;
import kl.v2x.provider.bean.Customer;
import kl.v2x.provider.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ProviderApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Provider("provider-springboot-customer")
@PactBroker(scheme = "https",host = "ariman.pactflow.io",authentication = @PactBrokerAuth(username = "bdYdtbx2qrRHsHfmYDOar8ecaXX0haE",password = "wuKYeBiLtmhKqR5ODqpmzZ7iVcDVt"))
class CustomersPactTest {

  @LocalServerPort
  int port;

  @MockBean
  private CustomerRepository repository;

  @BeforeEach
  public void setupTestTarget(PactVerificationContext context) {
    context.setTarget(new HttpTestTarget("localhost", port));
    System.setProperty("pact.verifier.publishResults", "true");
  }

  @TestTemplate
  @ExtendWith(PactVerificationInvocationContextProvider.class)
  public void pactVerificationTestTemplate(PactVerificationContext context) {
    context.verifyInteraction();
  }

  @State("customers exists")
  public void setupCustomerExistsState() throws IOException {
    when(repository.getAllCustomers()).thenReturn(
            Arrays.asList(new Customer(1999L, "shil"),
                    new Customer(2000L, "Tom")));
  }
// 通过方法去校验参数 PACT VS SCC
// 一个接口多个契约
}