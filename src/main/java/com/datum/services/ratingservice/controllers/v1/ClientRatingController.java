package com.datum.services.ratingservice.controllers.v1;

import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingAnswerDto;
import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingDto;
import com.datum.services.ratingservice.ratings.client.filters.ClientRatingFilter;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingAnswerForm;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingForm;
import com.datum.services.ratingservice.ratings.client.services.ClientRatingService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/client-ratings")
public class ClientRatingController {

    private final ClientRatingService clientRatingService;

    public ClientRatingController(ClientRatingService clientRatingService) {
        this.clientRatingService = clientRatingService;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    @PageableAsQueryParam
    public Page<ClientRatingDto> getAll(@ModelAttribute ClientRatingFilter filter, @Parameter(hidden = true) Pageable pageable) {
        return clientRatingService.getAll(filter, pageable);
    }

    @GetMapping("/{id}")
    public ClientRatingDto getById(@PathVariable Integer id) {
        return clientRatingService.getById(id);
    }

    @PostMapping
    public ClientRatingDto create(@RequestBody ClientRatingForm clientRatingForm) {
        return clientRatingService.create(clientRatingForm);
    }

    @PutMapping("/{id}")
    public ClientRatingDto update(@PathVariable Integer id, @RequestBody ClientRatingForm clientRatingForm) {
        return clientRatingService.update(id, clientRatingForm);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientRatingService.delete(id);
    }


    @PostMapping("/{id}/answer")
    public ClientRatingDto answer(@PathVariable Integer id, @RequestBody List<ClientRatingAnswerForm> answers) {
        return clientRatingService.answer(id, answers);
    }

    @GetMapping("/{id}/answers")
    @PageableAsQueryParam
    public Page<ClientRatingAnswerDto> getAnswers(@PathVariable Integer id, @Parameter(hidden = true) Pageable pageable) {
        return clientRatingService.getAnswers(id, pageable);
    }

}
