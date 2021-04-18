package coursework.tournamentbracketgenerator.repositories;

import coursework.tournamentbracketgenerator.models.Tournament;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;


public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    @RestResource(path = "by-name", rel = "by-name")
    Optional<Tournament> findByName(String name);

    @RestResource(path = "by-name-i", rel = "by-name-i")
    List<Tournament> findByNameIgnoreCase(String name);

    @Override
    <S extends Tournament> List<S> findAll(Example<S> example);

    @RestResource(path = "by-user", rel = "by-user")
    @PreAuthorize("#user == authentication?.name")
    List<Tournament> findByUser_Username(@Param("user") String username);

    @Override
    @PreAuthorize("#tournament?.user == null or #tournament?.user?.username == authentication?.name")
    @NonNull <S extends Tournament> S save(@NonNull @Param("tournament") S tournament);

    @Override
    @PreAuthorize("@tournamentRepository.findById(#id)?.user?.username == authentication?.name")
    void deleteById(@NonNull @Param("id") Long id);

    @Override
    @PreAuthorize("#tournament?.user?.username == authentication?.name")
    void delete(@NonNull @Param("tournament") Tournament tournament);
}
