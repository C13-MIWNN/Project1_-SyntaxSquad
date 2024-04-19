package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.controller;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.dtos.RecipeUserFormDTO;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.RecipeUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * @author Sami ARSLAN
 * <p>
 * purpose for the class
 **/
@Controller
public class RecipeUserController {
    private final RecipeUserService recipeUserService;

    public RecipeUserController(RecipeUserService recipeUserService) {
        this.recipeUserService = recipeUserService;
    }

    @GetMapping("/user/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new RecipeUserFormDTO());
        return "userForm";
    }

    @PostMapping("/user/new")
    public String processUserForm(@ModelAttribute("user") RecipeUserFormDTO recipeUserFormDTO,
                                  BindingResult bindingResult) {
        if (recipeUserService.userExists(recipeUserFormDTO.getName())) {
            bindingResult.rejectValue("name", "duplicate", "This username is not available");
        }

        if (!recipeUserFormDTO.getPassword().equals(recipeUserFormDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", "no.match", "The passwords do not match");
        }

        if(bindingResult.hasErrors()) {
            return "userForm";
        }

        recipeUserService.saveUser(recipeUserFormDTO);
        return "redirect:/user/new";
    }
}
