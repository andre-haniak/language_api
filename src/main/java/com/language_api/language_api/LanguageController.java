package com.language_api.language_api;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LanguageController {
    
    @Autowired
    private LanguageRepository repository;

    @GetMapping("languages")
    public List<Language> getLanguage() {
        List<Language> languages = repository.findAll();
        return languages;
    }

    @PostMapping("languages")
    public ResponseEntity<Language> registerLanguage(@RequestBody Language language) {
        Language saveLanguage = repository.save(language);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveLanguage);
    }

    @PutMapping("languages/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable String id, @RequestBody Language language) {
    return repository.findById(id)
        .map(languageExist -> {
            languageExist.setTitle(language.getTitle());
            languageExist.setImage(language.getImage());
            languageExist.setRanking(language.getRanking());
            Language updateLanguage = repository.save(languageExist);
            return ResponseEntity.ok(updateLanguage);
        })
        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("languages/ranking")
    public List<Language> getOrderByRanking(){
        List<Language> languages = repository.findAll();
        languages.sort(Comparator.comparingInt(Language::getRanking));
        return languages;
    }
}
