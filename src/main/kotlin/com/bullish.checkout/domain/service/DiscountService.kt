package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.output.repository.InMemoryDiscountRepositoryImpl
import com.bullish.checkout.domain.models.Discount
import com.bullish.checkout.domain.ports.input.CreatingDiscountUseCase
import com.bullish.checkout.domain.ports.input.DeletingDiscountUseCase
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class DiscountService @Inject constructor(
    val repository: InMemoryDiscountRepositoryImpl
) : CreatingDiscountUseCase, DeletingDiscountUseCase {
    override fun createDiscount(discount: Discount) {
        repository.addDiscount(discount)
    }

    override fun deleteDiscount(id: String) {
        repository.deleteDiscount(id)
    }
}