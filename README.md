The purpose of the project is to expose rest endpoints via Camel.

Technology stack:

- spring-boot-starter-web v1.2.5
- camel v2.15.3
- lombok


#### Running ####

<pre><code>mvn spring-boot:run</code></pre>

#### Usage ####

Create an account with £100 starter balance:
<pre><code>curl -X POST http://localhost:8080/bank/account/createAccount/100</code></pre>

Response:
```json
{
  "id" : "2bdf42c7-8401-4ab5-b982-290feeb810b4"
}
```

Get the account:
<pre><code>curl -X GET http://localhost:8080/bank/account/2bdf42c7-8401-4ab5-b982-290feeb810b4</code></pre>

Response:
```json
{
  "accountId" : {
    "id" : "2bdf42c7-8401-4ab5-b982-290feeb810b4"
  },
  "balance" : {
    "amount" : 100
  }
}
```

Get Balance:

<pre><code>curl -X GET http://localhost:8080/bank/account/2bdf42c7-8401-4ab5-b982-290feeb810b4/getBalance</code></pre>

Response:
```json
{
  "amount" : 100
}
```

Debit £10:
<pre><code>curl -X POST http://localhost:8080/bank/account/2bdf42c7-8401-4ab5-b982-290feeb810b4/debit/10</code></pre>

Response:
```json
{
  "amount" : 90
}
```

Credit £20:
<pre><code>curl -X POST http://localhost:8080/bank/account/2bdf42c7-8401-4ab5-b982-290feeb810b4/credit/20</code></pre>

Response:
```json
{
  "amount" : 110
}
```

#### The camel route builder ####

```java
@Component
public class AccountRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true")
                .port(8080);

        rest("/").produces("application/json").consumes("application/json")
                .post("/createAccount/{balance}").to("bean:bankAppServiceImpl?method=createAccount(in.header.balance)")
                .get("/{accountId}").to("bean:bankAppServiceImpl?method=getAccount(in.header.accountId)")
                .get("/{accountId}/getBalance").to("bean:bankAppServiceImpl?method=getBalance(in.header.accountId)")
                .post("/{accountId}/debit/{amount}").to("bean:bankAppServiceImpl?method=debit(in.header.accountId, in.header.amount)")
                .post("/{accountId}/credit/{amount}").to("bean:bankAppServiceImpl?method=credit(in.header.accountId, in.header.amount)");


    }
}
```

#### Camel configuration ####

```java
@Configuration
@ComponentScan("bank")
public class CamelConfig {

    private static final String CAMEL_URL_MAPPING = "/bank/account/*";
    private static final String CAMEL_SERVLET_NAME = "CamelServlet";

    @Bean
    public ServletRegistrationBean camelServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), CAMEL_URL_MAPPING);
        registration.setName(CAMEL_SERVLET_NAME);
        return registration;
    }
}
```
