package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.Tag;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.TagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author #SyntaxSquad
 * Deal with everything related to labels/categories
 **/

@Controller
public class TagController {
    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping("/tag")
    private String showAllTags(Model model) {
        model.addAttribute("allTags", tagRepository.findAll());
        model.addAttribute("newTag", new Tag());

        return "tagOverview";
    }

    @PostMapping("/tag/new")
    private String saveOrUpdateTag
            (@ModelAttribute("newTag") Tag tagToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            tagRepository.save(tagToBeSaved);
        }

        return "redirect:/tag";
    }
}
