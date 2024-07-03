package com.datum.services.ratingservice.controllers.v1;


import com.datum.services.ratingservice.reviews.dtos.FormDto;
import com.datum.services.ratingservice.reviews.entities.ReportCriteria;
import com.datum.services.ratingservice.reviews.forms.FormFieldValueForm;
import com.datum.services.ratingservice.reviews.services.FormService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }


    @GetMapping("/{id}")
    public FormDto getForm(@PathVariable Integer id) {
        return formService.get(id);
    }

    @PostMapping("/{formId}/submit")
    public FormDto submitForm(@PathVariable Integer formId, @RequestBody List<FormFieldValueForm> form) {
        return formService.submit(formId, form);
    }

    @GetMapping("/{id}/submissions")
    public Object getSubmissions(@PathVariable Integer id) {
        return formService.getSubmissions(id);
    }

    @PostMapping("/{id}/report")
    public Object getReport(@PathVariable Integer id, @RequestBody ReportCriteria reportCriteria) {
        return formService.createReport(id, reportCriteria);
    }

    @GetMapping("/{id}/qr")
    public Object getQR(@PathVariable Integer id) {
        var bytes = formService.qr(id);
        MediaType contentType = MediaType.IMAGE_PNG;
        return ResponseEntity.ok().contentType(contentType).body(new InputStreamResource(new ByteArrayInputStream(bytes)));
    }

}
