package com.example.restservice.controller;

import com.example.restservice.model.MountRule;
import com.example.restservice.repository.MountRuleRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
public class MountRuleController {
    @GetMapping("/mountrule")
    public List<MountRule> getMountsRule() {
        try {
            return MountRuleRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
