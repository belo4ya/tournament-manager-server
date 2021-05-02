package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.Optional;


public interface TeamRepository extends JpaRepository<Team, Long> {

    @RestResource(path = "my", rel = "my")
    @Query("SELECT t FROM Team t WHERE t.user.username = :#{authentication?.name}")
    Page<Team> findAllForAuthorizedUser(Pageable p);

    @RestResource(path = "ilike", rel = "ilike")
    @Query("SElECT t from Team t WHERE LOWER(t.name) LIKE CONCAT('%', LOWER(:team), '%') AND t.user.username = :#{authentication?.name}")
    Page<Team> findAllByNameLike(@Param("team") String team, Pageable p);



    @RestResource(exported = false)
    Optional<Team> findByName(String name);

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
