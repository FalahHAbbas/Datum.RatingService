package com.datum.services.ratingservice.reviews.services;


import com.datum.services.ratingservice.reviews.dtos.ClientDto;
import com.datum.services.ratingservice.reviews.dtos.FormDto;
import com.datum.services.ratingservice.reviews.entities.Client;
import com.datum.services.ratingservice.reviews.forms.ClientForm;
import com.datum.services.ratingservice.reviews.forms.FormForm;
import com.datum.services.ratingservice.reviews.mappers.ClientMapper;
import com.datum.services.ratingservice.reviews.mappers.FormMapper;
import com.datum.services.ratingservice.reviews.repositories.ClientRepository;
import com.datum.services.ratingservice.reviews.repositories.FormRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, FormRepository formRepository, ClientMapper clientMapper, FormMapper formMapper) {
        this.clientRepository = clientRepository;
        this.formRepository = formRepository;
        this.clientMapper = clientMapper;
        this.formMapper = formMapper;
    }

    public ClientDto create(ClientForm clientForm) {
        var client = new Client();
        client.setName(clientForm.getName());
        client.setEmail(clientForm.getEmail());
        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

    public FormDto createForm(Integer id, FormForm formForm) {
        var client = clientRepository.findById(id).orElseThrow();
        var form = formMapper.fromForm(formForm);
        form.setClient(client);
        for (var formField : form.getFormFields()) {
            formField.setForm(form);
        }
        form = formRepository.save(form);
        return formMapper.toDto(form);
    }


    public Page<ClientDto> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(client -> {
            var clientDto = new ClientDto();
            clientDto.setId(client.getId());
            clientDto.setName(client.getName());
            clientDto.setEmail(client.getEmail());
            return clientDto;
        });
    }

    public ClientDto delete(Integer id) {
        var client = clientRepository.findById(id).orElseThrow();
        clientRepository.delete(client);
        var clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
        return clientDto;
    }

    public List<FormDto> getForms(Integer id) {
        var client = clientRepository.findById(id).orElseThrow();
        return client.getForms().stream().map(formMapper::toDto).toList();
    }
}
