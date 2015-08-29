package bank.infrastructure;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

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
