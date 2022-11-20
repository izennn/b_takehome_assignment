package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryDiscountRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.models.Discount
import com.bullish.checkout.domain.models.Receipt
import com.bullish.checkout.domain.ports.input.CheckoutUseCase
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CheckoutService @Inject constructor(
    val basketRepository: InMemoryBasketRepositoryImpl,
    val productRepository: InMemoryProductRepositoryImpl,
    val discountRepository: InMemoryDiscountRepositoryImpl
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

    private fun getProductPrice(productId: String): Double? {
        return when (val product = productRepository.getByProductId(productId)) {
            null -> null
            else -> product.price
        }
    }

    private fun getProductDiscount(productId: String): Discount? {
        return discountRepository.getDiscount(productId)
    }
    private fun totalProductPrice(productId: String, count: Int): Double {
        var sum = 0.00
        val price = getProductPrice(productId)

        when (price) {
            null -> {
                logger.error("Could not find product info for productId={}", productId)
                return sum
            }
            else -> {
                sum += (price * count)
            }
        }

        return when (val discount = getProductDiscount(productId)) {
            null -> sum
            else -> {
                val discountAppliedTimes = count.floorDiv(discount.productCount)
                val discountPercentage = discount.discountInPercentage * 0.01
                sum - (price * discountPercentage * discountAppliedTimes)
            }
        }
    }

    companion object {
        private val logger = org.slf4j.LoggerFactory.getLogger(CheckoutService::class.java)
    }
}
