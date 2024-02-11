package com.ssl.cards;

import com.ssl.cards.dto.CardDTO;
import com.ssl.cards.entities.Card;
import com.ssl.cards.exceptions.CardAlreadyExistsException;
import com.ssl.cards.exceptions.ResourceNotFoundException;
import com.ssl.cards.repositories.CardRepository;
import com.ssl.cards.services.impl.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CardServiceTests {

    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private CardServiceImpl cardService;

    @Test
    public void testCreateCard_Successful() {
        String phoneNumber = "8989898989";
        when(cardRepository.findByMobileNumber(phoneNumber)).thenReturn(Optional.empty());

        cardService.createCard(phoneNumber);

        verify(cardRepository, times(1)).save(any(Card.class));
    }

    @Test
    public void testCreateCard_AlreadyExistsCard() {
        String phoneNumber = "8989898989";
        when(cardRepository.findByMobileNumber(phoneNumber)).thenReturn(Optional.of(new Card()));

        assertThrows(CardAlreadyExistsException.class, () -> cardService.createCard(phoneNumber));

        verify(cardRepository, never()).save(any(Card.class));
    }

    @Test
    public void testFetchCard_Successful() {
        String phoneNumber = "8989898989";
        Card expectedCard = new Card();
        when(cardRepository.findByMobileNumber(phoneNumber)).thenReturn(Optional.of(expectedCard));

        CardDTO result = cardService.fetchCard(phoneNumber);

        assertEquals(expectedCard.getMobileNumber(), result.getMobileNumber());
    }

    @Test
    public void testFetchCard_NonExistingCard() {
        String phoneNumber = "8989898989";

        assertThrows(ResourceNotFoundException.class, () -> cardService.fetchCard(phoneNumber));
    }
}
