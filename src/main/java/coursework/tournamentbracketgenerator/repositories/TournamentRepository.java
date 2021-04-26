package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;


public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @RestResource(path = "my", rel = "my")
    @Query("SELECT t FROM Tournament t WHERE t.user.username = :#{authentication?.name}")
    Page<Tournament> findAllForAuthorizedUser(Pageable p);

    @RestResource(path = "by_name", rel = "by_name")
    Optional<Tournament> findByName(String name);

    @RestResource(path = "by_name_i", rel = "by_name_i")
    List<Tournament> findByNameIgnoreCase(String name);

    @RestResource(path = "filters", rel = "filters")
    @Query("SELECT t FROM Tournament t WHERE " +
            "t.user.username = :#{authentication?.name} AND " +
            "(:name is NULL OR t.name LIKE %:name%) AND " +
            "((:types) IS NULL OR (SELECT COUNT(b) FROM t.brackets b WHERE b.type.type IN (:types)) > 0) AND " +
            "((:start IS NULL OR :end is NULL) OR t.teams.size between :start AND :end)")
    Page<Tournament> findByTeamsSizeBetween(@Param("name") String name, @Param("types") List<String> types, @Param("start") Integer start, @Param("end") Integer end, Pageable p);



    @Override
    @PreAuthorize("#tournament?.user == null or #tournament?.user?.username == authentication?.name")
    @NonNull
    <S extends Tournament> S save(@NonNull @Param("tournament") S tournament);

    @Override
    @PreAuthorize("@tournamentRepository.findById(#id)?.orElseThrow()?.user?.username == authentication?.name")
    void deleteById(@NonNull @Param("id") Long id);

    @Override
    @PreAuthorize("#tournament?.user?.username == authentication?.name")
    void delete(@NonNull @Param("tournament") Tournament tournament);
}
