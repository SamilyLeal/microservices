package com.ssl.cards.services;

import com.ssl.cards.dto.CardDTO;

public interface CardService {
    void createCard(String phoneNumber);
    CardDTO fetchCard(String cardNumber);
    boolean updateCard(CardDTO cardDTO);
    boolean deleteCard(String cardNumber);
}
