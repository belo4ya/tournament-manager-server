package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team, Long> {

    @RestResource(path = "by_name", rel = "by_name")
    Optional<Team> findByName(String name);

    @RestResource(path = "by_name_i", rel = "by_name_i")
    List<Team> findByNameIgnoreCase(String name);

    @RestResource(path = "my", rel = "my")
    @PostFilter("filterObject?.user?.username == authentication?.name")
    @NonNull
    List<Team> findAllBy();

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
