package com.bullish.checkout.domain.service

import com.bullish.checkout.adapters.input.rest.dto.UpdateBasketDto
import com.bullish.checkout.adapters.output.repository.InMemoryBasketRepositoryImpl
import com.bullish.checkout.domain.models.Basket
import com.bullish.checkout.domain.ports.input.ModifyingBasketUseCase
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class BasketService @Inject constructor(
    val repository: InMemoryBasketRepositoryImpl
) : ModifyingBasketUseCase {
    override fun modifyBasket(updateBasketDto: UpdateBasketDto): Basket {
        val updateBody = updateBasketDto.toDomain()
        return repository.updateBasket(updateBody)
    }
}