package com.datum.services.ratingservice.controllers.v1;


import com.datum.services.ratingservice.reviews.dtos.ClientDto;
import com.datum.services.ratingservice.reviews.dtos.FormDto;
import com.datum.services.ratingservice.reviews.forms.ClientForm;
import com.datum.services.ratingservice.reviews.forms.FormForm;
import com.datum.services.ratingservice.reviews.services.ClientService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;


    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto create(@RequestBody ClientForm client) {
        return clientService.create(client);
    }

    @PostMapping("/{id}/forms")
    public FormDto createForm(@PathVariable Integer id, @RequestBody FormForm form) {

        return clientService.createForm(id, form);
    }

    @GetMapping
    @PageableAsQueryParam
    public List<ClientDto> getAll(@Parameter(hidden = true) Pageable pageable) {
        return clientService.getAll(pageable).stream().toList();
    }

    @DeleteMapping("/{id}")
    public ClientDto delete(@PathVariable Integer id) {
        return clientService.delete(id);
    }

    @GetMapping("/{id}/forms")
    public List<FormDto> getForms(@PathVariable Integer id) {
        return clientService.getForms(id);
    }


}