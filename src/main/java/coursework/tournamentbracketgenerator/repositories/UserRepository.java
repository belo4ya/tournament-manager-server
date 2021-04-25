package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    @RestResource(path = "my", rel = "my")
    @Query("SELECT u FROM User u WHERE u.username = :#{authentication?.name}")
    Optional<User> findAuthorizedUser();

    @PreAuthorize("#id == @userRepository.findByUsername(authentication?.name)?.orElseThrow()?.id")
    @NonNull
    Optional<User> findById(@NonNull @Param("id") Long id);



    @RestResource(exported = false)
    Optional<User> findByUsername(String username);
}
