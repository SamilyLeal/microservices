package com.ssl.cards.controllers;

import com.ssl.cards.dto.CardsContactInfoDTO;
import com.ssl.cards.dto.CardDTO;
import com.ssl.cards.dto.ResponseDTO;
import com.ssl.cards.services.CardService;
import com.ssl.cards.utils.CardConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@EnableConfigurationProperties(CardsContactInfoDTO.class)
public class CardController {
    private final CardService cardService;

    @Autowired
    private CardsContactInfoDTO cardsContactInfoDTO;

    @Value("${build.version}")
    private String buildVersion;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam @Pattern(regexp = "\\d{10}", message = "Mobile number invalid  ss!")
            String mobileNumber) {
        cardService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDTO> fetchCardDetails(
            @RequestParam @Valid @Pattern(regexp = "\\d{10}", message = "Phone number invalid!")
            String mobileNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardService.fetchCard(mobileNumber));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCard(@RequestBody @Valid CardDTO cardDTO) {
        boolean isUpdated = cardService.updateCard(cardDTO);

        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam @Valid @Pattern(regexp = "\\d{10}", message = "Phone number invalid!") String mobileNumber) {
        boolean isDeleted = cardService.deleteCard(mobileNumber);

        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDTO(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDTO(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
    }

    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @GetMapping("/cards-contact")
    public ResponseEntity<CardsContactInfoDTO> getCardsContact() {
        return ResponseEntity.status(HttpStatus.OK).body(cardsContactInfoDTO);
    }
}
