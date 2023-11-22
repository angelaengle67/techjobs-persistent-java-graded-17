package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public class SkillController {
    @Autowired
    private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("skills", skillRepository.findAll());
        return "skills/index";
    };

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        } else {
            skillRepository.save(newSkill);
        }

        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewSkill(Model model, @PathVariable int skills) {

        Optional optSkill = skillRepository.findById(skills);
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }

    }
}
