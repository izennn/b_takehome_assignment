package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.Receipt
import com.bullish.checkout.domain.ports.input.CheckoutUseCase
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CheckoutService @Inject constructor(
    val basketRepository: InMemoryBasketRepositoryImpl,
    val productRepository: InMemoryProductRepositoryImpl
) : CheckoutUseCase {
    override fun checkout(): Receipt {
        // TODO: factor in discounts
        var sum = 0.00
        val basket = basketRepository.getBasket()
        basket.productCount.entries.forEach { entry ->
            sum = sum.plus(totalProductPrice(entry.key, entry.value))
        }

        return Receipt(
            price = sum
        )
    }

    private fun totalProductPrice(productId: String, count: Int): Double {
        when (val product = productRepository.getByProductId(productId)) {
            null -> logger.error("Could not find product info for productId={}", productId)
            else -> {
                return product.price * count
            }
        }
        return 0.00
    }

    companion object {
        private val logger = org.slf4j.LoggerFactory.getLogger(CheckoutService::class.java)
    }
}
