package nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services;

import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.dtos.RecipeUserFormDTO;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.model.RecipeUser;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.repositories.RecipeUserRepository;
import nl.miwnn.cohort13.hashtagsyntaxsquad.recipeproject.services.mappers.RecipeUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RecipeUserService implements UserDetailsService {
    private final RecipeUserRepository recipeUserRepository;
    private final PasswordEncoder passwordEncoder;

    public RecipeUserService(RecipeUserRepository recipeUserRepository, PasswordEncoder passwordEncoder) {
        this.recipeUserRepository = recipeUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return recipeUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public boolean userExists(String username) {
        return recipeUserRepository.findByUsername(username).isPresent();
    }
    public void saveUser(RecipeUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        recipeUserRepository.save(user);
    }

    public void saveUser(RecipeUserFormDTO dto) {
        saveUser(RecipeUserMapper.fromDTO(dto));
    }

    public boolean isNotInitialised() {
        return recipeUserRepository.count() == 0;
    }
}
