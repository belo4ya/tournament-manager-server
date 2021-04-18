package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team, Long> {

    @RestResource(path = "by-name", rel = "by-name")
    Optional<Team> findByName(String name);

    @RestResource(path = "by-name-i", rel = "by-name-i")
    List<Team> findByNameIgnoreCase(String name);

    @RestResource(path = "users", rel = "users")
    @PreAuthorize("#username == authentication?.name")
    List<Team> findByUser_Username(@Param("username") String username);

    @Override
    @PreAuthorize("#team?.user == null or #team?.user?.username == authentication?.name")
    @NonNull
    <S extends Team> S save(@NonNull @Param("team") S team);

    @Override
    @PreAuthorize("@teamRepository.findById(#id)?.user?.username == authentication?.name")
    void deleteById(@NonNull @Param("id") Long id);

    @Override
    @PreAuthorize("#team?.user?.username == authentication?.name")
    void delete(@NonNull @Param("team") Team team);
}
