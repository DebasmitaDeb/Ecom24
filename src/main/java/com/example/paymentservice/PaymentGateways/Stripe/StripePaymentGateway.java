package com.example.paymentservice.PaymentGateways.Stripe;

import com.example.paymentservice.PaymentGateways.PaymentGateway;
import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.stripe.model.PaymentLink.create;

@Service
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;
    @SneakyThrows
    @Override
    public String generatePaymentLink(Long amount) {
        Stripe.apiKey = stripeSecretKey;
        ProductCreateParams params =
                ProductCreateParams.builder().setName("generic").build();
        Product product = Product.create(params);

        PriceCreateParams priceCreateParams =
                PriceCreateParams.builder()
                        .setCurrency("INR")
                        .setUnitAmount(amount)  // amount * 100
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(priceCreateParams);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .build();

        PaymentLink paymentLink = create(paymentLinkCreateParams);
        return paymentLink.getUrl() ;
    }
}
