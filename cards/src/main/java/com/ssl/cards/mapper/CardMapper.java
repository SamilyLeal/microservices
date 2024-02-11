package com.ssl.cards.mapper;

import com.ssl.cards.dto.CardDTO;
import com.ssl.cards.entities.Card;

public class CardMapper {
    private CardMapper() {}

    public static Card toCard(CardDTO cardDTO, Card card) {
        card.setMobileNumber(cardDTO.getMobileNumber());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardType(cardDTO.getCardType());
        card.setTotalLimit(cardDTO.getTotalLimit());
        card.setAmountUsed(cardDTO.getAmountUsed());
        card.setAvailableAmount(cardDTO.getAvailableAmount());

        return card;
    }

    public static CardDTO toCardDTO(Card card, CardDTO cardDTO) {
        cardDTO.setMobileNumber(card.getMobileNumber());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardType(card.getCardType());
        cardDTO.setTotalLimit(card.getTotalLimit());
        cardDTO.setAmountUsed(card.getAmountUsed());
        cardDTO.setAvailableAmount(card.getAvailableAmount());

        return cardDTO;
    }
}
