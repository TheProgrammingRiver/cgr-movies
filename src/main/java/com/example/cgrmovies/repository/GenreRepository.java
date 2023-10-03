package com.example.cgrmovies.repository;

import com.example.cgrmovies.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findByUserId(Long userId);

    Optional<Genre> findByNameAndUserId(String name, Long userId);
    List<Genre> findAllByUserId(Long userId);


    /**
     * Find genres by the user id and returns just they're Ids.
     * @param userId User Id.
     * @return List of genre Ids.
     * @see <a href="https://faun.pub/select-specific-columns-from-a-database-table-using-spring-data-jpa-d4eb0a24a2c4">Select specific columns from a database table using Spring Data JPA</a>
     */
    @Query("SELECT g.id FROM Genre g WHERE g.user.id = :userId")
    List<Long> findIdsByUserId(@Param("userId") Long userId);
    Optional<Genre> findByIdAndUserId(Long genreId, Long userId);
}
