package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryDiscountRepositoryImpl
import com.bullish.checkout.adapters.output.repository.InMemoryProductRepositoryImpl
import com.bullish.checkout.domain.Utils
import com.bullish.checkout.domain.models.Discount
import com.bullish.checkout.domain.models.Product
import com.bullish.checkout.domain.models.Receipt
import com.bullish.checkout.domain.ports.input.CheckoutUseCase
import kotlinx.serialization.Serializable
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class CheckoutService @Inject constructor(
    val basketRepository: InMemoryBasketRepositoryImpl,
    val productRepository: InMemoryProductRepositoryImpl,
    val discountRepository: InMemoryDiscountRepositoryImpl
) : CheckoutUseCase {
    override fun checkout(): Receipt {
        val basket = basketRepository.getBasket()
        val items: List<ReceiptItem> =
            basket.productCount.entries.mapNotNull { entry -> mapToReceiptItem(entry.key, entry.value) }

        return translateItemsToReceipt(items)
    }

    private fun translateItemsToReceipt(items: List<ReceiptItem>): Receipt {
        var totalPriceBeforeDiscount = 0.00
        var totalDiscount = 0.00
        var totalPrice = 0.00
        items.forEach { item ->
            run {
                totalPriceBeforeDiscount += item.priceBeforeDiscount
                totalDiscount += item.discount
                totalPrice += item.totalPrice
            }
        }

        return Receipt(
            items = items,
            totalPriceBeforeDiscount = Utils.roundOffDecimal(totalPriceBeforeDiscount),
            totalDiscount = Utils.roundOffDecimal(totalDiscount),
            totalPrice = Utils.roundOffDecimal(totalPrice)
        )
    }

    private fun mapToReceiptItem(productId: String, count: Int): ReceiptItem? {
        val product = getProduct(productId) ?: return null
        val discount = getProductDiscount(productId)
        val priceBeforeDiscount = Utils.roundOffDecimal(product.price * count)
        val calculatedDiscount = Utils.roundOffDecimal(calculateDiscount(product.price, discount, count))

        return ReceiptItem(
            product = product.name,
            priceBeforeDiscount = priceBeforeDiscount,
            discount = calculatedDiscount,
            discountDescription = discount?.description,
            totalPrice = Utils.roundOffDecimal(priceBeforeDiscount - calculatedDiscount)
        )
    }
    private fun getProduct(productId: String): Product? {
        return when (val product = productRepository.getByProductId(productId)) {
            null -> {
                logger.info("Checking out, could not find product information on productId={}", productId)
                null
            }

            else -> product
        }
    }

    private fun getProductDiscount(productId: String): Discount? {
        return discountRepository.getDiscount(productId)
    }

    private fun calculateDiscount(price: Double, discount: Discount?, count: Int): Double {
        if (discount == null) return 0.00
        
        val discountAppliedCount = count.floorDiv(discount.productCount)
        return price * discount.discountInPercentage * 0.01 * discountAppliedCount
    }

    companion object {
        private val logger = org.slf4j.LoggerFactory.getLogger(CheckoutService::class.java)
    }
}

@Serializable
data class ReceiptItem(
    val product: String,
    val priceBeforeDiscount: Double,
    val discount: Double,
    val discountDescription: String? = null,
    val totalPrice: Double
)
