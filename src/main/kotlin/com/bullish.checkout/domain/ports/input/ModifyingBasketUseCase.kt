package com.bullish.checkout.domain.ports.input

import com.bullish.checkout.adapters.input.rest.dto.UpdateBasketDto
import com.bullish.checkout.domain.models.Basket

interface ModifyingBasketUseCase {
    fun modifyBasket(updateBasketDto: UpdateBasketDto): Basket
}