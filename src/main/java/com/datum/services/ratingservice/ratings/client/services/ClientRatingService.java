package com.datum.services.ratingservice.ratings.client.services;

import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingAnswerDto;
import com.datum.services.ratingservice.ratings.client.dtos.ClientRatingDto;
import com.datum.services.ratingservice.ratings.client.filters.ClientRatingFilter;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingAnswerForm;
import com.datum.services.ratingservice.ratings.client.forms.ClientRatingForm;
import com.datum.services.ratingservice.ratings.client.mappers.ClientRatingAnswerMapper;
import com.datum.services.ratingservice.ratings.client.mappers.ClientRatingMapper;
import com.datum.services.ratingservice.ratings.client.mappers.ClientRatingQuestionMapper;
import com.datum.services.ratingservice.ratings.client.repositories.ClientRatingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientRatingService {

    private final ClientRatingRepository clientRatingRepository;
    private final ClientRatingMapper clientRatingMapper;
    private final ClientRatingAnswerMapper clientRatingAnswerMapper;
    private final ClientRatingQuestionMapper clientRatingQuestionMapper;


    public ClientRatingService(ClientRatingRepository clientRatingRepository, ClientRatingMapper clientRatingMapper, ClientRatingAnswerMapper clientRatingAnswerMapper, ClientRatingQuestionMapper clientRatingQuestionMapper) {
        this.clientRatingRepository = clientRatingRepository;
        this.clientRatingMapper = clientRatingMapper;
        this.clientRatingAnswerMapper = clientRatingAnswerMapper;
        this.clientRatingQuestionMapper = clientRatingQuestionMapper;
    }


    public ClientRatingDto create(ClientRatingForm clientRatingForm) {
        var clientRating = clientRatingMapper.fromForm(clientRatingForm);
        for (var question : clientRating.getQuestions()) {
            question.setClientRating(clientRating);
        }
        clientRating = clientRatingRepository.save(clientRating);
        return clientRatingMapper.toDto(clientRating);
    }

    //todo: implement update properly
    public ClientRatingDto update(Integer id, ClientRatingForm clientRatingForm) {
        var clientRating = clientRatingRepository.findById(id).orElseThrow();
        var updatedClientRating = clientRatingMapper.fromForm(clientRatingForm);
        clientRating = clientRatingRepository.save(clientRating);
        return clientRatingMapper.toDto(clientRating);
    }

    public void delete(Integer id) {
        clientRatingRepository.deleteById(id);
    }

    public ClientRatingDto getById(Integer id) {
        var clientRating = clientRatingRepository.findById(id).orElseThrow();
        return clientRatingMapper.toDto(clientRating);
    }


    public Page<ClientRatingDto> getAll(ClientRatingFilter filter, Pageable pageable) {
        return clientRatingRepository.findAllFiltered(filter, pageable).map(clientRatingMapper::toDto);

    }

    public ClientRatingDto answer(Integer id, List<ClientRatingAnswerForm> answers) {
        var clientRating = clientRatingRepository.findById(id).orElseThrow(() -> new RuntimeException("Client rating not found"));
        for (var answerForm : answers) {
            var question = clientRating.getQuestions().stream()
                    .filter(q -> q.getId().equals(answerForm.getQuestionId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Question not found"));
            var answer = clientRatingAnswerMapper.fromForm(answerForm);
            answer.setQuestion(question);
            question.getAnswers().add(answer);
        }
        clientRating = clientRatingRepository.save(clientRating);
        return clientRatingMapper.toDto(clientRating);
    }

    public Page<ClientRatingAnswerDto> getAnswers(Integer id, Pageable pageable) {
        var clientRating = clientRatingRepository.findById(id).orElseThrow(() -> new RuntimeException("Client rating not found"));
        var answers = clientRating.getQuestions()
                .stream()
                .flatMap(q -> q.getAnswers().stream())
                .map(clientRatingAnswerMapper::toDto)
                .toList();

        return new PageImpl<>(answers, pageable, answers.size());
    }
}
