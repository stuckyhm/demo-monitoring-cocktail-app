package de.stuckenbrockhm.demo.monitoring.content.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.stuckenbrockhm.demo.monitoring.api.ContentBaseApi;
import de.stuckenbrockhm.demo.monitoring.content.services.ContentService;

import lombok.extern.java.Log;

@Log
@RestController
public class ContentController {

    private ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping(path = "/contents", produces = "application/json") //, consumes = "application/json"
    public ResponseEntity<ContentBaseApi> save() {
        ContentBaseApi content = contentService.getContents();
        return new ResponseEntity<>(content, HttpStatus.OK);
    }

}
