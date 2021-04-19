package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Tournament;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;


public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @RestResource(path = "by_name", rel = "by_name")
    Optional<Tournament> findByName(String name);

    @RestResource(path = "by_name_i", rel = "by_name_i")
    List<Tournament> findByNameIgnoreCase(String name);

    @Override
    <S extends Tournament> List<S> findAll(Example<S> example);

    @RestResource(path = "my", rel = "my")
    @PostFilter("filterObject?.user?.username == authentication?.name")
    @NonNull
    List<Tournament> findAllBy();

    @Override
    @PreAuthorize("#tournament?.user == null or #tournament?.user?.username == authentication?.name")
    @NonNull <S extends Tournament> S save(@NonNull @Param("tournament") S tournament);

    @Override
    @PreAuthorize("@tournamentRepository.findById(#id)?.user?.username == authentication?.name")
    void deleteById(@NonNull @Param("id") Long id);

    @Override
    @PreAuthorize("#tournament?.user?.username == authentication?.name")
    void delete(@NonNull @Param("tournament") Tournament tournament);

    @RestResource(path = "by_team_size", rel = "by_team_size")
    @Query("SELECT t FROM Tournament t WHERE t.teams.size between :start AND :end")
    List<Tournament> findByTeamsSizeBetween(@Param("start") Integer start, @Param("end") Integer end);
}
