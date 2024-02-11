package com.ssl.cards.services.impl;

import com.ssl.cards.dto.CardDTO;
import com.ssl.cards.entities.Card;
import com.ssl.cards.exceptions.CardAlreadyExistsException;
import com.ssl.cards.exceptions.ResourceNotFoundException;
import com.ssl.cards.mapper.CardMapper;
import com.ssl.cards.repositories.CardRepository;
import com.ssl.cards.services.CardService;
import com.ssl.cards.utils.CardConstants;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public void createCard(String mobileNumber) {
        Optional<Card> card = cardRepository.findByMobileNumber(mobileNumber);

        if(card.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given phone number");
        }

        cardRepository.save(createNewCard(mobileNumber));
    }

    public Card createNewCard(String mobileNumber) {
        Card card = new Card();

        card.setMobileNumber(mobileNumber);
        long cardNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        card.setCardNumber(Long.toString(cardNumber));
        card.setCardType(CardConstants.CREDIT_CARD);
        card.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        card.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        card.setAmountUsed(0D);
        card.setCreatedBy("salami");
        card.setCreatedAt(LocalDateTime.now());

        return card;
    }

    @Override
    public CardDTO fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
        );

        return CardMapper.toCardDTO(card, new CardDTO());
    }

    @Override
    public boolean updateCard(CardDTO cardDTO) {
        Card card = cardRepository.findByCardNumber(cardDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "card number", cardDTO.getCardNumber())
        );

        cardRepository.save(CardMapper.toCard(cardDTO, card));

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobile number", mobileNumber)
        );

        cardRepository.delete(card);

        return true;
    }
}
