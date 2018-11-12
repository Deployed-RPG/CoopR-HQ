package com.coopr.hq.endpoints;

import com.coopr.hq.models.Character;
import com.coopr.hq.models.Person;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Level;

/***************************************
 * Author: xetra11                     
 * Datum: 12.11.2018                      
 * Funktion/Komponente: -              
 * Beschreibung: -                     
 * API: -                              
 **************************************/
@RestController
@Log
public class HqEndpoint {

  private MongoTemplate mongoTemplate;

  @Autowired
  public HqEndpoint(MongoTemplate mongoTemplate) {
    this.mongoTemplate = mongoTemplate;
  }

  @PostMapping(value = "/save/characters/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateCharacters(@RequestBody List<Character> characters) {
    mongoTemplate.save(characters);
    log.log(Level.INFO, "{} characters have been saved", characters.size());
  }

  @PostMapping(value = "/save/character/", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void updateCharacter(@RequestBody Character character) {
    mongoTemplate.save(character);
    log.log(Level.INFO, "character with uid {} has been saved", character.getUid());
  }

  @GetMapping("/fetch/characters/")
  public List<Character> fetchCharacter() {
    return mongoTemplate.findAll(Character.class);
  }

}
