package com.datum.services.ratingservice.reviews.services;


import com.datum.services.ratingservice.reviews.dtos.FormDto;
import com.datum.services.ratingservice.reviews.dtos.FormValuesDto;
import com.datum.services.ratingservice.reviews.entities.FormFieldValue;
import com.datum.services.ratingservice.reviews.entities.ReportCriteria;
import com.datum.services.ratingservice.reviews.forms.FormFieldValueForm;
import com.datum.services.ratingservice.reviews.mappers.FormFieldMapper;
import com.datum.services.ratingservice.reviews.mappers.FormMapper;
import com.datum.services.ratingservice.reviews.repositories.FormFieldRepository;
import com.datum.services.ratingservice.reviews.repositories.FormFieldValueRepository;
import com.datum.services.ratingservice.reviews.repositories.FormRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.SneakyThrows;
import org.slf4j.event.KeyValuePair;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.*;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FormMapper formMapper;
    private final FormFieldRepository formFieldRepository;
    private final FormFieldValueRepository formFieldValueRepository;
    private final FormFieldMapper formFieldMapper;


    public FormService(FormRepository formRepository, FormMapper formMapper, FormFieldRepository formFieldRepository, FormFieldValueRepository formFieldValueRepository, FormFieldMapper formFieldMapper) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
        this.formFieldRepository = formFieldRepository;
        this.formFieldValueRepository = formFieldValueRepository;
        this.formFieldMapper = formFieldMapper;
    }

    public FormDto get(Integer id) {
        var form = formRepository.findById(id).orElseThrow();
        return formMapper.toDto(form);
    }


    public FormDto submit(Integer id, List<FormFieldValueForm> form) {
        var formEntity = formRepository.findById(id).orElseThrow();

        form.forEach(formFieldValueForm -> {
            var formFieldValue = new FormFieldValue();
            formFieldValue.setValue(formFieldValueForm.getValue());
            var formField = formFieldRepository.findById(formFieldValueForm.getFormFieldId()).orElseThrow();
            formFieldValue.setFormField(formField);

            //todo validate value based on form field type
//            switch (formField.getValueType()) {
//                case FormField.VALUE_TYPE.NUMBER -> {
//                    try {
//                        Integer.parseInt(formFieldValueForm.getValue());
//                    } catch (NumberFormatException e) {
//                        throw new RuntimeException("Invalid value for field " + formField.getName());
//                    }
//                }
//                case FormField.VALUE_TYPE.EMAIL -> {
//                    if (!formFieldValueForm.getValue().contains("@")) {
//                        throw new RuntimeException("Invalid value for field " + formField.getName());
//                    }
//                }
//                case FormField.VALUE_TYPE.TEL -> {
//                    if (formFieldValueForm.getValue().length() != 10) {
//                        throw new RuntimeException("Invalid value for field " + formField.getName());
//                    }
//                }
//                case FormField.VALUE_TYPE.DATE, FormField.VALUE_TYPE.TIME -> {
//                    try {
//                        new Date(formFieldValueForm.getValue());
//                    } catch (Exception e) {
//                        throw new RuntimeException("Invalid value for field " + formField.getName());
//                    }
//                }
//            }

            formFieldValueRepository.save(formFieldValue);
        });

        return formMapper.toDto(formEntity);
    }

    public Object getSubmissions(Integer id) {
        var form = formRepository.findById(id).orElseThrow();
        var data = form.getFormFields()
                .stream()
                .map(formField -> {
                    var formValuesDto = new FormValuesDto();
                    formValuesDto.setFormField(formFieldMapper.toDto(formField));
                    formValuesDto.setValues(formField.getFormFieldValues().stream().map(FormFieldValue::getValue).toList());
                    return formValuesDto;
                })
                .toList();

        List<Object> jsonArray = new ArrayList<>();

        for (FormValuesDto formField : data) {
            formField.getValues().forEach(s -> {
                var jsonObject = new ArrayList<KeyValuePair>();
                jsonObject.add(new KeyValuePair(formField.getFormField().getName(), s));
                jsonArray.add(jsonObject);
            });
        }

        Map<String, Map<String, String>> result = new HashMap<>();
        for (FormValuesDto item : data) {
            String key = item.getFormField().getName();
            List<String> values = item.getValues();
            for (int j = 0; j < values.size(); j++) {
                String value = values.get(j);
                if (!result.containsKey(String.valueOf(j))) {
                    result.put(String.valueOf(j), new HashMap<>());
                }
                result.get(String.valueOf(j)).put(key, value);
            }
        }

        System.out.println(result);

        return result.values().stream().toList();
    }

    public Object createReport(Integer id, ReportCriteria criteria) {
        var form = formRepository.findById(id).orElseThrow();
        var data = form.getFormFields().stream().filter(formField -> criteria.getFilters().stream().anyMatch(reportFilterForm -> reportFilterForm.getFormFieldId().equals(formField.getId()))).flatMap(formField -> formField.getFormFieldValues().stream()).toList();


        var filtered = data.stream().filter(formFieldValue -> {
                    var result = true;
                    var filter = criteria.getFilters().stream().filter(reportFilterForm -> reportFilterForm.getFormFieldId().equals(formFieldValue.getFormField().getId())).findFirst().orElseThrow();
                    if (filter.getOperation() == null) return true;
                    switch (filter.getOperation()) {
                        case EQUALS -> result = formFieldValue.getValue().equals(filter.getStartValue());
                        case NOT_EQUALS -> result = !formFieldValue.getValue().equals(filter.getStartValue());
                        case GREATER_THAN -> {
                            switch (formFieldValue.getFormField().getValueType()) {
                                case NUMBER ->
                                        result = formFieldValue.getValue().contains(".") ? Double.parseDouble(formFieldValue.getValue()) > Double.parseDouble(filter.getStartValue()) : Integer.parseInt(formFieldValue.getValue()) > Integer.parseInt(filter.getStartValue());
                                case DATE, TIME ->
                                        result = new Date(formFieldValue.getValue()).after(new Date(filter.getStartValue()));
                            }

                        }
                        case LESS_THAN -> {
                            switch (formFieldValue.getFormField().getValueType()) {
                                case NUMBER ->
                                        result = formFieldValue.getValue().contains(".") ? Double.parseDouble(formFieldValue.getValue()) < Double.parseDouble(filter.getStartValue()) : Integer.parseInt(formFieldValue.getValue()) < Integer.parseInt(filter.getStartValue());
                                case DATE, TIME ->
                                        result = new Date(formFieldValue.getValue()).before(new Date(filter.getStartValue()));
                            }
                        }
                        case BETWEEN -> {
                            switch (formFieldValue.getFormField().getValueType()) {
                                case NUMBER ->
                                        result = formFieldValue.getValue().contains(".") ? Double.parseDouble(formFieldValue.getValue()) > Double.parseDouble(filter.getStartValue()) && Double.parseDouble(formFieldValue.getValue()) < Double.parseDouble(filter.getEndValue()) : Integer.parseInt(formFieldValue.getValue()) > Integer.parseInt(filter.getStartValue()) && Integer.parseInt(formFieldValue.getValue()) < Integer.parseInt(filter.getEndValue());
                                case DATE, TIME ->
                                        result = new Date(formFieldValue.getValue()).after(new Date(filter.getStartValue())) && new Date(formFieldValue.getValue()).before(new Date(filter.getEndValue()));
                            }
                        }
                        case IN -> {
                            var values = filter.getStartValue().split(",");
                            result = Arrays.stream(values).toList().contains(formFieldValue.getValue());
                        }
                        case NOT_IN -> {
                            var values = filter.getStartValue().split(",");
                            result = !Arrays.stream(values).toList().contains(formFieldValue.getValue());
                        }
                        case CONTAINS -> result = formFieldValue.getValue().contains(filter.getStartValue());
                        case NOT_CONTAINS -> result = !formFieldValue.getValue().contains(filter.getStartValue());
                        case IS_EMPTY -> result = formFieldValue.getValue().isEmpty();
                        case IS_NOT_EMPTY -> result = !formFieldValue.getValue().isEmpty();
                        case IS_TRUE -> result = formFieldValue.getValue().equals("true");
                        case IS_FALSE -> result = formFieldValue.getValue().equals("false");
                    }
                    if (!result) formFieldValue.setValue(null);
                    return result;
                })
                .toList();


        List<Object> jsonArray = new ArrayList<>();

        for (var formFieldValue : filtered) {
            formFieldValue.getFormField().getFormFieldValues().forEach(s -> {
                var jsonObject = new ArrayList<KeyValuePair>();
                jsonObject.add(new KeyValuePair(formFieldValue.getFormField().getName(), s));
                jsonArray.add(jsonObject);
            });
        }

        Map<String, Map<String, String>> result = new HashMap<>();
        for (var item : filtered) {
            String key = item.getFormField().getName();
            List<String> values = item.getFormField().getFormFieldValues().stream().map(FormFieldValue::getValue).toList();
            for (int j = 0; j < values.size(); j++) {
                String value = values.get(j);
                if (!result.containsKey(String.valueOf(j))) {
                    result.put(String.valueOf(j), new HashMap<>());
                }
                result.get(String.valueOf(j)).put(key, value);
            }
        }

        System.out.println(result);

        return result.values()
                .stream()
                .filter(stringMap -> stringMap.values().stream().allMatch(Objects::nonNull))
                .toList();
    }


    @SneakyThrows
    public byte[] qr(Integer id) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(id.toString(), BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

}
